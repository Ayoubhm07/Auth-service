package com.cognicrafters.authservice.Controller;

import com.cognicrafters.authservice.Model.*;
import com.cognicrafters.authservice.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(
        origins = "http://localhost:4200",
        allowedHeaders = "*"
)
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

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signuprequest) {
        return loginservice.signup(signuprequest);
    }


    @DeleteMapping("/delete-user")
    public ResponseEntity<Response> deleteUser(@RequestParam String userId, @RequestBody LoginResponse loginResponse) {
        return loginservice.deleteUser(userId, loginResponse);
    }
}
