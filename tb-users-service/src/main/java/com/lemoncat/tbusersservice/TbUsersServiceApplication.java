package com.lemoncat.tbusersservice;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TbUsersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TbUsersServiceApplication.class, args);
	}

	static Keycloak keycloak = null;
	final static String serverUrl = "http://localhost:8080";
	public final static String realm = "tasksbook";
	final static String clientId = "tb-api-gateway";
	final static String clientSecret = "1yaN8ymDTviIWTC91qmcsXBS6ngBC2zm";
	final static String userName = "admin";
	final static String password = "123";

	@Bean
	Keycloak keycloak() {
		return KeycloakBuilder.builder()
				.serverUrl("http://localhost:8080")
				.realm(realm)
				.clientId(clientId)
				.clientSecret(clientSecret)
				.grantType(OAuth2Constants.PASSWORD)
				.username(userName)
				.password(password)
				.build();
	}
}
