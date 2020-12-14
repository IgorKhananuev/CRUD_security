
package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;
//import web.service.UserServiceImp;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUsers(Model model){
        model.addAttribute ("list_users",userService.getAllUsers());
        return "/index";
    }

    @GetMapping("/add")
    public String getAddForm(){
        return "add";
    }

    @PostMapping ("/add")
    public String addUser(@ModelAttribute("user") User user){
        userService.add (user);
        return "redirect:/";
    }

    @GetMapping ("/edit/{id}")
    public String getEditForm(@PathVariable("id") Long id, Model model){
        model.addAttribute ("user",userService.getUserById (id));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editUser (@ModelAttribute("user")User user){
        userService.update (user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String getDeleteForm (@PathVariable ("id") Long id,Model model){
        model.addAttribute ("user",userService.getUserById (id));
        return "edit";
    }
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable ("id") Long id,Model model){
        userService.deleteById (id);
        return "redirect:/";
    }





}
