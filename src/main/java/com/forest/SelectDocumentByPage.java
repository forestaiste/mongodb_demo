package com.forest;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

public class SelectDocumentByPage {
    public static void main(String[] args) {
        SelectDocumentByPage selectDocumentByPage = new SelectDocumentByPage();
//        selectDocumentByPage.selectDocumentByPageUseSkipAndLimit(3);
//        selectDocumentByPage.selectDocumentByPageUseSkipAndLimitByCondition(2);
        selectDocumentByPage.selectDocumentByPageUseCondition(3, 2, "5f4ce9113fc495aadde42982");
    }


    //
    public void selectDocumentByPageUseSkipAndLimit(int indexPage) {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        long count = collection.countDocuments();

        System.out.println(count);
        int page = (indexPage - 1) * 2;

        FindIterable iterable = collection.find().skip(page).limit(2);
        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc);
        }
    }

    public void selectDocumentByPageUseSkipAndLimitByCondition(int indexPage) {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        Document condition = new Document("size", new Document("$ne", null));
        long count = collection.countDocuments(condition);

        System.out.println(count);
        int page = (indexPage - 1) * 2;

        FindIterable iterable = collection.find(condition).skip(page).limit(2);
        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc);
        }
    }

    public void selectDocumentByPageUseCondition(int pageIndex, int pageSize, String lastIndex) {
        MongoCollection collection = MongoDBAuthPoolUtil.getCollection("dev", "dev");

        Document condition = new Document("size", new Document("$ne", null));
        long count = collection.countDocuments(condition);

        System.out.println(count);

        FindIterable iterable = null;
        if (pageIndex == 1) {
            iterable = collection.find(condition).limit(pageSize);
        } else {
            if (lastIndex != null) {
                condition.append("_id", new Document("$gt", new ObjectId(lastIndex)));

                iterable = collection.find(condition).limit(pageSize);
            }
        }

        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();

            System.out.println(doc);
        }
    }
}
