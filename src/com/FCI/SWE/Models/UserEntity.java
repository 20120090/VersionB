package com.FCI.SWE.Models;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 */
public class UserEntity {
	public static String name;
	public static String email;
	public static String password;
	public static String requestname;
	public static String logged_name;
	public static UserEntity user;
	
	

	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user email
	 * @param password
	 *            user provided password
	 */
	
	public UserEntity()
	{
		
	}
	public UserEntity(String name, String requestname)
	{
		this.name = name;
		this.requestname = requestname;
	}
	
	public UserEntity(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	
	public UserEntity(String name) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}

	/**
	 * 
	 * This static method will form UserEntity class using json format contains
	 * user data
	 * 
	 * @param json
	 *            String in json format contains user data
	 * @return Constructed user entity
	 */
	public static UserEntity getUser(String json) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			return new UserEntity(object.get("name").toString(), object.get(
					"email").toString(), object.get("password").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * This static method will form UserEntity class using user name and
	 * password This method will serach for user in datastore
	 * 
	 * @param name
	 *            user name
	 * @param pass
	 *            user password
	 * @return Constructed user entity
	 */

	public static UserEntity getUser(String name, String pass) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			System.out.println(entity.getProperty("name").toString());
			if (entity.getProperty("name").toString().equals(name)
					&& entity.getProperty("password").toString().equals(pass)) {
			System.out.println("found name and password");
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				UserEntity.email=returnedUser.getEmail();
				System.out.println("UserEntity.email "+UserEntity.email);
				return returnedUser;
			}
		}
		System.out.print("NOT found name");
		return null;
	}
	
	
      public static int getusername(String  name) {
    	  System.out.print("user name");
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			System.out.println(entity.getProperty("name").toString());
			if (entity.getProperty("name").toString().equals(name)
					) {
				UserEntity.requestname=name;
				
				//UserEntity returnedUser = new UserEntity(entity.getProperty("name").toString());
				System.out.println("found search name");
				return 1;
			}
		}

		return 0;
	}
      
      
   
	
	

	/**
	 * This method will be used to save user object in datastore
	 * 
	 * @return boolean if user is saved correctly or not
	 */
	public Boolean saveUser() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("users", list.size() + 1);

		employee.setProperty("name", this.name);
		employee.setProperty("email", this.email);
		employee.setProperty("password", this.password);
		
		datastore.put(employee);

		return true;

	}
	
	
       public static Boolean saveRequest() {
    	   DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();


    	   Entity employee = new Entity("request");
    	   employee.setProperty("logged_name", UserEntity.name);
    	   employee.setProperty("requestname", UserEntity.requestname);
    	   employee.setProperty("flag", "false");
    	   datastore.put(employee);
		return true;

	}
       
       public static int getrequest() {
 		DatastoreService datastore = DatastoreServiceFactory
 				.getDatastoreService();

 		Query gaeQuery = new Query("request");
 		PreparedQuery pq = datastore.prepare(gaeQuery);
 		String log="";
 		String req="";
 		String flag="";
 		for (Entity entity : pq.asIterable()) {
 			log=entity.getProperty("logged_name").toString();
 			req=entity.getProperty("requestname").toString();
 			flag=entity.getProperty("flag").toString();
 			if (req.equals(UserEntity.name)&&flag.equals("false")) {
 				UserEntity.logged_name=log;
 				System.out.println("found request name "+UserEntity.logged_name);
 				return 1;
 			}
 		}

 		System.out.println("not found request name ");
 		return 0;
 	}
       
	
       public static int updateflag() {
    		DatastoreService datastore = DatastoreServiceFactory
    				.getDatastoreService();

    		Query gaeQuery = new Query("request");
    		PreparedQuery pq = datastore.prepare(gaeQuery);
    		String log="";
    		String req="";
    		String flag="";
    		for (Entity entity : pq.asIterable()) {
    			log=entity.getProperty("logged_name").toString();
    			req=entity.getProperty("requestname").toString();
    			flag=entity.getProperty("flag").toString();
    			if (req.equals(UserEntity.name)&&flag.equals("false")) {
    				
    				entity.setProperty("flag", "true");
    				datastore.put(entity);
    				
    				System.out.println("the flag updated");
    				return 1;
    				
    			}
    		}

    		System.out.println(" not updated");
    		return 0;
    	}
	
}
