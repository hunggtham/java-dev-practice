package org.global.api.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpCommonUtil {

    private static final RestTemplate restTemplate = new RestTemplate();

    /**
     * Gửi request GET tới Open Source API
     */
    public static <T> T get(String url, Class<T> responseType) {
        AppLog.debug("[HttpCommonUtil] GET Request to: {}", url);
        ResponseEntity<T> response = restTemplate.getForEntity(url, responseType);
        return response.getBody();
    }

    /**
     * Gửi request POST tới Open Source API
     */
    public static <T> T post(String url, Object requestBody, Class<T> responseType) {
        AppLog.debug("[HttpCommonUtil] POST Request to: {}", url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);
        
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType);
        return response.getBody();
    }
}
