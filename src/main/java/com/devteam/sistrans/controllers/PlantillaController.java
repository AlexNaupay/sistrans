package com.devteam.sistrans.controllers;

import com.devteam.sistrans.dto.SistransDto;
import com.devteam.sistrans.entities.Campo;
import com.devteam.sistrans.services.PlantillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PlantillaController {

    @Autowired
    PlantillaService plantillaService;

    @RequestMapping("/plantilla")
    public String plantilla(){
        return "plantilla";
    }

    @RequestMapping(value = "/plantilla/campos", method = RequestMethod.GET)
    public ResponseEntity<SistransDto> campos(){
        SistransDto fieldsDto = plantillaService.obtenerCampos();
        return new ResponseEntity<>(fieldsDto,null, HttpStatus.OK);
    }

    @RequestMapping(value = "/plantilla/campos", method = RequestMethod.PUT)
    public ResponseEntity<SistransDto> editarCampos(@RequestBody List<Campo> campos){

        SistransDto dataDto = plantillaService.actualizarCampos(campos);

        return new ResponseEntity<>(dataDto,null, HttpStatus.OK);
    }

}
