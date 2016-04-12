package co.quickwork.database;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class Connection {
	DB dBase;
	public MongoClient CLIENT ;
	
	public DB conn()
	{
		try{
		CLIENT = new MongoClient("localhost",27017) ;
        dBase = CLIENT.getDB("OmniChannelDB"); 
        }catch(Exception E){
	      E.printStackTrace();
        }
        return dBase;  	
	}
}
