package com.projet.evalBtp.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.web.multipart.MultipartFile;

public class Util {

    String FOLDER_IMAGE = "./src/main/resources/static/img/";
    String FOLDER_DATA_CSV = "./src/main/data/csv/";

    
    public void uploadImage(MultipartFile file) throws Exception
    {
        if (file.isEmpty()) {
            throw new Exception("Selectionnez un fichier");
        }

        System.out.println(file.getContentType());
        if (!file.getContentType().startsWith("image")) {
            throw new Exception("Le fichier n'est pas une image");
        }

        byte[] bytes = file.getBytes();
        String folder = FOLDER_IMAGE;
        File repertoire = new File(folder);
        if (!repertoire.exists()) {
            System.out.println("Tsy mi-existe ----------------------");
            boolean creer = repertoire.mkdirs();
            if (!creer) {
                System.out.println("Tsy mety creer");
            }
        }
        Path path = Paths.get(folder + file.getOriginalFilename());
        Files.write(path, bytes);
    }

    public void uploadCsv(MultipartFile file) throws Exception
    {
        if (file.isEmpty()) {
            throw new Exception("Selectionnez un fichier");
        }

        System.out.println(file.getContentType());
        if (!file.getContentType().contains("csv")) {
            throw new Exception("Le fichier n'est pas de type csv");
        }

        byte[] bytes = file.getBytes();
        String folder = FOLDER_DATA_CSV;
        File repertoire = new File(folder);
        if (!repertoire.exists()) {
            System.out.println("Tsy mi-existe ----------------------");
            boolean creer = repertoire.mkdirs();
            if (!creer) {
                System.out.println("Tsy mety creer");
            }
        }
        Path path = Paths.get(folder + file.getOriginalFilename());
        Files.write(path, bytes);
    }

    public String capitalize(String str)
    {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static LocalDate castDate(String dateString) throws Exception
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate date = LocalDate.parse(dateString, formatter);

        return date;
    }

}
