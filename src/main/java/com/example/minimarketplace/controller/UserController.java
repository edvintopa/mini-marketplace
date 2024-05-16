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

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-04-01
 */
@CrossOrigin(origins = "*")
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

    //http://localhost:8080/user/get
    @GetMapping("/get")
    public ResponseEntity<User> getAllUsers(@RequestParam String username) {
        try {
            User user = userRepository.findByUsername(username);

            if (user == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method handles the registration of a new user.
     * It first checks if the provided username and email are already in use.
     * If either the username or email is in use, it returns a BAD_REQUEST status with an appropriate message.
     * If both the username and email are not in use, it creates a new User object with the provided details,
     * hashes the provided password using BCrypt, and saves the new user in the repository.
     * It then returns a CREATED status with the ID of the newly created user.
     * If any exception occurs during the process, it returns an INTERNAL_SERVER_ERROR status.
     *
     * @param user This is a request body parameter that contains the new user's details.
     * @return ResponseEntity This returns a response entity that contains either the ID of the newly created user (in case of successful registration) or a BAD_REQUEST/INTERNAL_SERVER_ERROR status (in case of failed registration or any other exception).
     * @throws Exception This is a general exception that can be thrown for various reasons.
     * @author edvintopa
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
            return new ResponseEntity<>(newUser.getUserId().toString() ,HttpStatus.CREATED);    //TODO: return auth token and automatically log in after registration
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method handles the login request. It takes a LoginRequest object as input, which contains the username and password.
     * The method attempts to authenticate the user with the provided credentials. If the authentication is successful, a JWT token is generated for the user.
     * The token along with the username is then wrapped into a LoginResponse object and sent back to the client.
     * <p>
     * If the authentication fails due to bad credentials, an ErrorResponse object is created with a status of BAD_REQUEST and a message indicating invalid username or password.
     * This ErrorResponse object is then sent back to the client.
     * <p>
     * If any other exception occurs during the process, an ErrorResponse object is created with a status of BAD_REQUEST and the exception message.
     * This ErrorResponse object is then sent back to the client.
     *
     * @param loginRequest This is a request body parameter that contains the login credentials (username and password).
     * @return ResponseEntity This returns a response entity that contains either a LoginResponse object (in case of successful authentication) or an ErrorResponse object (in case of failed authentication or any other exception).
     * @throws BadCredentialsException This exception is thrown when the provided credentials are invalid.
     * @throws Exception This is a general exception that can be thrown for various reasons other than bad credentials.
     *
     * @author edvintopa
     */
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
