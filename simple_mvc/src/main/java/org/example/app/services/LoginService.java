package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final Logger logger = Logger.getLogger(LoginService.class);
    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(LoginForm loginForm){
        logger.info("Try authenticate with user-form: " + loginForm);
        return canAuthenticate(loginForm);
    }

    public boolean canAuthenticate(LoginForm loginForm) {

        boolean credentialsValid = userRepository.retrieveAll()
                .stream().anyMatch(user -> user.getLogin().equals(loginForm.getUsername()) &&
                        user.getPassword().equals(loginForm.getPassword()));
        if (credentialsValid) {
            logger.info("The user has successfully logged in");
            return true;
        } else {
            logger.info("Provided credentials are not correct");
            return false;
        }
    }
}
