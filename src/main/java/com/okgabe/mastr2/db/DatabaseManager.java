/*
 * Copyright 2020 Gabriel Keller
 * This work is licensed under a Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * A copy of this license can be found at
 * https://creativecommons.org/licenses/by-nc-sa/4.0/legalcode.
 */

package com.okgabe.mastr2.db;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.okgabe.mastr2.entity.BotUser;
import com.okgabe.mastr2.entity.EntityAdaptor;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseManager {

    private MongoClient client;
    private MongoDatabase mastrDatabase;
    private MongoCollection<Document> users;
    private static Logger logger = LoggerFactory.getLogger(DatabaseManager.class);

    public DatabaseManager(String connectionString) throws MongoException  {
        client = MongoClients.create(connectionString);
        mastrDatabase = client.getDatabase("mastr");
        users = mastrDatabase.getCollection("users");
    }

    public BotUser getBotUser(long id){
        Document search = new Document();
        search.put("_id", id);

        FindIterable<Document> userIter = users.find(search);
        Document user = userIter.cursor().tryNext();

        return EntityAdaptor.toBotUser(user);
    }
}