package com.forest;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {

    private static MongoClient client = null;

    static {
        if (client == null)
            client = new MongoClient("127.0.0.1", 27017);
    }

    public static MongoDatabase getDatabase(String dbName) {
        return client.getDatabase(dbName);
    }

    public static MongoCollection getCollection(String dbName, String collumn) {

        MongoDatabase database = getDatabase(dbName);
        return database.getCollection(collumn);
    }
}
