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

    public ResponseEntity<?> addUser(Users user) {
        Optional<Users> existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already registered");
        }
        userRepo.save(user);
        return ResponseEntity.ok(" Registered Successfully");
    }
}
