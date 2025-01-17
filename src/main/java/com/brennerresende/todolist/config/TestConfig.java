package com.brennerresende.todolist.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.brennerresende.todolist.entities.Category;
import com.brennerresende.todolist.entities.Task;
import com.brennerresende.todolist.entities.User;
import com.brennerresende.todolist.entities.enums.Priority;
import com.brennerresende.todolist.entities.enums.TaskStatus;
import com.brennerresende.todolist.repositories.CategoryRepository;
import com.brennerresende.todolist.repositories.TaskRepository;
import com.brennerresende.todolist.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", passwordEncoder.encode("123456"), "ROLE_USER");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", passwordEncoder.encode("123456"), "ROLE_ADMIN");
        User u3 = new User(null, "Carl Purple", "carl@gmail.com", passwordEncoder.encode("123456"), "ROLE_USER");
		
		Category c1 = new Category(null, "Selfcare", null);
		Category c2 = new Category(null, "Studies", null);
		Category c3 = new Category(null, "Income Improvement", null);
		Category c4 = new Category(null, "Work", null);
		
		categoryRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		Task t1 = new Task(null,"Dentist",
				"Lorem ipsum dolor",
				Instant.parse("2024-12-21T14:53:07Z"),
				Instant.parse("2024-12-21T16:10:07Z"),
				TaskStatus.DONE,
				Priority.MEDIUM,
				c1,
				u1);
		Task t2 = new Task(null,"Work",
				"Lorem ipsum dolor",
				Instant.parse("2024-12-27T08:00:07Z"),
				Instant.parse("2024-12-27T17:10:07Z"),
				TaskStatus.DONE,
				Priority.HIGH,
				c4,
				u2);
		
		userRepository.saveAll(Arrays.asList(u1,u2, u3));
		taskRepository.saveAll(Arrays.asList(t1, t2));
	}
}
