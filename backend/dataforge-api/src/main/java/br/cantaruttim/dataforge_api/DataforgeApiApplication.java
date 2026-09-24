package br.cantaruttim.dataforge_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataforgeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataforgeApiApplication.class, args);
	}

	// 98b62c20-ac86-4386-bbd3-c4cbd8c363be == roleId
	// 725eff11-b65b-48dd-a7fc-7c21c9ff843f == permissionId

	// curl -X POST http://localhost:8080/roles/98b62c20-ac86-4386-bbd3-c4cbd8c363be/permissions/725eff11-b65b-48dd-a7fc-7c21c9ff843f
}
