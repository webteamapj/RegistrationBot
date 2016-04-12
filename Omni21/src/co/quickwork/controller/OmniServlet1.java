package co.quickwork.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.quickwork.database.Database;
import co.quickwork.service.Validation;

@WebServlet("/OmniServlet1")
public class OmniServlet1 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    Database database= new Database();
    Validation validation=new Validation();
    
    public OmniServlet1() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		String context=request.getParameter("context");
		String message =request.getParameter("message");
		out.flush();
		
		boolean var=database.checkUserExist(context);
		if(var==false)
		{
			database.insertData(context,0);
		}
		else
		{
			database.updateData(context);
		}
		
		
//	   if(database.getRegistrationDoneFlag(context)==0)
//	      {
	    		int count=database.getCount(context);
		    	String data;
	    		switch(count)
	    		{
	    		case 0: 
	    			  data="Hello, welcome to QuickWork Managed Internship Program. Can you please tell me your full name?";
	    			  out.print(data);   
	    			  out.flush();
	    			  break;	  
	    			  
	    		case 1:
	    			 if(validation.validateName(message)==true)
	    			 {
	    			 database.updateCheckFlag(2, context);
	    			 database.addName(message, context);	
	    			 database.updateCounter(context, 2);
	    			 }
	    		     else
	    		    {
	    			     data="Please enter a valid name";
		    	         out.println(data);
		    	         out.flush();
		    	         database.decreaseCounter(context);	    			
	    			  break;	    			
	    		    }
	    			 
	    		case 2:  
	    			   if(validation.phoneValidation(message)==true)
	    		        {
	    			     database.addMOB(message, context);
	    			     database.updateCounter(context, 3);
	    		        }
	    		   
	    				else if((database.getMob(context).equals("2"))&&(database.getCheckFlag(context)==2))
	    				{
	    					out.println("Enter a valid 10 digit mobile number");
	    					out.flush();
	    					database.decreaseCounter(context);
	    					database.updateCheckFlag(3, context);
	    					break;
	    				}
	    				else if(database.getCheckFlag(context)==3)
	    				{
	    				 data="Invalid number. Please enter a valid 10 digit mobile number.";
	   	    	         out.println(data);
	   	    	         out.flush();
	   	    	         database.decreaseCounter(context);
	   	    	         break;
	    				}
	    
			     case 3:   		    	 
			    	      if((validation.getAltNumberValidation(message, context)==2)||message.equalsIgnoreCase("NA"))
			    			   {
			    			     database.addAlternate(message, context);
			    			     database.updateCounter(context, 4);
			    			   }
			     
			    		        else if((database.getAltNo(context).equals("3"))&&(database.getCheckFlag(context)==3))
			    		       {    		    	    
			 	    	        out.println("Thanks. If you have an alternate  mobile/landline number then please specify, else type NA.");
			 	    	        out.flush();
			 	    	        database.updateCheckFlag(4, context);
			 	    	        database.decreaseCounter(context);
			 	    	        break;
			    		       }
			    		       else if(validation.getAltNumberValidation(message, context)==3)
			    		       {
			    		    	 data="Invalid number. Please enter a valid number.";
				    	         out.println(data);
				    	         out.flush();
				    	         database.decreaseCounter(context);
				    	         break;
			    		       }
			    		       else if(validation.getAltNumberValidation(message, context)==1)
			    	            {
			    	        	data="Your primary number and alternate number is the same, please enter a different alternate number";
				    	        out.println(data);
				    	        out.flush();
				    	        database.decreaseCounter(context);
				    	        break;
			    	            }
	    		
	    		       case 4: if(validation.isValidEmail(message)==true)
	    			          {
	    			            database.emailID(message, context);
	    			            database.updateCounter(context, 5);
	    			          }
	    		   
	    		   		    else if((database.getEmail(context).equals("4"))&&(database.getCheckFlag(context)==4))
	    		   		     {
			    			    data="Please specify your email address." ;
				    	        out.println(data);
				    	        out.flush();
				    	        database.updateCheckFlag(5, context);
				    	        database.decreaseCounter(context);
				    	        break;
			    		   		}
			    		   		else
			    		   		{
			    			      data="Invalid email address. Please enter a valid address.";
				    	    	  out.println(data);
				    	    	  out.flush();
				    	    	  database.decreaseCounter(context);
				    	    	  break;
			    			    }
	    		   
		    		   case 5: if((database.getEducationQualification(context).equals("5"))&&(database.getCheckFlag(context)==5))
		    		   			{
		    			        data="What is your educational qualification ?";    
			    	            out.println(data);
			    	            out.flush();
			    	            database.updateCheckFlag(6, context);
			    	            database.decreaseCounter(context);
		    			        break; 
		    		   			}
		    		   			else
		    		   			{
		    		   				database.educationQualification(message, context);
		    		   				database.updateCounter(context, 6);
		    		   			}
	    			   
			    		   case 6: if((database.getYearOfPassing(context).equals("6"))&&(database.getCheckFlag(context)==6))
			   					   {
			   						data="What is your year (yyyy) of graduation.";
			   		    	        out.println(data);
			   		    	        out.flush();
			   		    	        database.updateCheckFlag(7, context);
			   		    	        database.decreaseCounter(context);
			   	                    break;
			   					    }
				    			   		    			  
				   					else if(validation.yearOfPassingValidation(message).equalsIgnoreCase("Invalid Format"))
				   					{
				   					  data="Invalid Format. Please enter a valid year(yyyy)";
					            	  out.println(data);
					            	  database.decreaseCounter(context);
					            	  break;
							   		 }
						    		   if(validation.yearOfPassingValidation(message).equalsIgnoreCase("yes"))
					   					{
				    			         database.yearOfPassing(message, context);
				    			         database.updateCounter(context, 7);
					   					}
				   					else if((database.getYearOfPassing(context).equals("6"))&&(database.getCheckFlag(context)==6))
				   					{
				   						data="What is your year (yyyy) of graduation.";
				   		    	        out.println(data);
				   		    	        out.flush();
				   		    	        database.updateCheckFlag(7, context);
				   		    	        database.decreaseCounter(context);
				   	                    break;
				   					}
				   					else
				   					{
				   						 PrintWriter out1=response.getWriter();
					    	             out1.println("Currently we are considering candidates whose year of graduation is between "
					    	                     +validation.getPreviousYearDate()+" and "+validation.getNextYear());
					    	             out1.flush();
					    	             out1.println("Thank you for registering with QuickWork Technologies Pvt Ltd.");
					    	             out1.println("Here is your registered profile");    		
					    	             out1.flush();
						    	         out1.println("1. Name: "+ database.getName(context));
						    	         out1.flush();
						    	         out1.println("2. Mobile Number: "+ database.getMob(context));
						    	         out1.flush();
						    	         out1.println("3. Email Address: "+ database.getEmail(context));
						    	         out1.flush();
						    	         out1.println("4. Educational Qualification: "+ database.getEducationQualification(context));
						    	         out1.flush();    	    	         out1.flush();
				     	                 database.updateCounter(context, 11);;
						    	         break;
				   					}
			    			   
			    		   case 7: if((database.getCareerInterest(context).equals("7"))&&(database.getCheckFlag(context)==7))
				   					{
			    			   			out.println("What is your career goal?");
			    			   			database.yearOfPassing(message, context);
			    			   		    database.updateCheckFlag(8, context);
				   		    	        database.decreaseCounter(context);
			    			   			break;
				   					}
				   					else
				   					{
				   				      database.careerInterest(message, context);
				   				      database.updateCounter(context, 8);
				   					}
			    			   
			    		   case 8: if(validation.isValidAvaliabilitity(message)==true)
				   					{
			    			        if(message.equalsIgnoreCase("Y"))
					    		      {
					    	          database.availability(message, context);
					    	          database.updateCounter(context, 9);
					    		      }
					    		      else if(message.equalsIgnoreCase("N"))
					    		      {	  
					    		    	 data="Thanks. This is your profile";
					    		    	 out.println(data);
						    	         out.println("1. Name: "+ database.getName(context));
						    	         out.flush();
						    	         out.println("2. Mobile Number: "+ database.getMob(context));
						    	         out.flush();
						    	         out.println("3. Email Address: "+ database.getEmail(context));
						    	         out.flush();
						    	         out.println("4. Educational Qualification: "+ database.getEducationQualification(context));
						    	         out.flush();
						    	         out.println("5. Year Of Graduation: "+ database.getYearOfPassing(context));
						    	         out.flush();
						    	         out.println("Thank you for registering with QuickWork Technologies Pvt Ltd.");
					    		    	 database.availability(message, context);
					    		    	 database.updateCounter(context,11);
					    		    	 break;
					    		      }
					    	          }
				   					
				   					else if((database.getAvailability(context).equals("8"))&&(database.getCheckFlag(context)==8))
				   					{	   						
				   		    	        out.println("Are you available for a full-time internship for 6 months ? Y or N");
				   		    	        database.updateCheckFlag(9, context);
				   		    	        database.decreaseCounter(context);
				   		    	      	break;
				   					}
				   					else
				   					{
				   	    	         out.println("Invalid input, Please type Y or N.");
				   	    	    	 database.decreaseCounter(context);
				   	    	    	 break;
				   					}
			    		   case 9: 	if((database.getStartInternshipDate(context).equals("9"))&&(database.getCheckFlag(context)==9)) 
			    		   			{
				    	            out.println("From what date are you available full-time. Enter in dd/mm/yyyy format.");
				    	            database.updateCheckFlag(10, context);
			   		    	        database.decreaseCounter(context);
			   		    	       
			    		   			}
			    		   			else if(validation.isValidDate(message)==true)
					    		        {
			    		   				 database.startInternshipDate(message, context);
			    		   				 data= "Thanks. This is your profile";  
			    		    	         out.println(data);
			    		    	         out.println("1. Name: "+ database.getName(context));
			    		    	         out.flush();
			    		    	         out.println("2. Mobile Number: "+ database.getMob(context));
			    		    	         out.flush();
			    		    	         out.println("3. Alternate Number: "+ database.getAltNo(context));
			    		    	         out.flush();
			    		    	         out.println("4. Email Address: "+ database.getEmail(context));
			    		    	         out.flush();
			    		    	         out.println("5. Educational Qualification: "+ database.getEducationQualification(context));
			    		    	         out.flush();
			    		    	         out.println("6. Year Of Graduation: "+ database.getYearOfPassing(context));
			    		    	         out.flush();
			    		    	         out.println("7. Career Interest: "+ database.getCareerInterest(context));
			    		    	         out.flush();
			    		    	         out.println("8. Availibility for full-time internship: "+ database.getAvailability(context));
			    		    	         out.flush();
			    		    	         out.println("9. Availability Date: "+ database.getStartInternshipDate(context));
			    		    	         out.flush();
						    	         out.println("Thank you for registering with QuickWork Technologies Pvt Ltd.");
			    		    	         database.updateRegistrationDoneFlag(1, context);
			    		    	         database.updateCounter(context, -1);
			    		    	         database.updateCheckFlag(0, context);
			    		    	        
					    		        }
				    		   	    else
				 	    	        {
				 	    	        data="Invalid date. Please enter a valid date in dd/mm/yyyy format.";
				 	    	        out.println(data);
				 	    	    	database.decreaseCounter(context);
				 	    	    	
				 	    	        }
			    		           break;  
			    		         
			    		           }	    		    		      
	                    }				    	
	    	     }
	        
	   	    		