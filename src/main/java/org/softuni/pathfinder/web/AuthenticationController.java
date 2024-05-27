package org.softuni.pathfinder.web;

import org.softuni.pathfinder.model.dto.UserLoginDTO;
import org.softuni.pathfinder.model.dto.UserRegisterDTO;
import org.softuni.pathfinder.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
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
}