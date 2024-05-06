package com.example.minimarketplace.controller;

import com.example.minimarketplace.model.user.LoginRequest;
import com.example.minimarketplace.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-04-01
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

//    @GetMapping("/getUsers")
//    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username) {
//        try {
//            List<User> users = new ArrayList<User>();
//
//            if(username == null) {
//                users.addAll(userRepository.findAll());
//            } else {
//                users.addAll(userRepository.findByUsername(username));
//            }
//
//            if(users.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(users, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    /**
     * @author edvintopa
     * Uses save method available in repo interface.
     *
     * Recieves user obj (JSON) and then checks availability of email and username
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {

        //TODO: Implement verification of bad values, error management

        try {
            /*
             * CHECKS IF USERNAME & EMAIL IS IN USE
             */
            List<User> existingUsers = userRepository.findByUsername(user.getUsername());
            if (!existingUsers.isEmpty()) {
                return new ResponseEntity<>("Username is already in use", HttpStatus.BAD_REQUEST);
            }

            existingUsers = userRepository.findByEmail(user.getEmail());
            if (!existingUsers.isEmpty()) {
                return new ResponseEntity<>("Email is already in use", HttpStatus.BAD_REQUEST);
            }

            /*
             * CREATE USER
             */
            User newUser = userRepository.save(new User(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUsername(),
                    user.getPassword(), //TODO: lookup password storing
                    user.getDateOfBirth(),
                    user.getEmail()

            ));
            return new ResponseEntity<>(newUser.getUserId().toString() ,HttpStatus.CREATED);    //TODO: return auth token
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            //JSON extraction
            String lrUsername = loginRequest.getUsername();
            String lrPassword = loginRequest.getPassword();

            List<User> attemptedUser = userRepository.findByUsername(lrUsername);   //TODO: Check if list is redundant, use single obj instead?
            if (attemptedUser.isEmpty()) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            //compare password with database

            //grant access, return token JWT

            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
