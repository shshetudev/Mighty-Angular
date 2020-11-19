package com.shetu.tutorialcrud.service;

import com.shetu.tutorialcrud.model.Tutorial;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TutorialService {
    ResponseEntity<Tutorial> saveTutorial(Tutorial tutorial);
    ResponseEntity<List<Tutorial>> fetchAllTutorials(String title);
    ResponseEntity<Tutorial> fetchOneTutorial(String id);
    ResponseEntity<List<Tutorial>> fetchPublishedTutorials();
    ResponseEntity<Tutorial> updateTutorial(String id, Tutorial tutorial);
    ResponseEntity<HttpStatus> deleteTutorial(String id);
    ResponseEntity<HttpStatus> deleteAllTutorials();
}
