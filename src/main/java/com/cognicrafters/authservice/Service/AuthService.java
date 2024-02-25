package com.cognicrafters.authservice.Service;

import com.cognicrafters.authservice.Model.LoginRequest;
import com.cognicrafters.authservice.Model.LoginResponse;
import com.cognicrafters.authservice.Model.Response;
import com.cognicrafters.authservice.Model.TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:application.yaml")
public class AuthService {
    private final RestTemplate restTemplate;

    @Autowired
    AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //    @Value("${spring.security.oauth2.client.registration.provider.keycloak.issuer-uri}")
//    private String issuerUrl;
    @Value("${spring.security.oauth2.client.registration.ouath2-client-credentials.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.ouath2-client-credentials.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.ouath2-client-credentials.authorization-grant-type}")
    private String grantType;


    public ResponseEntity<LoginResponse> login(LoginRequest loginrequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("grant_type", grantType);
        map.add("username", loginrequest.getUsername());
        map.add("password", loginrequest.getPassword());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
        LoginResponse response =  restTemplate.postForObject("http://localhost:8080/realms/CogniKids/protocol/openid-connect/token", httpEntity, LoginResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Response> logout (TokenRequest request){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id",clientId);
        map.add("client_secret",clientSecret);
        map.add("refresh_token",request.getToken());


        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(
                "http://localhost:8080/realms/CogniKids/protocol/openid-connect/logout",
                httpEntity,
                Response.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Response response = responseEntity.getBody();
            if (response != null) {
                response.setMessage("Logged out successfully");
            }
        }
        return  responseEntity;
    }
}

