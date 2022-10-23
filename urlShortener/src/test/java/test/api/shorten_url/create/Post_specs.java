package test.api.shorten_url.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

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
class Post_specs {

    private static final String path = "/shorten-url/create";

    @Autowired
    private TestRestTemplate client;

    @Test
    void sut_returns_bad_request_status_code_if_original_url_is_invalid() {

        // Arrange
        Map<String, Object> requestContent = new HashMap<>();
        requestContent.put("originalUrl", "This is not a URL.");

        // Act
        ResponseEntity<JsonNode> response = client.postForEntity(path, requestContent, JsonNode.class);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void sut_returns_ok_status_code_if_original_url_is_valid() {

        // Arrange
        Map<String, Object> requestContent = new HashMap<>();
        requestContent.put("originalUrl", "https://github.com");

        // Act
        ResponseEntity<JsonNode> response = client.postForEntity(path, requestContent, JsonNode.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void sut_returns_shorten_url_as_body_if_original_url_is_valid() {

        // Arrange
        Map<String, Object> requestContent = new HashMap<>();
        requestContent.put("originalUrl", "https://github.com");

        // Act
        JsonNode responseContent = client.postForObject(path, requestContent, JsonNode.class);

        // Assert
        assertNotNull(requestContent);
        assertTrue(responseContent.has("shortenUrl"));
        assertNotNull(responseContent.get("shortenUrl").asText());
    }

    @Test
    void sut_returns_same_shorten_url_for_same_original_urls() {

        // Arrange
        String originalUrl = "https://github.com";

        // Act
        String shortenUrl1 = shorten(originalUrl);
        String shortenUrl2 = shorten(originalUrl);

        // Assert
        assertEquals(shortenUrl1, shortenUrl2);
    }

    private String shorten(String originalUrl) {
        Map<String, Object> requestContent = new HashMap<>();
        requestContent.put("originalUrl", originalUrl);
        JsonNode responseContent = client.postForObject(path, requestContent, JsonNode.class);
        return responseContent.get("shortenUrl").asText();
    }
}
