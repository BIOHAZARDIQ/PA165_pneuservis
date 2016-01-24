/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.mvc.controllers;

import cz.muni.fi.pa165_pneuservis.dto.CustomerDTO;
import cz.muni.fi.pa165_pneuservis.dto.LoginFormDTO;
import cz.muni.fi.pa165_pneuservis.facade.CustomerFacade;
import cz.muni.fi.pa165_pneuservis.facade.PneuFacadeException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Login controller
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private CustomerFacade customerFacade;
    
    @RequestMapping(method = RequestMethod.GET)
    public String loginForm(Model m, HttpServletRequest request) {
        m.addAttribute("login", new LoginFormDTO());
                
        // check if admin is not already authenticated while accessing login form
        HttpSession session = request.getSession(true);
        if(session.getAttribute("auth") != null) {
            return "redirect:/tire/list";
        }
        else return "/login";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String loginSubmit(@ModelAttribute("login") @Valid LoginFormDTO login, 
            BindingResult result, Model m, RedirectAttributes redirectAttributes, 
            UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        
        if (result.hasErrors()) {
            for (FieldError fieldError : result.getFieldErrors()) {
                m.addAttribute(fieldError.getField() + "_error", true);
            }
            return "/login";
        }
        
        try {
            CustomerDTO customer = customerFacade.authenticate(login.getEmail(),
                    login.getPassword());
            request.getSession().setAttribute("auth", customer);
            if(customer.getIsAdmin())
                return "redirect:/tire/list";
            else
                return "redirect:/order/list";
        } catch (PneuFacadeException ex) {
            Logger.getLogger(LoginController.class.getName())
                    .log(Level.WARNING,ex.getMessage());
            m.addAttribute("alert_danger", "Unable to log in. Incorrect email "
                    + "or password.");
            return "/login";
        }
    }
}
