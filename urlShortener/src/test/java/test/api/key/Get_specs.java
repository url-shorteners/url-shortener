package test.api.key;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.urlShortener.UrlShortenerApplication;

@SpringBootTest(classes = { UrlShortenerApplication.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
class Get_specs {

    @Autowired
    private TestRestTemplate client;

    @Test
    void sut_returns_moved_permanently_status_code() {

        // Arrange
        String createUrl = "/shorten-url/create";
        Map<String, Object> createRequestContent = new HashMap<>();
        createRequestContent.put("originalUrl", "https://github.com");
        String shortenUrl = client
                .postForObject(createUrl, createRequestContent, JsonNode.class)
                .get("shortenUrl")
                .asText();

        // Act
        ResponseEntity<JsonNode> response = client.getForEntity(shortenUrl, JsonNode.class);

        // Assert
        assertEquals(HttpStatus.MOVED_PERMANENTLY, response.getStatusCode());
    }

    @Test
    @SuppressWarnings("all")
    void sut_correctly_sets_location_header() {

        // Arrange
        String createUrl = "/shorten-url/create";
        Map<String, Object> createRequestContent = new HashMap<>();
        String originalUrl = "https://github.com";
        createRequestContent.put("originalUrl", originalUrl);
        String shortenUrl = client
                .postForObject(createUrl, createRequestContent, JsonNode.class)
                .get("shortenUrl")
                .asText();

        // Act
        ResponseEntity<JsonNode> response = client.getForEntity(shortenUrl, JsonNode.class);

        // Assert
        assertNotNull(response.getHeaders().getLocation());
        assertEquals(originalUrl, response.getHeaders().getLocation().toString());
    }

    @Test
    void sut_returns_not_found_status_code_if_key_not_exists() {

        // Arrange
        String key = UUID.randomUUID().toString();

        // Act
        String restoreUrl = "/" + key;
        ResponseEntity<JsonNode> response = client.getForEntity(restoreUrl, JsonNode.class);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
