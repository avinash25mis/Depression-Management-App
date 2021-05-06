package com.controller;

import com.dao.CommonRepository;
import com.model.DayWiseContent;
import com.model.StoredFile;
import org.apache.commons.collections4.CollectionUtils;
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
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/addData")

public class ContentController {

    @Autowired
    private CommonRepository commonRepository;


    @RequestMapping("/upload")
    @ResponseBody
   public Long uploadImage(@RequestParam(name = "file") MultipartFile multipartImage) throws Exception {
        StoredFile file = new StoredFile();
        file.setName(multipartImage.getOriginalFilename());
        file.setContent(new SerialBlob(multipartImage.getBytes()));
        StoredFile storedFile = commonRepository.saveOrUpdate(file);
        return storedFile.getId();
    }


    @RequestMapping("/addDayData")
    @ResponseBody
    public String addDay(@ModelAttribute DayWiseContent dayData)  {

        if(CollectionUtils.isNotEmpty(dayData.getIdList())) {
            List<StoredFile> allStoredFileWithId = commonRepository.getAllStoredFileWithId(dayData.getIdList());
            dayData.setFileList(allStoredFileWithId);
        }
        commonRepository.saveOrUpdate(dayData);
        return "redirect:/viewDays";
    }


    @GetMapping(value = "/getFile/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<byte[]> getFile(@PathVariable(name = "id") Long id, HttpServletResponse response) throws Exception {
            StoredFile storedFile = commonRepository.findById(StoredFile.class,id);

        byte[] imageBytes = null;
        byte[] base64 = null;
        long length = 0;
        String fileName = "";
        if (storedFile!=null) {
            fileName=storedFile.getName();
            imageBytes = storedFile.getContent().getBytes(1,
                    (int) storedFile.getContent().length());

            base64= Base64.getEncoder().encode(imageBytes);
            length=base64.length;
        }


        return ResponseEntity.ok().contentLength(length).body(base64);

    }
}
