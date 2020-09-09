package com.forest;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Arrays;

public class UpdateDocument {
    public static void main(String[] args) {
        UpdateDocument document = new UpdateDocument();

        document.updateDocumentArray();
    }

    public void updateSingleDocumentSingleKey() {
//        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");
//        Document doc = new Document();
//
//        doc.append("name", "wangwu").append("age", 26).append("desc", "Very good").append("favorite", Arrays.asList(new String[]{"music", "sports"}));
//        collection.insertOne(doc);

        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");
//
        collection.updateOne(Filters.eq("name", "selina"), new Document("$set", new Document("description", "pretty")));
    }

    public void updateSingleDocumentMultiKeys() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        collection.updateOne(Filters.eq("name", "ella"), new Document("$set", new Document("description", "pretty").append("sex", "male")));

    }

    public void updateMutliDocumentSingleKey() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        collection.updateMany(Filters.eq("name", null), new Document("$set", new Document("name", "unkown")));
    }

    public void updateMutliDocumentMultiKey() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        collection.updateMany(Filters.eq("name", "unkown"), new Document("$set", new Document("size", 2222).append("sex", "unkonwn")));
    }

    public void updateDocumentArray() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        collection.updateOne(Filters.eq("username", "lisi"), new Document("$push", new Document("favorite", "Dance")));
    }
}
