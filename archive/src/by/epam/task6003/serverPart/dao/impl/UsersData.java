package by.epam.task6003.serverPart.dao.impl;

import by.epam.task6003.serverPart.bean.User;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.util.List;

@XmlType(name = "usersData")
@XmlRootElement
public class UsersData {
    private List<User> users;

    public UsersData() {
    }

    public UsersData(List<User> users) {
        this.users = users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
