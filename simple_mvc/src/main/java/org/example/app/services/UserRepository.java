package org.example.app.services;


import org.example.web.dto.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {


    private final List<User> usersRepo = new ArrayList<>();


    public List<User> retrieveAll() {
        return new ArrayList<>(usersRepo);
    }

    public void save(User user) {
        usersRepo.add(user);
    }
}
