package com.uploader.Controller;

import com.uploader.AjaxResponse.AjaxResponseOnSuccessUpload;
import com.uploader.Helpers.CustomMetadataExtractor;
import com.uploader.Model.SongModel;
import com.uploader.Service.FirestoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;


@RestController
public class FirestoreController {

    @Autowired
    private FirestoreService fsService;
    private String songArtist;

    @PostMapping("/store")
    public ResponseEntity<?> uploadFile(@Valid @RequestBody SongModel model, Errors errors) {

        AjaxResponseOnSuccessUpload result = new AjaxResponseOnSuccessUpload();
        if (errors.hasErrors())
        {
            result.setResult(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(" ,")));
            return ResponseEntity.badRequest().body(result);
        }

        if (fsService.addDataToFirestore(model))
        {
            result.setResult("Success");
        }else{
            result.setResult("File not save");
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }
}
