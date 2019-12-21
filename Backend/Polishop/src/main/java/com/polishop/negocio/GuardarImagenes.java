package com.polishop.negocio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class GuardarImagenes {

	public String cargaArchivos(MultipartFile eventImage, String upload_folder, String nombreImagen) {
		try {
			return subirArchivos(eventImage, upload_folder, nombreImagen);
		} catch (IOException e) {
			System.err.println(e);
			return "fail";
		}
	}

	private String subirArchivos(MultipartFile eventImage, String upload_folder, String nombreImagen) throws IOException {
		if(!eventImage.isEmpty()) {
			byte[] bytes= eventImage.getBytes();
			Path path = Paths.get(upload_folder + (nombreImagen + ".png"));
			Files.write(path, bytes);
		}
		return upload_folder + (nombreImagen + ".png");
	}
}
