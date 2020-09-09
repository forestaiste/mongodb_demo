package com.forest;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class MongoDBDemo {

    public static void main(String[] args) {
//        MongoClient client = new MongoClient("127.0.0.1", 27017);
//
//        MongoDatabase database = client.getDatabase("develop");
//
//        MongoCollection collection = database.getCollection("dev");
//        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");
//        System.out.println("OK...............");

        MongoDatabase database = MongoDBAuthUtil.getDatabase("dev");

//        database.createCollection("trest");
//
        MongoCollection collection = MongoDBAuthUtil.getCollection("dev", "trest");
//        MongoDBAuthUtil.dropCollection(collection);
        //System.out.println(collection);

        Document doc = new Document();

        doc.append("username", "lisi").append("age", 26).append("desc", "Very good").append("favorite", Arrays.asList(new String[]{"music", "sports"}));
        collection.insertOne(doc);
    }
}
