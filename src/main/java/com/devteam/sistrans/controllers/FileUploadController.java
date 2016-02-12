package com.devteam.sistrans.controllers;

import com.devteam.sistrans.dto.SistransDto;
import com.devteam.sistrans.services.ArchivoService;
import com.devteam.sistrans.services.PlantillaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

import java.util.List;
import java.util.Properties;

@Controller
public class FileUploadController {
    @Resource(name = "config")
    Properties properties;

    @Autowired
    ArchivoService archivoService;

    @Autowired
    PlantillaService plantillaService;

    @Autowired
    private Environment env;

    private static Log logger = LogFactory.getLog(FileUploadController.class);

    @PostConstruct
    public void post(){
        System.out.println("=== iniciando === "+this.getClass().getName());
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String fileUploadForm(ModelMap modelMap) throws IOException {
        SistransDto camposDto = plantillaService.obtenerCampos();
        if (camposDto.getErrorCod() == 0){
            modelMap.addAttribute("fields",(List)camposDto.getData());
        }
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<SistransDto> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        logger.info("FileUpload Start >");
        SistransDto sistransDto = archivoService.procesarArchivo(file);
        logger.info("FileUpload Renponse send >>> ");
        return new ResponseEntity<>(sistransDto,null,HttpStatus.OK);
    }

    @RequestMapping("/try")
    public String tryf(){

        /*model.addAttribute("varon", "alexh");
        model.addAttribute("mujer", "kimberly");*/

        System.out.println(env.getProperty("time.input.format"));
        System.out.println(properties.getProperty("time.input.format"));
        return "try";
    }
}
