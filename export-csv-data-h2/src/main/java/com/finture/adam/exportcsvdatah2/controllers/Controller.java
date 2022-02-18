package com.finture.adam.exportcsvdatah2.controllers;

import com.finture.adam.exportcsvdatah2.entity.TagRepository;
import com.finture.adam.exportcsvdatah2.entity.Tags;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
public class Controller {

    @Autowired
    TagRepository repository;

    @GetMapping(path = "/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping(path = "/postFile")
    public ResponseEntity postFile(@RequestParam("file") MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        CsvParserSettings csvParserSetting = new CsvParserSettings();
        csvParserSetting.setHeaderExtractionEnabled(true);
        CsvParser csvParser = new CsvParser(csvParserSetting);
        var parseAllRecords = csvParser.parseAllRecords(inputStream);
        int i = 0;
        parseAllRecords.forEach(record -> {
            Tags tags = new Tags();
            tags.setUserId(Integer.parseInt(record.getString("userId")));
            tags.setMovieId(Integer.parseInt(record.getString("movieId")));
            tags.setTag(record.getString("tag"));
            tags.setTimestampValue(record.getString("timestamp")); // more timestamp handling
            try {
                repository.save(tags);
            } catch (Exception e) {
                System.out.println("Saving error" + tags.getUserId() + " " + tags.getMovieId() + " " + tags.getTag()); // logger
            }
        });
        return ResponseEntity.ok().build();
    }

}
