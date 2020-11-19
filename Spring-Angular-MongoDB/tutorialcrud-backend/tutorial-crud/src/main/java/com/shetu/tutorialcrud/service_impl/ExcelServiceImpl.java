package com.shetu.tutorialcrud.service_impl;

import com.shetu.tutorialcrud.model.ExcelHelper;
import com.shetu.tutorialcrud.model.Tutorial;
import com.shetu.tutorialcrud.repository.TutorialsRepository;
import com.shetu.tutorialcrud.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {
    private TutorialsRepository tutorialsRepository;

    @Autowired
    public ExcelServiceImpl(TutorialsRepository tutorialsRepository) {
        this.tutorialsRepository = tutorialsRepository;
    }

    @Override
    public ByteArrayInputStream load(List<Tutorial> tutorials) {
        ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorials);
        // todo: Comment the below line
        log.info("In:{}",in);
        return in;
    }
}
