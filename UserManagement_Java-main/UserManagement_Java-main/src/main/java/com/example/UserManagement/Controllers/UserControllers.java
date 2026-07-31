package com.example.UserManagement.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserManagement.Entity.User;
import com.example.UserManagement.Repository.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class UserControllers {
     @Autowired
     UserRepository repo;
     // post 
     @PostMapping("/users")
     public String createUser(@RequestBody User user){
          repo.save(user);
          return "Created new Users Successfully";
     }
     // get request
     @GetMapping("/users")
     public Object getAllUsers(){
          List<User> users=repo.findAll();
          if(users.isEmpty()){
               return "users table is empty";
          }
          return users;
     }
     @GetMapping("/users/{id}")
     public User getUserById(@PathVariable int id){
          return repo.findById(id)
          .orElseThrow(() -> new RuntimeException("user doesn't found with id:"+id));
     }
     // delete user
     @DeleteMapping("/users/{id}")
     public String deleteUser(@PathVariable int id){
          repo.deleteById(id);
          return "User deleted successfully with id:"+id;
     }
     @PutMapping("/users/{id}")
     public String updateUser(@PathVariable int id,@RequestBody User updateUser){
          return repo.findById(id)
          .map(user -> {
               user.setUserName(updateUser.getUserName());
               user.setRole(updateUser.getRole());
               repo.save(user);
               return "Updated data is successfully with id:"+id ;
          })
          .orElseThrow(() -> new RuntimeException("user doesn't with id:"));

     }
    

}
