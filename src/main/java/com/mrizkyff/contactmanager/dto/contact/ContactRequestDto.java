package com.mrizkyff.contactmanager.dto.contact;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ContactRequestDto {
    @NotBlank (message = "Name is required")
    @Size (min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Email (message = "Email is not valid")
    private String email;

    private String phone;

    private String username;

    private String password;
}
