package com.mrizkyff.contactmanager.dto.contact;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ContactResponseDto {
    private UUID id;

    private String name;

    private String email;

    private String phone;

    private String username;

}
