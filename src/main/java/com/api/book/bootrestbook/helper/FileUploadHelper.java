package com.api.book.bootrestbook.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component //so that spring container will handle it's lifecycle automatically
public class FileUploadHelper {
    //public final String UPLOAD_DIR="C:\\Users\\prude\\IdeaProjects\\bootRESTbook\\src\\main\\resources\\static\\image";
    public final String UPLOAD_DIR=new ClassPathResource("static/image/").getFile().getAbsolutePath();//BCZ classPathResource will return object so we are converting it to the path
    //in above line we are making dynamic resource path so that it will work on different computer as well
    //this will save images in target folder only and not in resource folder
    public FileUploadHelper() throws IOException {
        //because getFile() above can throw IOException
        //so you need to handle it int the constructor only
    }

    public boolean uploadFile(MultipartFile multipartFile){
        boolean f=false;
        try{
//            InputStream is=multipartFile.getInputStream();
//            byte data[]=new byte[is.available()]; //is.available() will make the array of size "is"
//            is.read(data);  //InputStream data will save in data  array of type byte
//
//            //write
//            FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+ File.separator+multipartFile.getOriginalFilename());
//            fos.write(data);
//
//            fos.flush();
//            fos.close();

            //Files.copy(in,target,options)  --format
            Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+ File.separator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            f=true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
}
