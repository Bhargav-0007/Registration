package com.billspltr.Service;

import com.billspltr.Entity.Group;
import com.billspltr.Entity.Users;
import com.billspltr.Repo.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupRepo groupRepo;

    public String createGroup(String groupName, String description ,Users user) {
        Group group = new Group();
        group.setGroupName(groupName);
        group.setDescription(description);
        group.setGroupOwner(user);

        groupRepo.save(group);

        return "Group '" + groupName + "' created successfully by " + user.getEmail();

    }
}
