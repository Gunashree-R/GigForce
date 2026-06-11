package com.demo.gigforce.service;

import com.demo.gigforce.entity.User;
import com.demo.gigforce.enums.ApprovalStatus;
import com.demo.gigforce.enums.UserRole;
import com.demo.gigforce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(User user) {

        //  Auto-approve internal roles
        if (user.getRole() == UserRole.ADMIN ||
                user.getRole() == UserRole.HIRING_MANAGER ||
                user.getRole() == UserRole.VENDOR_MANAGER ||
                user.getRole() == UserRole.FINANCE ) {

            user.setApprovalStatus(ApprovalStatus.APPROVED);

        } else {
            // External users (like VENDOR,CONTRACTOR)
            user.setApprovalStatus(ApprovalStatus.PENDING);
        }

        return userRepository.save(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(updatedUser.getName());
            user.setRole(updatedUser.getRole());
            user.setPhone(updatedUser.getPhone());
            user.setStatus(updatedUser.getStatus());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found: " + id);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateApprovalStatus(Long id, ApprovalStatus status) {


        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //  If REJECTED → delete user from DB
        if (status == ApprovalStatus.REJECTED) {
            userRepository.delete(user);
            return null; //  user deleted
        }

        //  If APPROVED → update normally
        user.setApprovalStatus(status);

        return userRepository.save(user);

    }
    public List<User> getPendingUsers() {
        return userRepository.findByApprovalStatus(ApprovalStatus.PENDING);
    }

}