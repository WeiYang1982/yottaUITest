package com.yottabyte.utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.yottabyte.entity.Account;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author sunxj
 */
public class MongoDBJDBC {

    private static MongoDatabase getMongoDatabase() {
        MongoDatabase mongoDatabase = getMongoClient().getDatabase("spl");
        return mongoDatabase;
    }

    private static MongoClient getMongoClient() {
        String uri = "mongodb://rizhiyi:rizhiyi&2018@192.168.1.134:27017,192.168.1.135:27017,192.168.1.136:27017/?readPreference=secondaryPreferred&w=majority&fsync=false&journal=true&connectTimeoutMS=10000&socketTimeoutMS=1000";
        MongoClientURI mongoClientURI = new MongoClientURI(uri);

        //通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        System.out.println("Connect to database successfully");
        return mongoClient;
    }

    private static ObjectId search(Map<String, Object> map, String documentName) {
        MongoCollection<Document> collection = getMongoDatabase().getCollection(documentName);

        BasicDBObject basicDBObject = new BasicDBObject();
        for (String key : map.keySet()) {
            basicDBObject.put(key, map.get(key));
            basicDBObject.put("metadata.domain",Account.getAccountName());

        }

        FindIterable<Document> dbCursor = collection.find(basicDBObject);
        MongoCursor<Document> dbCursorIterator = dbCursor.iterator();
        ObjectId resultId = null;
        while (dbCursorIterator.hasNext()) {
            Document result = dbCursorIterator.next();
            resultId = (ObjectId) result.get("_id");
        }
        return resultId;
    }

    public static void delete(String documentName, List<String> deleteDataList) {
        MongoCollection<Document> collection = getMongoDatabase().getCollection(documentName);
        GridFS fs = new GridFS(getMongoClient().getDB("spl"), documentName);
        for (String deleteData : deleteDataList) {
            Map<String, Object> deleteMap = JsonStringPaser.json2Stirng(deleteData);

            if (deleteMap.containsKey("key")) {
                normalDelete(deleteMap, collection);
            } else {
                ObjectId deleteId = search(deleteMap, documentName);
                if (deleteId != null)
                    fs.remove(deleteId);
            }
        }
    }

    private static void normalDelete(Map<String, Object> map, MongoCollection<Document> collection) {
        String accountName = Account.getAccountName();
        String accountId = Account.getAccountId();

        List<String> list = (List) map.get("key");
        for (String value : list) {
            value = accountName + "/" + accountId + value;
            Document document = new Document();
            document.put("key", value);
            collection.deleteOne(document);
        }
    }

    public static void main(String[] args) {
        Account account = new Account();
        account.setAccountName("ops");
        account.setAccountId("71");
//        String deleteData = "{'metadata.domain':'ops','metadata.user_id':'71'}";
        String deleteData = "{'key':['autotest2txt','test']}";
        List list = new ArrayList();
        list.add(deleteData);
        MongoDBJDBC.delete("download", list);
    }
}

