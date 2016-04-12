package co.quickwork.database;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Database {

	Connection Mcon = new Connection();
	protected DB dBase = Mcon.conn();
	
	DBCollection dummy = dBase.getCollection("UserDetails");
	DBCollection edit = dBase.getCollection("EditNumbers");
	
	public void insertData(String context,int flag)
	{
		BasicDBObject basicDBObject= new BasicDBObject();
		basicDBObject.put("context", context);
		basicDBObject.put("counter", 0);
		basicDBObject.put("registrationDoneFlag",flag);
		basicDBObject.put("checkFlag", 1);
		dummy.insert(basicDBObject);
	}
	
	public void  updateData(String context)
	{
		BasicDBObject objDb1 = new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("counter", getCount(context)+1);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		dummy.update(objDb1, updateDBobj);
	}
	
	public void updateCounter(String context,int no)
	{
		BasicDBObject objDb1 = new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("counter", no);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		dummy.update(objDb1, updateDBobj);		
	}
	
	public void  decreaseCounter(String context)
	{
		BasicDBObject objDb1 = new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("counter", getCount(context)-1);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		dummy.update(objDb1, updateDBobj);
	}
	
	public int getCount(String context)
	{
		return (int) dummy.findOne(new BasicDBObject("context", context)).get("counter");
	}
	
	public boolean checkUserExist(String context)
	{
	 try{
		    int value=(int) dummy.findOne(new BasicDBObject("context", context)).get("counter");
			return true;
			
		}catch(NullPointerException e){
			return false;
		}		
	}
	
	public void addName(String name,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("Name", name);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		dummy.update(objDb1, updateDBobj);
		
	}
	
	public void addMOB(String mob,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("MOB", mob);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		dummy.update(objDb1, updateDBobj);
	}
	
	public void addAlternate(String altmob,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("AltMOB", altmob);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		dummy.update(objDb1, updateDBobj);
	}
	
	public void emailID(String email,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("Email", email);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		dummy.update(objDb1, updateDBobj);
	}
	
	public void educationQualification(String edu,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("Qualification", edu);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		dummy.update(objDb1, updateDBobj);
	}
	
	public void yearOfPassing(String year,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("YOP", year);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		dummy.update(objDb1, updateDBobj);
	}
	
	public void careerInterest(String interest,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("careerInterest", interest);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		dummy.update(objDb1, updateDBobj);
	}
	
	public void availability(String available,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("Availability", available);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		dummy.update(objDb1, updateDBobj);
	}
	
	public void updateCheckFlag(int flag,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("checkFlag", flag);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		dummy.update(objDb1, updateDBobj);
	}
	
	public void updateRegistrationDoneFlag(int flag,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("registrationDoneFlag", flag);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		dummy.update(objDb1, updateDBobj);
	}
	
	public void startInternshipDate(String date,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("AvailabilityDate", date);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		dummy.update(objDb1, updateDBobj);
	}
	
	public void otherComment(String comment,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("Comment", comment);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		dummy.update(objDb1, updateDBobj);
	}
	
	public void dropTable() throws UnknownHostException
	{
		MongoClient mongoClient = new MongoClient("52.33.177.69",27017) ;
		DB db=mongoClient.getDB("dummy7");
		db.dropDatabase();
		System.out.println("db dropped");
	}
	
	public String getName(String context){
		String name;
		try{
	   name = dummy.findOne(new BasicDBObject("context",context)).get("Name").toString();
		
		 }
		catch(NullPointerException e)
		{
			name="1";
		}
		return name; 	
	}
	
	public String getMob(String context){
		String name;
		try{
		 name = dummy.findOne(new BasicDBObject("context",context)).get("MOB").toString();
		}
		catch(NullPointerException e)
		{
			name="2";
		}
		System.out.println(name);
		return name; 			
	}
	
	public String getAltNo(String context){
		String name;
		try{
			name= dummy.findOne(new BasicDBObject("context",context)).get("AltMOB").toString();
		}
		catch(NullPointerException e)
		{
			name="3";
		}
		return name; 
			
	}
	
	public String getEmail(String context){
		
		String name; 
		try{
			name= dummy.findOne(new BasicDBObject("context",context)).get("Email").toString();
		}
		catch(NullPointerException e)
		{
			name="4";
		}
		return name; 
			
	}
	public String getEducationQualification(String context){
		
		String name ;
		try{
		name= dummy.findOne(new BasicDBObject("context",context)).get("Qualification").toString();
		}
		catch(NullPointerException e)
		{
			name="5";
		}
		return name; 
			
	}
	public String getYearOfPassing(String context){
		String name;
		try{
			name= dummy.findOne(new BasicDBObject("context",context)).get("YOP").toString();
		}
		catch(NullPointerException e)
		{
			name="6";
		}
		return name; 
			
	}
	public String getCareerInterest(String context){
		
		String name;
		try{
			name= dummy.findOne(new BasicDBObject("context",context)).get("careerInterest").toString();
		}
		catch(NullPointerException e)
		{
			name="7";
		}
		return name; 
			
	}
	public String getAvailability(String context){
		String name;
		try{
			name= dummy.findOne(new BasicDBObject("context",context)).get("Availability").toString();
		}
		catch(NullPointerException e)
		{
			name="8";
		}
		return name; 
			
	}
	
	public String getStartInternshipDate(String context){
		String date=null;
		try{
		date = dummy.findOne(new BasicDBObject("context",context)).get("AvailabilityDate").toString();
		
		}
		catch(NullPointerException e)
		{
			date= "9";
		}
		return date;	
	}
	
	public void insertEditNumbers(String context,List<String> message,int size)
	{
		BasicDBObject basicDBObject= new BasicDBObject();
		basicDBObject.put("context", context);
		basicDBObject.put("numbers", message);
		basicDBObject.put("size", size);
		basicDBObject.put("trackNo", 0);
		edit.insert(basicDBObject);
	}
	
	public int getRegistrationDoneFlag(String context)
	{
		int flag = (int) dummy.findOne(new BasicDBObject("context",context)).get("registrationDoneFlag");
		return flag; 
	}
	
	public int getCheckFlag(String context)
	{
		int flag = (int) dummy.findOne(new BasicDBObject("context",context)).get("checkFlag");
		return flag; 
	}
	
	public int getSize(String context)
	{
		int size = (int) edit.findOne(new BasicDBObject("context",context)).get("size");
		return size; 
	}
	
	public int getTrackNo(String context)
	{
		int size = (int) edit.findOne(new BasicDBObject("context",context)).get("trackNo");
		return size; 
	}
	
	public List<String> getNumbers(String context)
	{
		List<String> numbers = (List<String>) edit.findOne(new BasicDBObject("context",context)).get("numbers");
		return numbers; 
	}
	
	public void dropCollection(String context)
	{
		//code
	}
	public void updateTrackNo(String context, int trackNo)
	{
		BasicDBObject objDb1 = new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("trackNo", trackNo);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		edit.update(objDb1, updateDBobj);		
	}
	
	public static void main(String[] args) {
		Database database= new Database();
		database.getMob("{\"botname\":\"QW05\",\"channel\":\"whatsapp\",\"contextid\":\"919930770326\",\"conversationType\":\"p2p\",\"type\":\"msg\"}");
	}
}