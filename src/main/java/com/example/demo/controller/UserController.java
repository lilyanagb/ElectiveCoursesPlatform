package com.example.demo.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.example.demo.model.User;

// import java.util.List;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {

//     @Autowired
//     private UserService userService;

//     @GetMapping
//     public ResponseEntity<List<User>> getAllUsers() {
//         List<User> users = userService.getAllUsers();
//         return new ResponseEntity<>(users, HttpStatus.OK);
//     }

//     @GetMapping("/{userId}")
//     public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
//         User user = userService.getUserById(userId);
//         return new ResponseEntity<>(user, HttpStatus.OK);
//     }

//     @PostMapping
//     public ResponseEntity<User> saveUser(@RequestBody User user) {
//         User savedUser = userService.saveUser(user);
//         return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//     }

//     @DeleteMapping("/{userId}")
//     public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
//         userService.deleteUser(userId);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     }
// }