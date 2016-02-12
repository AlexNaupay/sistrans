package com.devteam.sistrans.controllers;

import com.devteam.sistrans.services.ControlAccesoService;
import com.devteam.sistrans.services.UsuarioService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ControlAccesoService controlAccesoService;

    private static Log logger = LogFactory.getLog(FileUploadController.class);

    @RequestMapping(value = "/login",  method = RequestMethod.GET)
    public String printWelcome(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if (username!= null && !username.equals("")){ //Ya estas logueado
            logger.info("Access Login RequestMethod.GET: "+username);
            return "redirect:/";
        }
        return "login"; //Mostrar la pantalla de login ...
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate(); //Inavlidar la sesión
        return "redirect:/login"; //Redirigir a login
    }

}
