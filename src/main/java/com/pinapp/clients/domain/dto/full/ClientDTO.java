package com.pinapp.clients.domain.dto.full;

import com.pinapp.clients.domain.dto.partial.CountryPartialDTO;
import com.pinapp.clients.domain.dto.partial.DocumentTypePartialDTO;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long id;

    @NotBlank(message = "First name is required and cannot be blank.")
    @Size(max = 50, message = "First name cannot exceed 50 characters.")
    private String firstName;

    @NotBlank(message = "Last name is required and cannot be blank.")
    @Size(max = 50, message = "Last name cannot exceed 50 characters.")
    private String lastName;

    @NotBlank(message = "Email address is required.")
    @Email(message = "Email address must be a valid format, e.g., user@example.com.")
    private String email;

    @Pattern(
            regexp = "^\\+?[0-9]{7,15}$",
            message = "Phone number must contain only digits and may start with '+'. Length must be between 7 and 15 digits."
    )
    private String phone;

    @Size(max = 200, message = "Address cannot exceed 200 characters.")
    private String address;

    @Size(max = 100, message = "City name cannot exceed 100 characters.")
    private String city;

    @NotNull(message = "Country id is required.")
    private CountryPartialDTO country;

    @NotNull(message = "DocumentType id is required.")
    private DocumentTypePartialDTO documentType;

    @NotBlank(message = "Document number is required.")
    @Pattern(
            regexp = "^[0-9]{7,10}$",
            message = "Document number must contain only digits and be between 7 and 10 characters long."
    )
    private String documentNumber;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be a date in the past.")
    private LocalDate birthDate;

    private Integer age;

    @PastOrPresent(message = "Registration date cannot be in the future.")
    private LocalDate registrationDate;

    @NotNull(message = "Active status must be specified.")
    private Boolean active;

    @Pattern(
            regexp = "^(Male|Female|Other)$",
            message = "Gender must be one of the following values: Male, Female, or Other."
    )
    private String gender;
}
