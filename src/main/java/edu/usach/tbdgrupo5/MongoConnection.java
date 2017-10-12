package edu.usach.tbdgrupo5;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class MongoConnection {
	private static String uriConnection = "mongodb://localhost:27017";
	private String databaseName;
	private String collectionName;
	
	private MongoClient mongoClient;
	private DB database;
	private DBCollection collection;
	
	public MongoConnection(String db, String collection)
	{
		this.databaseName = db;
		this.collectionName = collection;
	}
	
	void connect()
	{
		try 
		{
			this.mongoClient = new MongoClient(new MongoClientURI(uriConnection));
		} catch (UnknownHostException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.database = mongoClient.getDB(this.databaseName);
		this.collection = database.getCollection(this.collectionName);
		
	}
	
	
	public void showTweets(){
		DBCursor cursor = this.collection.find();
		try {
		   while (cursor.hasNext()) {
		      DBObject cur = cursor.next();
		      System.out.println(cur);		      
		   }
		} finally {
		   cursor.close();
		}
	}
	public DBCursor getTweets(){
		DBCursor cursor = this.collection.find();
		return cursor;
	}
}
