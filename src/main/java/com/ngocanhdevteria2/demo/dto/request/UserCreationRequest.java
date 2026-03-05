package com.ngocanhdevteria2.demo.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

import com.ngocanhdevteria2.demo.validator.BirthConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 6, message = "USERNAME_INVALID")
    String username;

    @Size(min = 6, max = 50, message = "INVALID_PASSWORD")
    String password;

    String firstName;
    String lastName;

    @BirthConstraint(min = 16, message = "INVALID_BIRTHDATE")
    LocalDate birthDate;
}
