package com.shetu.tutorialcrud.service_impl;

import com.shetu.tutorialcrud.model.Tutorial;
import com.shetu.tutorialcrud.repository.TutorialsRepository;
import com.shetu.tutorialcrud.service.ExcelService;
import com.shetu.tutorialcrud.service.TutorialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TutorialServiceImpl implements TutorialService {
    @Autowired
    private TutorialsRepository tutorialRepository;
    @Autowired
    private ExcelService fileService;
//    @Autowired
//    public TutorialServiceImpl(TutorialRepository tutorialRepository) {
//        this.tutorialRepository = tutorialRepository;
//    }

    @Override
    public ResponseEntity<Tutorial> saveTutorial(Tutorial tutorial) {
        ResponseEntity<Tutorial> responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        // todo: Specify the Exception class
       try{
        Tutorial savedTutorial = tutorialRepository.save(tutorial);
        log.info("Saved Tutorial:{}",savedTutorial);
        responseEntity = new ResponseEntity<>(savedTutorial,HttpStatus.CREATED);
       }catch (NullPointerException e){
//            throw new RuntimeException("Null Object is passed");
           responseEntity = new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
       }catch (Exception e){
           e.printStackTrace();
       }
        return responseEntity;
    }

    @Override
    public ResponseEntity<List<Tutorial>> fetchAllTutorials(String title) {
        ResponseEntity<List<Tutorial>> responseEntity = new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        try {
        List<Tutorial> tutorials = new LinkedList<>();
        if(title == null) tutorialRepository.findAll().forEach(tutorials::add);
        else tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
        log.info("tutorials size:{}",tutorials.size());
        if(!tutorials.isEmpty()) responseEntity = new ResponseEntity<>(tutorials,HttpStatus.OK);
        }catch (Exception e) {
        e.printStackTrace();
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Tutorial> fetchOneTutorial(String id) {
        ResponseEntity<Tutorial> responseEntity = new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        Optional<Tutorial> tutorial = tutorialRepository.findById(id);
        if(tutorial.isPresent()){
            responseEntity = new ResponseEntity<>(tutorial.get(),HttpStatus.OK);
            log.info("tutorials:{}",tutorial.get());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<List<Tutorial>> fetchPublishedTutorials() {
        ResponseEntity<List<Tutorial>> responseEntity = new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        try {
            List<Tutorial> tutorials = new LinkedList<>();
            tutorialRepository.findByPublished(true).forEach(tutorials::add);
            log.info("Published tutorials size:{}",tutorials.size());
            if(!tutorials.isEmpty()) responseEntity = new ResponseEntity<>(tutorials,HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Tutorial> updateTutorial(String id, Tutorial tutorial) {
        ResponseEntity<Tutorial> responseEntity = new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        Optional<Tutorial> optionalTutorial = tutorialRepository.findById(id);
        if(optionalTutorial.isPresent()){
            Tutorial tutorialFetchedFromDB = optionalTutorial.get();
            tutorialFetchedFromDB.setTitle(tutorial.getTitle());
            tutorialFetchedFromDB.setDescription(tutorial.getDescription());
            tutorialFetchedFromDB.setPublished(tutorial.isPublished());
            tutorialFetchedFromDB = tutorialRepository.save(tutorialFetchedFromDB);
            log.info("updated tutorial:{}",tutorialFetchedFromDB);
            responseEntity = new ResponseEntity<>(tutorialFetchedFromDB,HttpStatus.OK);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<HttpStatus> deleteTutorial(String id) {
        ResponseEntity<HttpStatus> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try{
        tutorialRepository.deleteById(id);
        log.info("Tutorial with id:{} is deleted",id);
        responseEntity = new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        ResponseEntity<HttpStatus> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try{
            tutorialRepository.deleteAll();
            log.info("All tutorials are deleted from Database");
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Resource> downloadExcelFileOfAllTutorials() {
//        String filename = "tutorials.xlsx";
        List<Tutorial> tutorials = tutorialRepository.findAll();
        InputStreamResource file = new InputStreamResource(fileService.load(tutorials));
        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
