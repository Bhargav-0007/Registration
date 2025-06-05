package com.billspltr.Service;

import com.billspltr.Entity.Users;
import com.billspltr.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    //new user registration
    public ResponseEntity<?> addUser(Users user) {
        Optional<Users> existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already registered - "+user.getEmail());
        }
        userRepo.save(user);
        return ResponseEntity.ok("User Registered Successfully - "+ user.getName());
    }


    //User Login
    public ResponseEntity<?> signin(String email, String password) {
        Optional<Users> existingUser = userRepo.findByEmail(email);
        if (existingUser.isPresent()) {
            Users user = existingUser.get();
            if (user.getPassword().equals(password)) {
                return ResponseEntity.ok(user);
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found - "+ email );
        }
    }
}
