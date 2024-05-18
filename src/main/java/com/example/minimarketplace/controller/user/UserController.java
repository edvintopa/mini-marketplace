package com.example.minimarketplace.controller.user;

import com.example.minimarketplace.auth.JwtUtil;
import com.example.minimarketplace.model.communication.request.user.SetInterestRequest;
import com.example.minimarketplace.model.notification.Notification;
import com.example.minimarketplace.model.user.UserInterest;
import com.example.minimarketplace.repository.user.NotificationRepository;
import com.example.minimarketplace.repository.user.UserInterestRepository;
import com.example.minimarketplace.repository.user.UserRepository;
import com.example.minimarketplace.model.communication.request.user.LoginRequest;
import com.example.minimarketplace.model.user.User;
import com.example.minimarketplace.model.communication.response.ErrorResponse;
import com.example.minimarketplace.model.communication.response.user.LoginResponse;
import com.example.minimarketplace.model.communication.response.user.UserResponse;
import com.example.minimarketplace.service.TokenResolverService;
import io.jsonwebtoken.JwtException;
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
import java.util.UUID;

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

    @Autowired
    UserInterestRepository userInterestRepository;

    @Autowired
    NotificationRepository notificationRepository;

    private final AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    private final TokenResolverService tokenResolverService;
    public UserController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, TokenResolverService tokenResolverService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.tokenResolverService = tokenResolverService;
    }

    //http://localhost:8080/user/get
    // test
    /*TO BE REMOVED*/
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
     * This method is responsible for fetching the user's information based on the provided JWT token.
     * The token is passed in the request header under the key "Authorization".
     * The method first extracts the username from the token and then fetches the user's information from the database.
     * It then creates a UserResponse object containing the necessary user information and returns it.
     * <p>
     * If the token is invalid, a JwtException is thrown and caught, and an ErrorResponse with the message "Invalid token" is returned with a status of UNAUTHORIZED.
     * If any other exception occurs during the process, an ErrorResponse with the exception message is returned with a status of INTERNAL_SERVER_ERROR.
     *
     * @param token This is a request header parameter that contains the JWT token.
     * @return ResponseEntity This returns a response entity that contains either a UserResponse object (in case of successful operation) or an ErrorResponse object (in case of an exception).
     * @throws JwtException This exception is thrown when the provided token is invalid.
     * @throws Exception This is a general exception that can be thrown for various reasons.
     * <p>
     * @author edvintopa
     */
    @GetMapping("/me")
    public ResponseEntity getUser(@RequestHeader("Authorization") String token) {
        try {
            UUID userId = tokenResolverService.resolveTokenToUserId(token);
            User user = userRepository.findByUserId(userId);

            UserResponse userResponse = new UserResponse(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getBalance()
            );

            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid token"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }


    /**
     * This method handles the registration of a new user.
     * It first checks if the provided username and email are already in use.
     * If either the username or email is in use, it returns a BAD_REQUEST status with an appropriate message.
     * <p>
     * If both the username and email are not in use, it creates a new User object with the provided details,
     * hashes the provided password using BCrypt, and saves the new user in the repository.
     * It then returns a CREATED status with a token of the newly created user, which essentially logs in automatically.
     * If any exception occurs during the process, it returns an INTERNAL_SERVER_ERROR status.
     *
     * @param user This is a request body parameter that contains the new user's details.
     * @return ResponseEntity This returns a response entity that contains either the ID of the newly created user (in case of successful registration) or a BAD_REQUEST/INTERNAL_SERVER_ERROR status (in case of failed registration or any other exception).
     * @throws Exception This is a general exception that can be thrown for various reasons.
     * <p>
     * @author edvintopa
     */
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) {

        //TODO: Implement verification of bad values, error management

        try {
            /*
             * CHECKS IF USERNAME & EMAIL IS IN USE
             */
            User existingUser = userRepository.findByUsername(user.getUsername());
            if (existingUser != null) {
                ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Username is already in use");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

            existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser != null) {
                ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Email is already in use");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
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

            //autheticate user after registration, automatic login
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(newUser.getUsername(), user.getPassword()));

            String token = jwtUtil.createToken(newUser);

            LoginResponse loginResponse = new LoginResponse(newUser.getUsername(), token);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(loginResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
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
     * <p>
     * @author edvintopa
     */
    @PostMapping("/login")
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
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/setinterests")
    public ResponseEntity setUserInterests(@RequestHeader("Authorization") String token, @RequestBody SetInterestRequest request) {
        try {
            UUID userId = tokenResolverService.resolveTokenToUserId(token);

            for (String interest : request.getInterests()) {
                userInterestRepository.save(new UserInterest(
                        userId,
                        interest
                ));
            }

            return ResponseEntity.status(HttpStatus.OK).body("Interests saved");
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
        }
    }

    @GetMapping("/notifications")
    ResponseEntity getNotifications(@RequestHeader("Authorization") String token) {
        try {
            UUID userId = tokenResolverService.resolveTokenToUserId(token);
            List<Notification> notifications = notificationRepository.findByUserId(userId);

            return ResponseEntity.status(HttpStatus.OK).body(notifications);

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
        }
    }
}
