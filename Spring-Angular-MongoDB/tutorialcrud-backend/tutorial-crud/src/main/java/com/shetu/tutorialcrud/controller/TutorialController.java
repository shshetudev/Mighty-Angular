package com.shetu.tutorialcrud.controller;

import com.shetu.tutorialcrud.model.Tutorial;
import com.shetu.tutorialcrud.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// todo: Implement the Cross origin
// todo: Know Why we use which annotation
// todo: Implement Factory Pattern and Adapter Pattern in Service Layer
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    private TutorialService tutorialService;
//    @Autowired
//    public TutorialController(TutorialService tutorialService) {
//        this.tutorialService = tutorialService;
//    }

    // *************  Find Operation Starts *******************
    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getTutorials(@RequestParam(required = false) String title){
       return tutorialService.fetchAllTutorials(title);
    }
    //todo: Know why we use @PathVariable instead of @RequestParam
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") String id){
        return tutorialService.fetchOneTutorial(id);
    }

    @GetMapping("/publishedTutorials")
    public ResponseEntity<List<Tutorial>> getPublishedTutorials(){
        return tutorialService.fetchPublishedTutorials();
    }
    // *************  Find Operation Ends *******************
    // Create Operation
    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial){
        return tutorialService.saveTutorial(tutorial);
    }
    // Update Operation
    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") String id,
                                                   @RequestBody Tutorial tutorial){
        return  tutorialService.updateTutorial(id,tutorial);
    }
    // Delete Operation
    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id){
        return tutorialService.deleteTutorial(id);
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials(){
        return tutorialService.deleteAllTutorials();
    }
}
