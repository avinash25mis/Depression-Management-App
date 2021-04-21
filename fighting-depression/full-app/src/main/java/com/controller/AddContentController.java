package com.controller;

import com.dao.CommonRepository;
import com.model.DayWiseContent;
import com.model.StoredFile;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.List;

@Controller
@RequestMapping("/addData")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddContentController {

    @Autowired
    private CommonRepository commonRepository;


    @RequestMapping("/upload")
    @ResponseBody
   public Long uploadImage(@RequestParam(name = "file") MultipartFile multipartImage) throws Exception {
        StoredFile file = new StoredFile();
        file.setName(multipartImage.getOriginalFilename());
        file.setContent(multipartImage.getBytes());
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


    @GetMapping(value = "/getFile/{id}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getFile(@PathVariable(name = "id") Long id) throws Exception {
        StoredFile storedFile = commonRepository.findById(StoredFile.class,id);

        //InputStreamResource resource=new InputStreamResource(new FileInputStream(storedFile.getContent())
        return storedFile.getContent();
    }
}
