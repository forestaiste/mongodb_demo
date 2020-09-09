package com.forest;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Arrays;

public class InsertDocument {

    public static void main(String[] args) {
        InsertDocument doc = new InsertDocument();
        doc.insertSingleDocument();
    }

    public void insertSingleDocument() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "trest");

        Document doc = new Document();

        doc.append("username", "lisi").append("age", 26).append("desc", "Very good").append("favorite", Arrays.asList(new String[]{"music", "sports"}));
        collection.insertOne(doc);

    }
}
