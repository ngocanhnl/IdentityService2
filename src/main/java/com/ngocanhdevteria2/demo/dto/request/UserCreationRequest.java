package com.ngocanhdevteria2.demo.dto.request;


import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 2, message = "USERNAME_INVALID")
    String username;
    @Size(min = 2, max = 50, message = "INVALID_PASSWORD")
    String password;
    String firstName;
    String lastName;
    LocalDate birthDate;


}
