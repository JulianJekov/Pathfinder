package org.softuni.pathfinder.web;

import org.softuni.pathfinder.model.dto.UserLoginDto;
import org.softuni.pathfinder.model.dto.UserRegisterDTO;
import org.softuni.pathfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(UserLoginDto userLoginDto) {

        final boolean isLogged = this.userService.login(userLoginDto);

        if (isLogged) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(UserRegisterDTO userRegisterDTO) {
        this.userService.register(userRegisterDTO);
        return new ModelAndView("redirect:login");
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        this.userService.logout();
        return new ModelAndView("redirect:/");
    }
}
