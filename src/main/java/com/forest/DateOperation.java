package com.forest;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;


import java.util.Arrays;
import java.util.Date;

public class DateOperation {

    public static void main(String[] args) {
        DateOperation operation = new DateOperation();

//        operation.insertDocumentSystemDate();
//        operation.insertDocumentCustomDate();
//        operation.selectDocumentDateUseEq();
        operation.selectDocumentDateUseGt();
    }

    public void insertDocumentSystemDate() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        Document doc = new Document();
        doc.put("name", "Greg Brown");
        doc.put("description", "CEO");
        doc.put("sex", "male");
        doc.put("favorite", Arrays.asList(new String[]{"golf, tennis"}));
        doc.put("dob", new Date());

        collection.insertOne(doc);
    }

    public void insertDocumentCustomDate() {

        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        Date date = DateUtil.stringToDate("yyyy-MM-dd HH:mm:ss", "1975-05-01 13:34:23");

        Document doc = new Document();
        doc.put("name", "Mackey Craig");
        doc.put("description", "Senior Engineer");
        doc.put("sex", "male");
        doc.put("favorite", Arrays.asList(new String[]{"reading, tennis"}));
        doc.put("dob", date);

        collection.insertOne(doc);
    }

    public void selectDocumentDateUseEq() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");
        Date date = DateUtil.stringToDate("yyyy-MM-dd HH:mm:ss", "1975-05-01 13:34:23");

        FindIterable iterable = collection.find(Filters.eq("dob", date));

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("name") + "\t" + doc.get("description") + "\t" + doc.get("sex") + "\t" + doc.get("dob"));
        }
    }

    public void selectDocumentDateUseGt() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");
        Date date = DateUtil.stringToDate("yyyy-MM-dd HH:mm:ss", "1985-05-01 13:34:23");

        FindIterable iterable = collection.find(Filters.gt("birth", date));

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("name") + "\t" + doc.get("description") + "\t" + doc.get("sex") + "\t" + doc.get("birth"));
        }
    }
}


