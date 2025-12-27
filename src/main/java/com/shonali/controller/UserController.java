package com.shonali.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shonali.model.User;
import com.shonali.service.UserService;

/* *
 * Controller class to manage User-Related APIs.
 *
 * This controller handles all endpoints related to user operations such as:
 * -Create or Update users
 * -Fetch user details
 * -Deleting users
 *
 * Author: Sonali Koli
 * Date: 27-12-2025
 */

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Create new User.
     *
     * @param user the userdata to save
     * @return ResponseEntity containing either:
     * -user if user saved successfully
     * -Error message(String ) if user not saved successfully
     * @apiNote ResponseEntity<?> is used here because the response body
     * can be of different types depending on success or failure.
     */
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User user) {

        User saveUser = userService.saveUser(user);
        log.info("user Request accepted with id: {}", user.getId());

        if (ObjectUtils.isEmpty(saveUser)) {
            return new ResponseEntity<>("user not saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<?> getAllUser() {
        List<User> allUsers = userService.getAllUser();
        log.info("All users get");

        return new ResponseEntity<>(allUsers, HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        User saveUser = userService.saveUser(user);


        if (ObjectUtils.isEmpty(saveUser)) {
            return new ResponseEntity<>("user not updated", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        log.error("user not found with id: {}", id);
        return new ResponseEntity<>("Delete Successfully!", HttpStatus.OK);
    }

	/**
	 * Create new User.
	 *
	 * @param id the unique identifier of the user
	 * @return ResponseEntity containing either:
	 * -user if user saved successfully
	 * -Error message(String ) if user not saved successfully
	 * @throws UserNotFoundException if no user exists with given id
	 * @apiNote ResponseEntity<?> is used here because the response body
	 * can be of different types depending on success or failure.
	 */
    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        User userById = userService.getUserById(id);
        if (ObjectUtils.isEmpty(userById)) {
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }


}
