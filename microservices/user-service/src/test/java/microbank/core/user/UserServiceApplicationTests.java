package microbank.core.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceApplicationTests {

	@Autowired
	private WebTestClient client;

	@Test
	void getUserById() {
		int userId = 1;

		client.get()
				.uri("/user/" + userId)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.userId").isEqualTo(userId);
	}

	@Test
	void getUserInvalidParameter() {
		client.get()
				.uri("/user/invalid")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/user/invalid");
	}

	@Test
	void getUserInvalidParameterNegativeValue() {
		int userIdInvalid = -100;

		client.get()
				.uri("/user/" + userIdInvalid)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isEqualTo(HttpStatus.NOT_FOUND)
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8);
	}
}
