package com.shetu.tutorialcrud.controller;

import com.shetu.tutorialcrud.service.ExcelService;
import com.shetu.tutorialcrud.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/tutorials")
public class ExcelController {
    /*
    * todo: See these websites
    *  https://www.eduforbetterment.com/exporting-data-to-excel-file-using-angular/
    *  https://bezkoder.com/spring-boot-download-excel-file/
    *  https://ducmanhphan.github.io/2019-08-08-How-to-download-excel-file-in-Angular-7/
    * */
    @Autowired
    private TutorialService tutorialService;

    @GetMapping("/excel-download")
    public ResponseEntity<Resource> downloadExcel(){
        return tutorialService.downloadExcelFileOfAllTutorials();
    }
}
