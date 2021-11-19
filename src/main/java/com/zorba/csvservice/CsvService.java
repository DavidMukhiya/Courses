package com.zorba.csvservice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zorba.csvhelper.CsvHelper;
import com.zorba.entitiy.Courses;
import com.zorba.repository.CoursesRepository;


//mysql to database
@Service
public class CsvService {
  @Autowired
  CoursesRepository coursesRepository;

  public void save(MultipartFile file) {
    try {
      List<Courses> courselist = CsvHelper.csvToTutorials(file.getInputStream());
      coursesRepository.saveAll(courselist);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<Courses> tutorials = coursesRepository.findAll();

    ByteArrayInputStream in = CsvHelper.tutorialsToCSV(tutorials);
    return in;
  }

  public List<Courses> getAllCoursesList() {
    return coursesRepository.findAll();
  }
}