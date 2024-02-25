package com.cognicrafters.authservice.Controller;

import com.cognicrafters.authservice.Model.LoginRequest;
import com.cognicrafters.authservice.Model.LoginResponse;
import com.cognicrafters.authservice.Model.Response;
import com.cognicrafters.authservice.Model.TokenRequest;
import com.cognicrafters.authservice.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthService loginservice;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginrequest) {
        return loginservice.login(loginrequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<Response> logout (@RequestBody TokenRequest token) {

        return loginservice.logout(token);
    }
}
