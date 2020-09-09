package com.forest;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectOper {

    public static void main(String[] args) {
        ProjectOper operation = new ProjectOper();
//        operation.selectDocumentProject();
//        operation.selectDocumentConcat();
//        operation.selectDocumentAdd();
        operation.selectDocumentProjectDate();
    }


    // db.dev.aggregate([{$unwind: "$favorite"}, {$project:{_id:0, favorites:"$favorite", name:1}}])
    public void selectDocumentProject() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        Document unwind = new Document();
        unwind.put("$unwind", "$favorite");

        Document projectContent = new Document();
        projectContent.put("_id", 0);
        projectContent.put("favorites", "$favorite");
        projectContent.put("name", 1);

        Document project = new Document();
        project.put("$project", projectContent);

        List<Document> list = new ArrayList<>();
        list.add(unwind);
        list.add(project);

        AggregateIterable iterable = collection.aggregate(list);

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("favorites") + "\t" + doc.get("name"));
        }
    }

    //db.dev.aggregate([{$unwind:"$favorite"}, {$project:{_id:0, Name_Favorites:{$concat:["$name", "-", "$favorite"]}}}])
    public void selectDocumentConcat() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        Document unwind = new Document();
        unwind.put("$unwind", "$favorite");

        Document concat = new Document();
        concat.put("$concat", Arrays.asList(new String[] {"$name", "-", "$favorite"}));

        Document projectContent = new Document();
        projectContent.put("_id", 0);
        projectContent.put("Name_Favorites", concat);


        Document project = new Document();
        project.put("$project", projectContent);

        List<Document> list = new ArrayList<>();
        list.add(unwind);
        list.add(project);

        AggregateIterable iterable = collection.aggregate(list);

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("Name_Favorites") + "\t" + doc.get("name"));
        }
    }

    // db.dev.aggregate([{$match:{size:{$ne:null}}}, {$project:{_id:0, name:1, New_Size:{$add:["$size", 1]}}}])
    // db.dev.aggregate([{$match:{size:{$type:"number"}}}, {$project:{_id:0, name:1, New_Size:{$add:["$size", 1]}}}])
    public void selectDocumentAdd() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        Document ne = new Document();
        ne.put("$ne", null);

        Document size = new Document();
        size.put("size", ne);

        Document match = new Document();
        match.put("$match", size);

        Document add = new Document();
        add.put("$add", Arrays.asList(new Object[] {"$size", 1}));

        Document newSize = new Document();
        newSize.put("_id", 0);
        newSize.put("name", 1);
        newSize.put("New_Size", add);

        Document project = new Document();
        project.put("$project", newSize);

        List<Document> list = new ArrayList<>();
        list.add(match);
        list.add(project);

        AggregateIterable iterable = collection.aggregate(list);

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("New_Size") + "\t" + doc.get("name"));
        }
    }

    // db.dev.aggregate([{$match: {dob: {$ne:null}}}, {$project: {自定义日期格式:{$dateToString:{format:"%Y年%m月%d日 %H:%M:%S", date:"$dob"}}}}])
    public void selectDocumentProjectDate() {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        Document ne = new Document();
        ne.put("$ne", null);

        Document dob = new Document();
        dob.put("dob", ne);

        Document match = new Document();
        match.put("$match", dob);

        Document format = new Document();
        format.put("format", "%Y年%m月%d日 %H:%M:%S");
        format.put("date", "$dob");

        Document dataToString = new Document();
        dataToString.put("$dateToString", format);

        Document custom = new Document();
        custom.put("自定义日期格式", dataToString);

        Document project = new Document();
        project.put("$project", custom);

        List<Document> list = new ArrayList<>();
        list.add(match);
        list.add(project);

        AggregateIterable iterable = collection.aggregate(list);

        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc.get("自定义日期格式"));
        }
    }
}
