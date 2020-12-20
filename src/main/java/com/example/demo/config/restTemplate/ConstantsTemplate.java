package com.example.demo.config.restTemplate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpMethod;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class ConstantsTemplate implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2836090537440262514L;

    public static final String TEST_HOST = "localhost:8081";
    public static final String PUBLIC_HOST = "localhost:8082";
    public static final String urlFrontEnd = "http://localhost:8081/";
    public static final HttpMethod httpMethod_GET = HttpMethod.GET;
    public static final HttpMethod httpMethod_POST = HttpMethod.POST;

}
