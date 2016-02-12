package com.devteam.sistrans.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @RequestMapping(value = "/reporte1", method = RequestMethod.GET)
    public String reporte1(){
        return "reporte1";
    }

    @RequestMapping(value = "/reporte2", method = RequestMethod.GET)
    public String reporte2(){
        return "reporte2";
    }

    @RequestMapping(value = "/reporte3", method = RequestMethod.GET)
    public String reporte3(){
        return "reporte3";
    }

}
