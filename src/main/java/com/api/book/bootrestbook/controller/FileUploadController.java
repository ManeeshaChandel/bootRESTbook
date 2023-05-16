package com.api.book.bootrestbook.controller;

import com.api.book.bootrestbook.helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController //bcz we  are creating rest API
public class FileUploadController {
    //ResponseEntity will show things in "postman"
    @Autowired
    private FileUploadHelper fileUploadHelper;
    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("profile")MultipartFile file){ //datatype used for file handling is MultipartFile


//       you will see all these in console only
//        System.out.println(file.getOriginalFilename()); //IMG20211106164320.jpg
//        System.out.println(file.getSize()); //3108049  returning size in bytes
//        System.out.println(file.getContentType());  //image/jpeg
//        System.out.println(file.getName()); //name we have given as key in postman //profile
        try{
            //validation
            if(file.isEmpty()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request Must Contain File");
            }
            if(!file.getContentType().equals("image/jpeg")){
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Only JPEG content type are allowed");
            }

            //file upload code
            //we will make helper class for coding of file upload
            boolean f=fileUploadHelper.uploadFile(file);
            if(f){
                //return ResponseEntity.ok("File is successfully uploaded"); //ok is for showing 200 OK in postman  // working will show response on postman
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
                //ServletUriComponentsBuilder.fromCurrentContextPath() will provide context path //path("/image") this will add image path and then file name

                //creating path for giving path as a response
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong! Try Again");

    }

}
