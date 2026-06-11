package com.demo.gigforce.repository;

import com.demo.gigforce.entity.User;
import com.demo.gigforce.enums.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findByApprovalStatus(ApprovalStatus approvalStatus);

}