package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepo;
    private final Logger logger = Logger.getLogger(UserRepository.class);

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void addUser(User user) {

        if (loginAlreadyUsed(user)) {
            logger.info("Cannot sign up! This login is already used");
        } else {
            userRepo.save(user);
            logger.info("the user has just been signed up: " + user);
        }
        userRepo.save(user);
    }

    public boolean loginAlreadyUsed(User user) {
        String login = user.getLogin();
        return userRepo.retrieveAll().stream()
                .map(User::getLogin)
                .anyMatch(s -> s.equals(login));
    }
}
