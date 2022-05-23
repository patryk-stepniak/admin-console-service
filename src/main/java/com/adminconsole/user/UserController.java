package com.adminconsole.user;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;

@Transactional
@RestController
class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/users"})
    Map<String, Boolean> getUsers() {
        return this.userService.getUsers();
    }

    @DeleteMapping({"/users/{username}"})
    void deleteUser(@PathVariable String username) {
        this.userService.deleteUser(username);
    }

    @PostMapping({"/users"})
    void addUser(@RequestBody String username) {
        this.userService.addUser(username);
    }

    @PutMapping({"/users/{username}"})
    void setActive(@PathVariable String username, @RequestParam boolean active) {
        this.userService.setActive(username, active);
    }

}
