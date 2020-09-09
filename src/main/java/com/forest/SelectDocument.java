package com.forest;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.Array;
import java.util.regex.Pattern;

public class SelectDocument {

    public static void main(String[] args) {
        SelectDocument document = new SelectDocument();
        //document.selectAllDocument();
//        document.selectDocumentById();
//        document.selectMultiDocumentByGt();
//        document.selectDocumentByType();
//        document.selectDocumentByIn();
//        document.selectDocumentByNotIn();
//        document.selectDocumentByReg();
//        document.selectDocumentByAnd();
//        document.selectDocumentByOr();
//        document.selectDocumentByAndOr();
        document.selectDocumentBySort();
    }


    public void selectAllDocument() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        FindIterable<Document> iterable = collection.find();

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("name") + "\t" + doc.get("description") + "\t" + doc.get("sex"));
        }
    }

    public void selectDocumentById() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        FindIterable<Document> iterable = collection.find(Filters.eq("_id", new ObjectId("5f465ad60adfeb7d6f617792")));

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("name") + "\t" + doc.get("description") + "\t" + doc.get("sex"));
        }
    }

    public void selectMultiDocumentByGt() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        FindIterable<Document> iterable = collection.find(Filters.gt("size", 500));

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("name") + "\t" + doc.get("description") + "\t" + doc.get("sex"));
        }
    }

    public void selectDocumentByType() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        FindIterable<Document> iterable = collection.find(Filters.type("size", "number"));

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("name") + "\t" + doc.get("description") + "\t" + doc.get("sex") + "\t" + doc.get("size"));
        }
    }

    public void selectDocumentByIn() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        FindIterable<Document> iterable = collection.find(Filters.in("name", "Jet Li", "Jack Chen"));

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("name") + "\t" + doc.get("description") + "\t" + doc.get("sex") + "\t" + doc.get("size"));
        }
    }

    public void selectDocumentByNotIn() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        FindIterable<Document> iterable = collection.find(Filters.nin("name", "Jet Li", "Jack Chen"));

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("name") + "\t" + doc.get("description") + "\t" + doc.get("sex") + "\t" + doc.get("size"));
        }
    }

    public void selectDocumentByReg() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        FindIterable<Document> iterable = collection.find(Filters.regex("name", Pattern.compile("^J")));

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("name") + "\t" + doc.get("description") + "\t" + doc.get("sex") + "\t" + doc.get("size"));
        }
    }

    public void selectDocumentByAnd() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        FindIterable<Document> iterable = collection.find(Filters.and(Filters.eq("name", "unknown"), Filters.ne("size", null)));

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("name") + "\t" + doc.get("description") + "\t" + doc.get("sex") + "\t" + doc.get("size"));
        }
    }

    public void selectDocumentByOr() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        FindIterable<Document> iterable = collection.find(Filters.or(Filters.eq("name", "unknown"), Filters.ne("description", null)));

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("name") + "\t" + doc.get("description") + "\t" + doc.get("sex") + "\t" + doc.get("size"));
        }
    }

    public void selectDocumentByAndOr() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        FindIterable<Document> iterable = collection.find(Filters.or(Filters.eq("name", "unknown"), Filters.and(Filters.ne("description", null), Filters.eq("sex", "male"))));

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("name") + "\t" + doc.get("description") + "\t" + doc.get("sex") + "\t" + doc.get("size"));
        }
    }

    public void selectDocumentBySort() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        FindIterable<Document> iterable = collection.find(Filters.or(Filters.eq("name", "unknown"), Filters.and(Filters.ne("description", null), Filters.eq("sex", "male")))).sort(new Document("name", -1));

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("name") + "\t" + doc.get("description") + "\t" + doc.get("sex") + "\t" + doc.get("size"));
        }
    }
}
