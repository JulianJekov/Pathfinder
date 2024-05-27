package org.softuni.pathfinder.web;

import org.softuni.pathfinder.model.dto.user.UserLoginDTO;
import org.softuni.pathfinder.model.dto.user.UserProfileDTO;
import org.softuni.pathfinder.model.dto.user.UserRegisterDTO;
import org.softuni.pathfinder.service.AuthenticationService;
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

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Autowired
    public UserController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(UserRegisterDTO userRegisterDTO) {
        this.authenticationService.register(userRegisterDTO);
        return new ModelAndView("redirect:login");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(UserLoginDTO userLoginDto) {

        final boolean isLogged = this.authenticationService.login(userLoginDto);

        if (isLogged) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("login");
    }


    @PostMapping("/logout")
    public ModelAndView logout() {
        this.authenticationService.logout();
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView("profile");
        UserProfileDTO profile = this.userService.getUserProfile();
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }
}
