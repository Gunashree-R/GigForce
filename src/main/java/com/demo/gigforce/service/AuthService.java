package com.demo.gigforce.service;

import com.demo.gigforce.dto.auth.AuthResponse;
import com.demo.gigforce.entity.User;
import com.demo.gigforce.dto.auth.AuthRequest;
import com.demo.gigforce.repository.UserRepository;
import com.demo.gigforce.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.demo.gigforce.enums.ApprovalStatus;
import com.demo.gigforce.enums.UserRole;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }


        // Only check approval status (clean design)
        if (user.getApprovalStatus() != ApprovalStatus.APPROVED) {

            if (user.getApprovalStatus() == ApprovalStatus.PENDING) {
                throw new RuntimeException("Approval pending from Admin");
            }

            if (user.getApprovalStatus() == ApprovalStatus.REJECTED) {
                throw new RuntimeException("Your account has been rejected");
            }
        }


        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        return new AuthResponse(token, user.getRole().name(), user.getEmail());
    }


    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.createUser(user);
        //return userRepository.save(user);
    }

}