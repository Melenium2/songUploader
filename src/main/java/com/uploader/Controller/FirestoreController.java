package com.uploader.Controller;

import com.uploader.Helpers.CustomMetadataExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@RestController
public class FirestoreController
{
    @Autowired
    private CustomMetadataExtractor mdExtractor;

    @PostMapping("/store")
    public void uploadFile(@RequestParam("filePath") MultipartFile file) throws IOException
    {
        /*if (!file.isEmpty())
        {
            File convFile = new File(file.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            mdExtractor.extractMetadataFromAudio(convFile);
        }
        else
        {
            *//**
             * Exception
             *//*
        }*/
        /**
         * Научится возвращать 404 посмотреть урок пацана
         * Файл создается оставется его закинуть
         * И еще посмтореть как в яве работать с сметоданными
         */
    }
}
