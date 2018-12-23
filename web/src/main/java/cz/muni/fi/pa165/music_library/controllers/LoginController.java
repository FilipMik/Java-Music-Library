package cz.muni.fi.pa165.music_library.controllers;

import cz.muni.fi.pa165.music_library.dto.UserAuthenticateDto;
import cz.muni.fi.pa165.music_library.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Filip Mik on 23. 12. 2018
 */

@Controller
public class LoginController {

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("login", new UserAuthenticateDto());
        return modelAndView;
    }
}
