package com.myFashion.DAO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EditProfileDTO {

    @NotBlank(message = "First Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Second Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String secondName;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(\\+91)?[6-9]\\d{9}$", message = "Invalid Indian mobile number")
    private String phoneNumber;

    @NotNull(message = "email value should not be Null..!")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 60, message = "Age must be below 60")
    private Integer age;

}
