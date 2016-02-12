package com.devteam.sistrans.config;

import com.devteam.sistrans.entities.Opcion;
import com.devteam.sistrans.entities.Usuario;
import com.devteam.sistrans.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AccessInterceptor implements HandlerInterceptor {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // Si no está logueado
        //System.out.println("===Pre-handle: "+request.getRequestURI());
        //System.out.println("Called before the handler execution, returns a boolean value, “true” : continue the handler execution chain; “false”, stop the execution chain and return it.");

        String username = (String) request.getSession().getAttribute("username");
        if (username!= null && !username.equals("")){ //Ya estas logueado
            return true;
        }
        response.sendRedirect("/login");
        return false;

        //return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //System.out.println("Called after the handler execution, allow manipulate the ModelAndView object before render it to view page.");
        System.out.println("===Post-handle: "+request.getRequestURI());

        //Solo llega cuando está logueado
        if (request.getMethod() == "GET"){
            List<Opcion> opciones = (List<Opcion>) request.getSession().getAttribute("options");
            if (opciones != null && modelAndView != null){
                //opciones.forEach(System.out::println);
                modelAndView.getModelMap().addAttribute("options",opciones);
                modelAndView.getModelMap().addAttribute("user",(Usuario)usuarioService.obtenerUsuario(
                                (String)request.getSession().getAttribute("username")).getData()
                );
            }
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("===After completion handle URI: "+request.getRequestURI());
        //System.out.println("Called after the complete request has finished. Seldom use, cant find any use case");
    }
}