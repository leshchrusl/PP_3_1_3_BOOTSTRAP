package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserServiceImp userService, RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("alluser", userService.getAllUsers());
        model.addAttribute("userEmail", userService.findUserByUsername(principal.getName()));
        model.addAttribute("newUser", new User());
        return "admin-panel";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }



    @PostMapping("/edit/{id}")
    public String editUser(@ModelAttribute("newUser") User user, @RequestParam("listRoles") ArrayList<Long> roles, @PathVariable("id") long id) {
        user.setRoles(roleService.findRoleById(roles));
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @PostMapping("/new")
    public String addNewUser(@ModelAttribute("newUser") User user,
                             @RequestParam("listRoles") ArrayList<Long> roles){
        user.setRoles(roleService.findRoleById(roles));
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
