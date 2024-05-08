package com.cognicrafters.authservice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PasswordUpdateRequest {
    private String email;
    private String currentPassword;
    private String newPassword;
}
