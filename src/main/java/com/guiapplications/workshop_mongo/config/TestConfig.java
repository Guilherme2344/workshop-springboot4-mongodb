package com.guiapplications.workshop_mongo.config;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.guiapplications.workshop_mongo.domain.Post;
import com.guiapplications.workshop_mongo.domain.User;
import com.guiapplications.workshop_mongo.dto.AuthorDTO;
import com.guiapplications.workshop_mongo.repository.PostRepository;
import com.guiapplications.workshop_mongo.repository.UserRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
				.withZone(ZoneId.of("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post p1 = new Post(null, LocalDate.parse("21/03/2018", formatter), "Partiu viagem", "Vou viajar", new AuthorDTO(maria));
		Post p2 = new Post(null, LocalDate.parse("23/03/2018", formatter), "Partiu comer", "Vou comer", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(p1, p2));
	
		maria.getPosts().addAll(Arrays.asList(p1, p2));
		userRepository.save(maria);
	}

}
