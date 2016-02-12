package com.devteam.sistrans.rest;

import com.devteam.sistrans.dto.SistransDto;
import com.devteam.sistrans.models.LoginRequest;
import com.devteam.sistrans.services.ControlAccesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Properties;


@Controller
public class LoginControllerApi {

    //Identificador de sistema por defecto
    private static final String SYSTEM_ID_DEFAULT = "sistrans";

    @Autowired
    ControlAccesoService controlAccesoService;

    @Resource(name = "config")
    private Properties properties;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest,HttpServletRequest request){
        HttpSession session = request.getSession();

        String sistema = properties.getProperty("system.id", SYSTEM_ID_DEFAULT);

        SistransDto loginDto = controlAccesoService.login(
                loginRequest.getUsername(), loginRequest.getPassword(), sistema);
        if (loginDto.getErrorCod() == 0){ // Todo está correcto, guardar sesión ...
            loginDto.put("redirect","/");
            session.setAttribute("username",loginRequest.getUsername());
            session.setAttribute("options",loginDto.getData());
            session.setAttribute("system",sistema);
        }
        return new ResponseEntity<>(loginDto, HttpStatus.OK);
    }
}
