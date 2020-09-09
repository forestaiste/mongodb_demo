package com.forest;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBAuthPoolUtil {

    private static MongoClient client = null;

    static {
        if (client == null) {
            MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
            builder.connectionsPerHost(10);
            builder.connectTimeout(5000);
            builder.socketTimeout(5000);

            MongoCredential credential = MongoCredential.createCredential("si", "dev", "si".toCharArray());

            ServerAddress address = new ServerAddress("127.0.0.1", 27017);

            client = new MongoClient(address, credential, builder.build());
        }
    }

    public static MongoDatabase getDatabase(String dbName) {
        return client.getDatabase(dbName);
    }

    public static MongoCollection getCollection(String dbName, String collumn) {

        MongoDatabase database = getDatabase(dbName);
        return database.getCollection(collumn);
    }
}
