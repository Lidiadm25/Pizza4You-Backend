package com.proyecto_final.Pizza4You.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
	private final String folder = "imagenes_externas";

    public String guardarImagen(MultipartFile archivo) throws IOException {
        if (archivo.isEmpty()) return null;

        String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
        Path path = Paths.get(folder).toAbsolutePath();
        
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        byte[] bytes = archivo.getBytes();
        Path rutaCompleta = Paths.get(path.toString() + File.separator + nombreArchivo);
        Files.write(rutaCompleta, bytes);

        return nombreArchivo;
    }
}
