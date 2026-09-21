package br.cantaruttim.dataforge_api;

import br.cantaruttim.dataforge_api.models.users.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.cantaruttim.dataforge_api.services.UserService;

@SpringBootApplication
public class DataforgeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataforgeApiApplication.class, args);
	}

	@Bean
	CommandLineRunner testUserCreation(UserService userService) {
		return args -> {

			User user = userService.createUser("Matheus", "matheus@cantarutti.com");

			System.out.println(
					"Usuário criado: " + user.getId()
			);
		};
	}
}
