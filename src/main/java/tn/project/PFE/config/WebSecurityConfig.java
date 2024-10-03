package tn.project.PFE.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//@Value("${allowed.origin}")
//private String allowedOrigin;
@Autowired
private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

@Autowired
private UserDetailsService jwtUserDetailsService;

@Autowired
private JwtRequestFilter jwtRequestFilter;
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
// configure AuthenticationManager so that it knows from where to load
// user for matching credentials
// Use BCryptPasswordEncoder
auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
}

@Bean
public PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}

@Bean
@Override
public AuthenticationManager authenticationManagerBean() throws Exception {
return super.authenticationManagerBean();
}

@Override
protected void configure(HttpSecurity httpSecurity) throws Exception {
// We don't need CSRF for this example
	httpSecurity.cors();
	httpSecurity.csrf().disable()
// dont authenticate this particular request
.authorizeRequests().antMatchers("/displayProduit/","/ajouter","/getAbonnements","/getInteraction/{idProduit}","/produitParCateg/{idCat}","/retrouveAdmin","/listProduitsPlusVendus","/paymentAbonnement","/retriveSecretPaiementCle/{email}/{cle}","/initPassword/{email}","/ajouterCleForgotPassword","/getCleForgotPassword/{id}","/ajouterSecretPaiement","/ajouterAbonnement","/send-mail/{email}","/retrouveUserEmail/{email}","/retriveSecretPaiement/{email}","/ajoutContact","/displayCat","/retrouveProduit/{id}","/produitsSeuleCat/{idCat}","/displayProduitParMarque/{marque}","/authenticate","/retriveImage/{id}","/retrivephoto/{id}","/gkz-stomp-endpoint/**").permitAll().
// all other requests need to be authenticated
anyRequest().authenticated().
and(). 
// make sure we use stateless session; session won't be used to
// store user's state.
exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

// Add a filter to validate the tokens with every request
httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
}
@Bean
public WebMvcConfigurer getCorsConfigure() {
	return new WebMvcConfigurer() {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
			//.allowedOriginPatterns("*")
			.allowedOrigins("http://localhost:4200")
			.allowedMethods("GET","POST","PUT","DELETE")
			.allowedHeaders("*");
			
		}
	};
	
}
}