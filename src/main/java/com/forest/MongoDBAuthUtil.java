package com.forest;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

public class MongoDBAuthUtil {

    private static MongoClient client = null;

    static {
        if (client == null) {
            MongoCredential credential = MongoCredential.createCredential("si", "dev", "si".toCharArray());

            ServerAddress address = new ServerAddress("127.0.0.1", 27017);
            client = new MongoClient(address, Arrays.asList(credential));
        }
    }

    public static MongoDatabase getDatabase(String dbName) {
        return client.getDatabase(dbName);
    }

    public static MongoCollection getCollection(String dbName, String collection) {

        MongoDatabase database = getDatabase(dbName);
        return database.getCollection(collection);
    }

    public static void createCollection(String dbName, String collection) {
        MongoDatabase database = getDatabase(dbName);

        database.createCollection(collection);
    }

    public static void dropCollection(MongoCollection collection) {

        collection.drop();
    }
}
