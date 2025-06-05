package com.billspltr.Controller;

import com.billspltr.Entity.Users;
import com.billspltr.Service.GroupService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/groups")
public class GroupController {
            @Autowired
        private GroupService groupService;

        @PostMapping("/create")
        public ResponseEntity<?> createGroup(@RequestBody Map<String, String> groupData, HttpSession session) {
            Users user = (Users) session.getAttribute("loggedInUser");
            if (user == null) {
                return ResponseEntity.status(401).body("Please log in to create a group");
            }

            Map<String, String> errors = new HashMap<>();

            String groupName = groupData.get("groupName");
            String description = groupData.get("description");

            if (groupName == null || groupName.trim().isEmpty()) {
                errors.put("groupName", "Group name is required");
            }

            if (description == null || description.trim().isEmpty()) {
                errors.put("description", "Description is required");
            }

            if (!errors.isEmpty()) {
                return ResponseEntity.badRequest().body(errors);
            }
                String message = groupService.createGroup(groupName, description, user);
                return ResponseEntity.ok(message);

        }
    }


