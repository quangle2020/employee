package com.quanglv.gateway.web.rest;

import com.quanglv.gateway.config.DomainConfig;
import com.quanglv.gateway.config.restTemplate.ConstantsTemplate;
import com.quanglv.gateway.config.restTemplate.RestOperationsUtils;
import com.quanglv.gateway.service.dto.GetTokenResponseDTO;
import com.quanglv.gateway.service.dto.TestRequestDTO;
import com.quanglv.gateway.service.dto.TokenRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginResource {
    @Autowired
    private DomainConfig domainConfig;

    @Autowired
    private RestOperationsUtils restOperationsUtils;

    @PostMapping(value = "/get-token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getToken(@RequestBody TokenRequestDTO request){
        String apiName = "authorization/oauth/token";
        GetTokenResponseDTO response = restOperationsUtils.executeGetToken(domainConfig.getAuthorizationDomain(), apiName, ConstantsTemplate.httpMethod_POST, request, GetTokenResponseDTO.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/api/test")
    public ResponseEntity<?> test(@RequestBody TestRequestDTO request){
        String hostName = domainConfig.getResourceDomain();
        String apiName = "api/demo";
//        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTAzODY2MzcsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiIwOGI4NWI2NC01ODdmLTQ4NjEtODNlMC05MmZlNDg2ZjU0ZTgiLCJjbGllbnRfaWQiOiJjbGllbnRfaWQiLCJzY29wZSI6WyJ0cnVzdCJdfQ.RrnZvhJKN_pjJKVUFwWiyzSUdtM0NMVK2I5iG7F5Qbgc7dPi1YCJ0p8UOoKXk2Hhzz0FRl6C7sESgUa6W6STmyhG31Z2tJ4gGl15NzMogQMv7ZQ0EwZaMFE0754N1K1Mmy-qdkzLKVcEiTqAfGn2qzm1Eo3HOEcMcUYbPHi9PBnICF31lVGuK0L2z7lc-5Lw4jSaaM-SKturznZlyqtaMAWZKzc3wNsbF70tjmn9A4BGFwfPm1iuAEPzXVSSJXxnjVMmFHQBGID7-ErBYIbn9nOIbJJVKqWQHrQ6WdZR1xOzIhdAVcGb-YFWUoU3DdvxMm72ODQXj-DmNQDoHElkZg--k";
        String response = restOperationsUtils.executeCallApi(hostName, apiName, ConstantsTemplate.httpMethod_POST, null, String.class, request.getToken());
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/demo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String demo(){
        return "demo success";
    }
}
