package tn.project.PFE;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

@SpringBootApplication
@EnableAutoConfiguration
public class ProjetPfeApplication {
	private Map<String, Integer> progress = new HashMap<>();
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	public static void main(String[] args) {
		SpringApplication.run(ProjetPfeApplication.class, args);
	}
	@Bean
	public CommandLineRunner websocketDemo() {
	    return (args) -> {
	        while (true) {
	            try {
	                Thread.sleep(3*1000); // Each 3 sec.
	                progress.put("num1", randomWithRange(0, 100));
	                progress.put("num2", randomWithRange(0, 100));
	                messagingTemplate.convertAndSend("/topic/progress", this.progress);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    };
	}
	private int randomWithRange(int min, int max)
	{
		int range = Math.abs(max - min) + 1;
		return (int)(Math.random() * range) + (min <= max ? min : max);
	}
}
