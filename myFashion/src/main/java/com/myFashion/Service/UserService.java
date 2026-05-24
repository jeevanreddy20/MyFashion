package com.myFashion.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myFashion.DAO.EditProfileDTO;
import com.myFashion.DAO.LoginUser;
import com.myFashion.DAO.SignUpUser;
import com.myFashion.Entity.UserEntity;
import com.myFashion.Repository.UserRepository;

@Service
public class UserService {


    private static final Logger log = LogManager.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> signUp(SignUpUser signUpUser) throws Exception {
        Optional<UserEntity> User = userRepository.findByEmail(signUpUser.getEmail());

        if(User.isPresent()){
            log.error("User already exist.......");
            throw  new Exception("user Already exist");
        }
        else {

            UserEntity user = new UserEntity();
            String passwordEncode = passwordEncoder.encode(signUpUser.getPassword());

            user.setFirstName(signUpUser.getFirstName());
            user.setSecondName(signUpUser.getSecondName());
            user.setEmail(signUpUser.getEmail());
            user.setPhoneNumber(signUpUser.getPhoneNumber());
            user.setPassword(passwordEncode);
            user.setAge(signUpUser.getAge());
            log.info("New user is created");
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body("profile created. happiee shopping...!");
        }
    }
    /* login user */
    public String loginUser( LoginUser loginUser) throws Exception {
        Optional<UserEntity> user = userRepository.findByEmail(loginUser.getEmail());
        if(user.isEmpty()){
            throw new Exception("User not found. please register");
        }
        else{
            UserEntity userDetails = user.get();
            if(passwordEncoder.matches(loginUser.getPassword(),userDetails.getPassword())){
                return "welcome to home page";
            }
            else {
                throw new Exception("Invalid credentials, please try again");
            }
        }
    }


    public Map<String,Object> profile( String email){
        Optional<UserEntity>user = userRepository.findByEmail(email);
        Map<String,Object> userMap = new LinkedHashMap<>();


        if(user.isPresent()){
           UserEntity userDetails = user.get();
           String name = userDetails.getFirstName()+" "+userDetails.getSecondName();
            userMap.put("id",userDetails.getId());
            userMap.put("name",name);
            userMap.put("age",userDetails.getAge());
            userMap.put("email",userDetails.getEmail());
            userMap.put("phone Number",userDetails.getPhoneNumber());

        }
        return userMap;
    }
//edit profile service

    public ResponseEntity<String> editProfile(EditProfileDTO editProfileDTO, String email) throws Exception {
        Optional<UserEntity> user =  userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new Exception("User Not found");
        }
        UserEntity userDetails = user.get();
        if(editProfileDTO.getFirstName()!=null){
            userDetails.setFirstName(editProfileDTO.getFirstName());
        }
        if(editProfileDTO.getSecondName()!=null){
            userDetails.setSecondName(editProfileDTO.getSecondName());
        }
        if(editProfileDTO.getAge()!= null){
            userDetails.setAge(editProfileDTO.getAge());
        }
        if(editProfileDTO.getEmail()!=null){
            userDetails.setEmail(editProfileDTO.getEmail());
        }
        if(editProfileDTO.getPhoneNumber()!=null){
            userDetails.setPhoneNumber(editProfileDTO.getPhoneNumber());
        }
        userRepository.save(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
    }
}
