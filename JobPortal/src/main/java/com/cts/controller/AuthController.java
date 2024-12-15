package com.cts.controller;





import com.cts.entity.User;
import com.cts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (!user.getRole().equalsIgnoreCase("USER") && !user.getRole().equalsIgnoreCase("EMPLOYER")) {
            return ResponseEntity.badRequest().body("Invalid role! Must be USER or EMPLOYER.");
        }
        return ResponseEntity.ok(userService.registerUser(user));
    }

	/*
	 * @PostMapping("/login") public ResponseEntity<?> loginUser(@RequestBody User
	 * loginDetails) { User user = userService.findByEmail(loginDetails.getEmail());
	 * if (user != null && user.getPassword().equals(loginDetails.getPassword())) {
	 * return ResponseEntity.ok("Login successful"); } return
	 * ResponseEntity.status(401).body("Invalid credentials"); }
	 */
    
 
    

        @Autowired
        private PasswordEncoder passwordEncoder;

        @PostMapping("/login")
        public ResponseEntity<?> loginUser(@RequestBody User loginDetails) {
            // Fetch user from database using email
            User user = userService.findByEmail(loginDetails.getEmail());

            if (user != null) {
                // Log the passwords for debugging
                System.out.println("Raw password from request: " + loginDetails.getPassword());
                System.out.println("Encoded password from DB: " + user.getPassword());

                // Check if raw password matches the encoded password
                if (passwordEncoder.matches(loginDetails.getPassword(), user.getPassword())) {
                    return ResponseEntity.ok("Login successful");
                }
            }
            
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }


