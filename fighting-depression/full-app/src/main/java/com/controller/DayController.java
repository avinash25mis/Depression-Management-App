package com.controller;

import com.dao.CommonRepository;
import com.model.DayWiseContent;
import com.model.StoredFile;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/addData")

public class DayController {

    @Autowired
    private CommonRepository commonRepository;


    @RequestMapping("/upload")
    @ResponseBody
   public Long uploadImage(@RequestParam(name = "file") MultipartFile multipartImage) throws Exception {
        StoredFile file = new StoredFile();
        file.setName(multipartImage.getOriginalFilename());
        byte[] base64= compressImages(multipartImage);
        file.setContent(new SerialBlob(base64));
        StoredFile storedFile = commonRepository.saveOrUpdate(file);
        return storedFile.getId();
    }


    @RequestMapping("/addDayData")
    @ResponseBody
    public Long addDay(@ModelAttribute DayWiseContent dayData)  {

        if(dayData.getId()!=null){
            List<Long> existigIdList=commonRepository.getAllStoredFileIds(dayData.getId());
            if(CollectionUtils.isNotEmpty(existigIdList)) {
                //int deletedCount = commonRepository.deleteStoredFiles(existigIdList);
            }
         }

        if(CollectionUtils.isNotEmpty(dayData.getDocList())) {
            List<StoredFile> allStoredFileWithId = commonRepository.getAllStoredFileWithId(dayData.getDocList());
            dayData.setFileList(allStoredFileWithId);

        }
        dayData=commonRepository.saveOrUpdate(dayData);
        return dayData.getId();
    }


    @GetMapping(value = "/getFile/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<byte[]> getFile(@PathVariable(name = "id") Long id, HttpServletResponse response) throws Exception {
            StoredFile storedFile = commonRepository.findById(StoredFile.class,id);
        byte[] base64 = null;
        //byte[] base64 = null;
        long length = 0;
        String fileName = "";
        if (storedFile!=null && storedFile.getContent()!=null) {
            fileName=storedFile.getName();
            base64 = storedFile.getContent().getBytes(1, (int) storedFile.getContent().length());
           // base64= Base64.getEncoder().encode(imageBytes);
            length=base64.length;
        }


        return ResponseEntity.ok().contentLength(length).body(base64);

    }



    public  byte[] compressImages(MultipartFile image) throws IOException {
        String originalImageName = image.getOriginalFilename();
        byte[] base64=null;
        //suffix
        String imageSuffix = originalImageName.substring(originalImageName.lastIndexOf(".") + 1);
        if (!(imageSuffix.equalsIgnoreCase("jpg") || imageSuffix.equalsIgnoreCase("png") || imageSuffix.equalsIgnoreCase("jpeg"))) {
            return null;
        }

        String fileName = originalImageName+RandomUtils.nextInt() + "." + imageSuffix;


        try {
            // Try to compress and save the image first
            ByteArrayOutputStream bOutput = new ByteArrayOutputStream(12);
          //  Thumbnails.of(image.getInputStream()).scale(0.7f).outputQuality(0.25f).toOutputStream(bOutput);
            Thumbnails.of(image.getInputStream()).scale(0.5f).outputQuality(0.20f).toOutputStream(bOutput);
            byte[] bytes = bOutput.toByteArray();
            base64 = Base64.getEncoder().encode(bytes);
            //FileUtils.writeByteArrayToFile(new File(fileName), bytes);

        } catch (Exception e) {
            // Failed to use the way that spring mvc comes with

        }

        return base64;
    }
}
