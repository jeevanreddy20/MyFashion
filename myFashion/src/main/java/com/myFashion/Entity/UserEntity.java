package com.myFashion.Entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String secondName;
    private String email;
    private int age;
    private String phoneNumber;
    private LocalDateTime creationDateTime = LocalDateTime.now();
    private String password;
    private String passwordKey;


}
