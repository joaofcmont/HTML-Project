package com.SwingCalendar;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.MongoBulkWriteException;

@Service
public class ImportJsonService {

    @Autowired
    private static  MongoTemplate mongo;
   

    public static List<Document> generateMongoDocs(List<String> lines) {
        List<Document> docs = new ArrayList<>();
        for (String json : lines) {
            docs.add(Document.parse(json));
        }
        return docs;
    }
    
    private  static int insertInto(String collection, List<Document> mongoDocs) {
        try {
            Collection<Document> inserts = mongo.insert(mongoDocs, collection);
            return inserts.size();
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof MongoBulkWriteException) {
                return ((MongoBulkWriteException) e.getCause())
                  .getWriteResult()
                  .getInsertedCount();
            }
            return 0;
        }
    }
    
    public static String importTo(String collection, List<String> jsonLines) {
        List<Document> mongoDocs = generateMongoDocs(jsonLines);
        System.out.println(mongoDocs);
        int inserts = insertInto(collection, mongoDocs);
        return inserts + "/" + jsonLines.size();
    }
    
    public static List<String> lines(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }
    
    public static List<String> lines(String json) {
        String[] split = json.split("[\\r\\n]+");
        return Arrays.asList(split);
    }

    public static List<String> linesFromResource(String resource) throws IOException {
        Resource input = new ClassPathResource(resource);
        Path path = input.getFile().toPath();
        return Files.readAllLines(path);
    }
}

