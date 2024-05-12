package com.example.minimarketplace.controller;

import com.example.minimarketplace.auth.JwtUtil;
import com.example.minimarketplace.model.user.request.LoginRequest;
import com.example.minimarketplace.model.user.User;
import com.example.minimarketplace.model.user.response.ErrorResponse;
import com.example.minimarketplace.model.user.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

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

    private final AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;
    public UserController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

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
            User existingUser = userRepository.findByUsername(user.getUsername());
            if (existingUser != null) {
                return new ResponseEntity<>("Username is already in use", HttpStatus.BAD_REQUEST);
            }

            existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser != null) {
                return new ResponseEntity<>("Email is already in use", HttpStatus.BAD_REQUEST);
            }

            /*
             * CREATE USER
             */
            User newUser = userRepository.save(new User(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUsername(),
                    BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()),    //hash with salt
                    user.getDateOfBirth(),
                    user.getEmail()

            ));
            return new ResponseEntity<>(newUser.getUserId().toString() ,HttpStatus.CREATED);    //TODO: return auth token
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginRequest loginRequest)  {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            String username = authentication.getName();
            User user = new User(username,"");
            String token = jwtUtil.createToken(user);
            LoginResponse loginRes = new LoginResponse(username,token);

            return ResponseEntity.ok(loginRes);

        }catch (BadCredentialsException e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
