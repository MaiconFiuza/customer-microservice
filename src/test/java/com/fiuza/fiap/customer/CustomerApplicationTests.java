package com.fiuza.fiap.customer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
		"springdoc.api-docs.enabled=false",
		"springdoc.swagger-ui.enabled=false",
		"spring.flyway.enabled=false"

})
class CustomerApplicationTests {

	@Test
	void contextLoads() {
	}

}
