package com.myFashion.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myFashion.DAO.EditProfileDTO;
import com.myFashion.DAO.LoginUser;
import com.myFashion.DAO.SignUpUser;
import com.myFashion.Repository.UserRepository;
import com.myFashion.Service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserRepository userRepository) {
    }


    @PostMapping("/login")
    public String loginUser(@RequestBody LoginUser loginUser) throws Exception {
        return userService.loginUser(loginUser);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpUser signUpUser) throws Exception {
        return userService.signUp(signUpUser);

    }

    @GetMapping("/profile")
    public Map<String, Object> profile(@RequestParam String email){
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("res",userService.profile(email));
        return resMap;
    }

    @PostMapping("/editprofile")
    public ResponseEntity<String> editProfile(@RequestBody EditProfileDTO editProfileDTO,@RequestParam String email) throws Exception {
        return userService.editProfile(editProfileDTO,email);
    }

}
