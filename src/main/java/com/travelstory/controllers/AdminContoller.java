package com.travelstory.controllers;

import com.travelstory.entity.User;
import com.travelstory.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminContoller {

    @Autowired
    AdminService adminService;

    @PostMapping("addUser")
    public void addUser(@RequestBody User addedUser) {
        adminService.addUser(addedUser);
    }

    @PostMapping("editUser")
    public void editUser(@RequestBody User editedUser) {
        adminService.addUser(editedUser);
    }

    @GetMapping("getUser/{id}")
    public User getUser(@PathVariable(value = "id") long id) {
        return adminService.getUserById(id);
    }

    @GetMapping("deleteUser/{id}")
    public void deleteUser(@PathVariable(value = "id") long id) {
        adminService.deleteUser(id);
    }

    @GetMapping("markDeletedUser/{id}")
    public void markDeletedUser(@PathVariable(value = "id") long id) {
        adminService.markAsDeleted(id);
    }

    @GetMapping("markActiveUser/{id}")
    public void markActiveUser(@PathVariable(value = "id") long id) {
        adminService.markAsActive(id);
    }

    @GetMapping("markBannedUser/{id}")
    public void markBannedUser(@PathVariable(value = "id") long id) {
        adminService.markAsBanned(id);
    }

    @GetMapping("setAdminStatus/{id}")
    public void setAdminStatus(@PathVariable(value = "id") long id) {
        adminService.setAdminStatus(id);
    }

    @GetMapping("setUserStatus/{id}")
    public void setUserStatus(@PathVariable(value = "id") long id) {
        adminService.setUserStatus(id);
    }

    @GetMapping("deleteComment/{id}")
    public void deleteComment(@PathVariable(value = "id") long id) {
        adminService.deleteComment(id);
    }

    @GetMapping("deleteTravelStory/{id}")
    public void deleteTravelStory(@PathVariable(value = "id") long id) {
        adminService.deleteTravelStory(id);
    }
}
