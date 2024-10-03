package tn.project.PFE.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tn.project.PFE.config.JwtTokenUtil;
import tn.project.PFE.entities.JwtRequest;
import tn.project.PFE.entities.JwtResponse;
import tn.project.PFE.entities.User;
import tn.project.PFE.services.AutentifServ;
import tn.project.PFE.services.UserServices;

@RestController
@CrossOrigin
public class AutentifCont {

@Autowired
private AuthenticationManager authenticationManager;

@Autowired
private JwtTokenUtil jwtTokenUtil;
@Autowired
private UserServices userserv;
@Autowired
private AutentifServ userDetailsService;

@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

Authentication auth= authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

final UserDetails userDetails = userDetailsService
.loadUserByUsername(authenticationRequest.getUsername());

final String token = jwtTokenUtil.generateToken(userDetails);
UserDetails user=(UserDetails) auth.getPrincipal();
User u=new User();
u=userserv.retrouveUser(authenticationRequest.getUsername());
return ResponseEntity.ok(new JwtResponse(token,user.getUsername(),user.getAuthorities(),u.getFirst_name(),u.getLast_name(),u.getPhone_number(),u.getAddress(),u.getId()));
}

private Authentication authenticate(String username, String password) throws Exception {
try {
return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
} catch (DisabledException e) {
throw new Exception("USER_DISABLED", e);
} catch (BadCredentialsException e) {
throw new Exception("INVALID_CREDENTIALS", e);
}
}
}