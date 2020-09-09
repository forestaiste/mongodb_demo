package com.forest;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ArggergateOper {

    public static void main(String[] args) {

        ArggergateOper arggergateOper = new ArggergateOper();
//        arggergateOper.selectDocumentAggregateCount();
//        arggergateOper.selectDocumentAggregateSize();
//        arggergateOper.selectDocumentAggregateGroupBySum();
//        arggergateOper.selectDocumentAggregateGroupByWhere();
        arggergateOper.selectDocumentAggregateGroupByHaving();
    }

    // db.dev.aggregate([{$group:{_id:null, count: {$sum:1}}}])
    public void selectDocumentAggregateCount() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        Document sum = new Document();

        sum.put("$sum", 1);
        Document count = new Document();
        count.put("_id", null);
        count.put("count", sum);

        Document group = new Document();
        group.put("$group", count);

        List<Document> list = new ArrayList<>();
        list.add(group);

        AggregateIterable iterable = collection.aggregate(list);

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("count"));
        }
    }

    // db.dev.aggregate([{$group:{_id: null, totalSize:{$sum:"$size"}}])
    public void selectDocumentAggregateSize() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        Document sum = new Document();

        sum.put("$sum", "$size");
        Document totalSize = new Document();
        totalSize.put("_id", null);
        totalSize.put("totalSize", sum);

        Document group = new Document();
        group.put("$group", totalSize);

        List<Document> list = new ArrayList<>();
        list.add(group);

        AggregateIterable iterable = collection.aggregate(list);

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("totalSize"));
        }
    }

    //  db.dev.aggregate([{$group:{_id: "$sex", totalSize:{$sum:"$size"}}}])
    public void selectDocumentAggregateGroupBySum() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        Document sum = new Document();

        sum.put("$sum", "$size");
        Document totalSize = new Document();
        totalSize.put("_id", "$sex");
        totalSize.put("totalSize", sum);

        Document group = new Document();
        group.put("$group", totalSize);

        List<Document> list = new ArrayList<>();
        list.add(group);

        AggregateIterable iterable = collection.aggregate(list);

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("totalSize"));
        }
    }

    // db.dev.aggregate([{$match:{size: {$gt:400}}}, {$group:{_id:null, totalSize:{$sum:1}}}])
    public void selectDocumentAggregateGroupByWhere() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        Document gt = new Document();
        gt.put("$gt", 400);

        Document size = new Document();
        size.put("size", gt);

        Document match = new Document();
        match.put("$match", size);

        Document sum = new Document();
        sum.put("$sum", 1);

        Document totalSize = new Document();
        totalSize.put("_id", null);
        totalSize.put("totalSize", sum);

        Document group = new Document();
        group.put("$group", totalSize);

        List<Document> list = new ArrayList<>();
        list.add(match);
        //list.add(group);

        AggregateIterable iterable = collection.aggregate(list);

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("totalSize"));
        }
    }

    // db.dev.aggregate([{$group:{_id:"$name", totalSize:{$sum:"$size"}}}, {$match:{totalSize:{$gt:300}}}])
    public void selectDocumentAggregateGroupByHaving() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        Document sum = new Document();
        sum.put("$sum", "$size");

        Document totalSize = new Document();
        totalSize.put("_id", "$name");
        totalSize.put("totalSize", sum);

        Document group = new Document();
        group.put("$group", totalSize);

        Document gt = new Document();
        gt.put("$gt", 300);

        Document totalSizeInMatch = new Document();
        totalSizeInMatch.put("totalSize", gt);

        Document match = new Document();
        match.put("$match", totalSizeInMatch);

        List<Document> list = new ArrayList<>();
        list.add(group);
        list.add(match);

        AggregateIterable iterable = collection.aggregate(list);

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("_id") + "\t" + doc.get("totalSize"));
        }
    }


}
