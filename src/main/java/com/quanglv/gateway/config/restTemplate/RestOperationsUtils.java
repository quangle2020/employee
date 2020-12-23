package com.quanglv.gateway.config.restTemplate;

import com.quanglv.gateway.service.dto.TokenRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
    public <T> T executeGetToken(String hostName, String apiName, HttpMethod httpMethod, TokenRequestDTO request,
                                Class<T> responseType) {
        try {
            // set param
            MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
            postParameters.add("grant_type", request.getGrant_type());
            postParameters.add("username", request.getUsername());
            postParameters.add("password", request.getPassword());
            postParameters.add("scope", request.getScope());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Authorization","Basic Y2xpZW50X2lkOmNsaWVudF9zZWNyZXQ=");

            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(postParameters, headers);
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
            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.add("Authorization","Basic Y2xpZW50X2lkOmNsaWVudF9zZWNyZXQ=");
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
