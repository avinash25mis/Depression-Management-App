package com.controller;

import com.dao.CommonRepository;
import com.model.DashboardContent;
import com.model.StoredFile;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardContentController {

    @Autowired
    private CommonRepository commonRepository;



    @ResponseBody
    @PostMapping(value = {"/update"})
    public String update(@ModelAttribute DashboardContent content, ModelMap map) {

        Long existingImageIds= null;
        Long newImageIds= content.getFileIdDashBoard();
        StoredFile existingImages= new StoredFile();
        List<Long> deleteList=new ArrayList<>();
        if(content.getId()!=null){
            existingImages=commonRepository.findExistingImageByDashboardId(content.getId());
            if(existingImages!=null){
                existingImageIds=existingImages.getId();
                deleteList.add(existingImageIds);
            }
        }


        if(newImageIds==null){
            if(existingImageIds!=null) {
                content.setFile(null);
                commonRepository.saveOrUpdate(content);
                int deletedCount = commonRepository.deleteStoredFiles(deleteList);
            }
        }else{
            if(existingImageIds==null){
                StoredFile newStoredImages = commonRepository.findById(StoredFile.class,newImageIds);
                content.setFile(newStoredImages);
            }else{
                if(newImageIds.equals(existingImageIds)){
                    content.setFile(existingImages);
                }else {
                    content.setFile(null);
                    commonRepository.saveOrUpdate(content);
                    int deletedCount = commonRepository.deleteStoredFiles(deleteList);
                    StoredFile newStoredImages = commonRepository.findById(StoredFile.class,newImageIds);
                    content.setFile(newStoredImages);
                }
            }
        }


        content=commonRepository.saveOrUpdate(content);

       commonRepository.saveOrUpdate(content);
        return "saved";

    }
}


