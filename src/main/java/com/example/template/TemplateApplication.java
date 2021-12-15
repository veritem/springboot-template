package com.example.template;

import com.example.template.enums.ERoleType;
import com.example.template.models.Role;
import com.example.template.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class TemplateApplication {
	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
	}
}


/*
@Component
class AppReady implements ApplicationRunner {
	@Autowired
	private final RoleRepository  roleRepository;

	public AppReady(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		roleRepository.save(new Role(ERoleType.ADMIN));
		roleRepository.save(new Role(ERoleType.USER));
		roleRepository.save(new Role(ERoleType.USER));
	}
}
*/
