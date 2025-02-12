package com.url_shortener;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "JWT_SECRET_KEY=KHbh89hebqSc65cRKFGDEc9hOn1GJn0SZauhze4DLaw=",
    "DB_URL=jdbc:postgresql://localhost:5432/url_shortener"
})
class URLShortenerApplicationTests {

	@Test
	void contextLoads() {
	}

}
