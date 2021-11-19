package com.zorba.csvhelper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.zorba.entitiy.Courses;


public class CsvHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Id", "Course_Name", "Postion", "Duration" };

  public static boolean hasCSVFormat(MultipartFile file) {
    if (TYPE.equals(file.getContentType())
    		|| file.getContentType().equals("application/vnd.ms-excel")) {
      return true;
    }

    return false;
  }

  //csvtomysql
  public static List<Courses> csvToTutorials(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        @SuppressWarnings("deprecation")
		CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Courses> CoursesList = new ArrayList<>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
    	  Courses courses = new Courses(Integer.parseInt(csvRecord.get("ID")), 
    			  csvRecord.get("Course_Name"),
    			  csvRecord.get("Position"), 
    			  csvRecord.get("Duration")
    			);

    	  CoursesList.add(courses);
      }

      return CoursesList;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

  //mysql to csv file data
  public static ByteArrayInputStream tutorialsToCSV(List<Courses> CoursesList) {
    @SuppressWarnings("deprecation")
	final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (Courses Courses : CoursesList) {
        List<String> data = Arrays.asList(
              String.valueOf(Courses.getId()),
              Courses.getCourse_Name(),
              Courses.getPosition(),
              Courses.getDuration()
            );

        csvPrinter.printRecord(data);
      }

      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }
}
