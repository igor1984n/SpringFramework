package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.example.web.dto.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final Logger logger = Logger.getLogger(UserRepository.class);
    private final List<User> usersRepo = new ArrayList<>();


    public List <User>retrieveAll() {
        return new ArrayList<>(usersRepo);
    }

    public void save(User user) {

        if(loginAlreadyUsed(user)){
            logger.info("Cannot sign in! This login is already used");
        }else {
            usersRepo.add(user);
            logger.info("the user has just been signed in: " + user);
        }
    }

    public boolean canAuthenticate(LoginForm loginForm) {

        boolean credentialsValid = usersRepo.stream().anyMatch(user -> user.getLogin().equals(loginForm.getUsername()) &&
                user.getPassword().equals(loginForm.getPassword()));
        if (credentialsValid) {
            logger.info("The user has successfully logged in");
            return true;
        } else {
            logger.info("Provided credentials are not correct");
            return false;
        }
    }

    public boolean loginAlreadyUsed(User user) {
        String login = user.getLogin();
        return retrieveAll().stream()
                .map(User::getLogin)
                .anyMatch(s -> s.equals(login));
    }

}
