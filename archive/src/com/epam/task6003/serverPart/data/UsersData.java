package com.epam.task6003.serverPart.data;

import com.epam.task6003.serverPart.model.User;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.util.ArrayList;

@XmlType(name = "usersData")
@XmlRootElement
public class UsersData {
    private ArrayList<User> users;

    public UsersData() {
    }

    public UsersData(ArrayList<User> users) {
        this.users = users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
