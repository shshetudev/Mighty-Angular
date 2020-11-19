package com.shetu.tutorialcrud.repository;

import com.shetu.tutorialcrud.model.Tutorial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TutorialsRepository extends MongoRepository<Tutorial,String> {
    List<Tutorial> findByTitleContaining(String title);
    List<Tutorial> findByPublished(boolean isPublished);
}
