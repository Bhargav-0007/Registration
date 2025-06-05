package com.billspltr.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String groupName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users groupOwner;

    public Group(long id, String name, String description, Users groupOwner) {
        this.id = id;
        this.groupName = name;
        this.description = description;
        this.groupOwner = groupOwner;
    }
    public Group() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String name) {
        this.groupName = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getGroupOwner() {
        return groupOwner;
    }

    public void setGroupOwner(Users groupOwner) {
        this.groupOwner = groupOwner;
    }
}
