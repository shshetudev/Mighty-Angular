package com.shetu.tutorialcrud.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// todo: Add validation API
@Getter
@Setter
@Document(collection = "tutorials")
public class Tutorial {
    @Id
    private String id;
    private String title;
    private String description;
    private boolean published;
    // todo: Try without adding getters and setters
}
