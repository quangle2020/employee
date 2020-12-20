package com.example.demo.config.restTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestOperations;

@Component
@RequiredArgsConstructor
public class RestOperationsUtils {

    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    private static final String BEARER_HEADER_NAME = "Bearer ";

    @Autowired
    private RestOperations restOperations;

    /**
     * string url
     * @param hostName
     * @param apiName
     * @return StringBuilder
     */
    private String url(String hostName, String apiName){
        StringBuilder s = new StringBuilder();
//        s.append("http://");
        s.append(hostName);
//        s.append("/");
        s.append(apiName);
        return s.toString();
    }

    /**
     * executeGetToken
     * @param hostName
     * @param apiName
     * @param httpMethod
     * @param request
     * @param responseType
     * @param <T>
     * @return
     */
    public <T> T executeGetToken(String hostName, String apiName, HttpMethod httpMethod, Object request,
                                Class<T> responseType) {
        try {
            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Authorization","Basic Y2xpZW50X2lkOmNsaWVudF9zZWNyZXQ=");
            request = null;
            HttpEntity<?> entity = new HttpEntity<>(request, headers);
            // RestTemplate

            // Call API
            ResponseEntity<T> response = restOperations.exchange(url(hostName, apiName), httpMethod, entity, responseType);

            // return response
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            throw e;
        }
    }

    public <T> T executeCallApi(String hostName, String apiName, HttpMethod httpMethod, Object request,
                                Class<T> responseType, String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Authorization","Basic Y2xpZW50X2lkOmNsaWVudF9zZWNyZXQ=");
            if (token != null && token.length() > 0) {
                headers.add(AUTHORIZATION_HEADER_NAME, BEARER_HEADER_NAME + token);
            }
            request = null;
            HttpEntity<?> entity = new HttpEntity<>(request, headers);
            // RestTemplate

            // Call API
            ResponseEntity<T> response = restOperations.exchange(url(hostName, apiName), httpMethod, entity, responseType);

            // return response
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            throw e;
        }
    }

    /**
     * executeCallPublicApi
     * @param apiName
     * @param httpMethod
     * @param request
     * @param responseType
     * @param <T>
     * @return T
     */
    public <T> T executeCallPublicApi( String hostName, String apiName, HttpMethod httpMethod, Object request,
                                       Class<T> responseType, String token) {
        return executeCallApi(hostName, apiName, httpMethod, request, responseType, null);
    }
}
