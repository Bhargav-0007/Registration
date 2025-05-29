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
import java.util.HashMap;
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

    //new User Registration
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> userData) {
        Map<String, String> errors = new HashMap<>();

        String name = userData.get("name");
        String email = userData.get("email");
        String password = userData.get("password");

        if (name == null || name.isEmpty()) {
            errors.put("name", "required");
        }

        if (email == null || email.isEmpty()) {
            errors.put("email", "required");
        } else if (!EMAIL_REGEX.matcher(email).matches()) {
            errors.put("email", "Email is not valid");
        }

        if (password == null || password.isEmpty()) {
            errors.put("password", "required");
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }
        Users user = new Users();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
       return userService.addUser(user);
    }

    //User Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> userLogin) {
        String email = userLogin.get("email");
        String password = userLogin.get("password");
        Map<String, String> errors = new HashMap<>();
        if (email == null || email.isEmpty()) {
            errors.put("email", "Email is required");
        }
        if (password == null || password.isEmpty()) {
            errors.put("password", "Password is required");
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        return userService.signin(email, password);
    }


}
