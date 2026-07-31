package com.example.UserManagement.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY) 
     private Integer userID;
     private String userName;
     private String role;
     public User() {
     }
     public User(String userName, String role) {
          this.userName = userName;
          this.role = role;
     }
     public Integer getUserID() {
          return userID;
     }
     public String getUserName() {
          return userName;
     }
     public String getRole() {
          return role;
     }
     public void setUserID(Integer userID) {
          this.userID = userID;
     }
     public void setUserName(String userName) {
          this.userName = userName;
     }
     public void setRole(String role) {
          this.role = role;
     }
     @Override
     public String toString() {
          return "User [userID=" + userID + ", userName=" + userName + ", role=" + role + "]";
     }
     
     
     
}
