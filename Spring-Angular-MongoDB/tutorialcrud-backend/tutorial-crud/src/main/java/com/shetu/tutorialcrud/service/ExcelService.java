package com.shetu.tutorialcrud.service;

import com.shetu.tutorialcrud.model.Tutorial;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelService {
    ByteArrayInputStream load(List<Tutorial> tutorials);
}
