package com.devteam.sistrans.rest;

import com.devteam.sistrans.dto.SistransDto;
import com.devteam.sistrans.services.PlantillaService;
import com.devteam.sistrans.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ReportController {

    @Autowired
    PlantillaService plantillaService;

    @Autowired
    ReporteService reporteService;


    @RequestMapping(value = "/api/reporte1", method = RequestMethod.GET)
    public ResponseEntity<?> reporteOne(@RequestParam Map<String,String> params){
        SistransDto fieldsDto = reporteService.reporteAdquirienteCanal(
                params.get("date_prev"), params.get("date_next"), params.get("adq_filters"),
                params.get("canal_filters")
        );
        return new ResponseEntity<>(fieldsDto,null, HttpStatus.OK);
    }


    @RequestMapping(value = "/api/reporte2", method = RequestMethod.GET)
    public ResponseEntity<SistransDto> reporteTwo(@RequestParam Map<String,String> params){

        SistransDto fieldsDto = reporteService.reportePorAutorizador(
                params.get("date_prev"),params.get("date_next"),params.get("aut_filters")
        );

        return new ResponseEntity<>(fieldsDto,null, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/reporte3", method = RequestMethod.GET)
    public ResponseEntity<?> reporteThree(@RequestParam Map<String,String> params){
        SistransDto fieldsDto = reporteService.reporteDetallado(
                params.get("date_prev"), params.get("date_next"),params.get("aut_filters"),
                params.get("adq_filters"),
                params.get("canal_filters")
        );
        return new ResponseEntity<>(fieldsDto,null, HttpStatus.OK);
    }





    @RequestMapping(value = "/api/autorizadores", method = RequestMethod.GET)
    public ResponseEntity<?> autorizadores(){
        SistransDto autorizadoresDto = reporteService.obtenerAutorizadores();
        return new ResponseEntity<>(autorizadoresDto,null, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/adquirientes", method = RequestMethod.GET)
    public ResponseEntity<?> adquirienetes(){
        SistransDto adquirienetesDto = reporteService.obtenerAdquirientes();
        return new ResponseEntity<>(adquirienetesDto,null, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/canales", method = RequestMethod.GET)
    public ResponseEntity<?> canales(){
        SistransDto canalesDto = reporteService.obtenerCanales();
        return new ResponseEntity<>(canalesDto,null, HttpStatus.OK);
    }
}
