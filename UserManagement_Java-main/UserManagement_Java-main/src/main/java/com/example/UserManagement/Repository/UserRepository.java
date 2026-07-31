package com.example.UserManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserManagement.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
