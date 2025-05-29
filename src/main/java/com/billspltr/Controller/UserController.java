package com.billspltr.Controller;

import com.billspltr.Entity.Users;
import com.billspltr.Repo.UserRepo;
import com.billspltr.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;
    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> userData) {
        List<String> errors = new ArrayList<>();

        String name = userData.get("name");
        String email = userData.get("email");
        String password = userData.get("password");

        if (name == null || name.isEmpty()) {
            errors.add("name: Name is required");
        }

        if (email == null || email.isEmpty()) {
            errors.add("email: Email is required");
        } else if (!EMAIL_REGEX.matcher(email).matches()) {
            errors.add("email: Email is not valid");
        }

        if (password == null || password.isEmpty()) {
            errors.add("password: Password is required");
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }
        Users user = new Users();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepo.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

}
