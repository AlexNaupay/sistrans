package com.devteam.sistrans.services;

import com.devteam.sistrans.dto.SistransDto;
import org.springframework.web.multipart.MultipartFile;

public interface ArchivoService {

    SistransDto procesarArchivo(MultipartFile archivo);


}
