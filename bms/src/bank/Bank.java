package bank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Bank {
    
    static List<User> database;
    static int accno = 100;
    
    final static String startscreen =  "\n1. Employee \t2.Customer \t3.Exit" ;
	
    final static String empmenu =  "\n1.Accounts List \t2.Search Account \t3.Query \t4.Back";
    final static String accmenu = "\n1.Account List by Type \t2.Account List by Name \t3.Account List by Date of Creation \t4.Account List by Balance \t5.Back";
    final static String typmenu = "1.Check In \t2.Saving \t3.Business \t4.Back";
    
    final static String cusstart = "\n1.New Customer \t2.Existing Customer \t3.Back";
    
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args){
        
        database = new ArrayList<>();
        
        // Bank Homepage.
        int exit=0;
        do{
            System.out.println("\tBank Homepage");
            System.out.println(startscreen);
            switch(in.nextLine()){
            case "1":   employee();                        
                        break;
            case "2":   customer();                        
                        break;
            case "3":   exit=1;
                        break;
            default:    System.out.println("Invalid selection");
                        break;
        }
        }while(exit!=1);        
    }
    
   // Employee Menu 
   static void employee(){
       
       int exit = 0;
       do{
            System.out.println(empmenu);
            switch(in.nextLine()){
            case "1":   viewlist();                        
                        break;
            case "2":   searchacc();                        
                        break;
            case "3":   query();
                        break;
            case "4":   exit=1;
                        break;
            default:    System.out.println("Invalid selection");
                        break;
            }
        }while(exit!=1);
   }
   
   // Customer Menu
   static void customer(){
       
       int exit = 0;       
       do{
           System.out.println(cusstart);
           switch(in.nextLine()){
           case "1":    newcustomer();
                        break;
           case "2":    oldcustomer();
                        break;
           case "3":    exit=1;
                        break;
           default:     System.out.println("Invalid Selection");
                        break;                
           }
       }while(exit!=1);
   }
   
   /****** Customer Functions ******/
   
   // Add new Customer
   static void newcustomer(){
       
       boolean flag=false;
       String uname = "NA";
       
       // Check for unique username
       while(!flag){
           flag = true;
           System.out.println("Choose username : ");
            uname = in.nextLine();
            if(!database.isEmpty()){
                for(int i=0;i<database.size();++i){
                    if(database.get(i).getId().equals(uname)){
                        System.out.println("Username already taken. Try Again.");
                        flag = false;
                    }
                }
            }
       }
       
       User user = new User();
       user.create(uname,++accno);
       database.add(user);
       System.out.println(" \n\n .... New Account Created. Account Details ....\n");
       user.display(1);
       user.usermenu();
   }
   
   // Existing Customer. Account access through ID.
   static void oldcustomer(){
       System.out.println("\nEnter UserID - ");
       String uid = in.nextLine();
       int i=0,flag=0;
       while(i<database.size()){
           if(database.get(i).getId().equals(uid)){
               database.get(i).display(1);
               database.get(i).usermenu();
               flag=1;
               break;
           }
           i++;
       }       
       if(flag==0){
           System.out.println("Account Not Found. Check your ID ! ");
       }       
   }

/****** Employee Functions ******/   
   
// List of all accounts
   static void viewlist() {
        System.out.println("\n\n ..... Accounts List ..... \n");
        if(database.isEmpty()){
            System.out.println("No Accounts Exist");
        }else{
            int i=0;
            while(i<database.size()){
            database.get(i).display(2);
            i++;
            }
        }        
    }

// Search on Account number.   
    static void searchacc() {
        System.out.println("Enter Account ID - ");
        String uid = in.nextLine();
        int i=0,flag=0;
        while(i<database.size()){
           if(database.get(i).getAccount().getId().equals(uid)){
                database.get(i).display(2);
                flag=1;
                break;
           }
           i++;
        }
        if(flag==0){
            System.out.println("Account not found. Check Account ID");
        }
    }

// Database Query    
    static void query() {
        
       int exit = 0;       
       do{
           System.out.println(accmenu);
           switch(in.nextLine()){
           case "1":    listbytype();                        
                        break;
           case "2":    listbyname();                        
                        break;
           case "3":    listbydoc();                        
                        break;
           case "4":    listbybal();
                        break;
           case "5":    exit=1;
                        break;
           default:     System.out.println("Invalid Selection");
                        break;                
           }
       }while(exit!=1);
    }

// Search Accounts by type    
    static void listbytype() {
        
        int i=0,flag=0,exit=0;
        String type = "NA";
        do{
           System.out.println("Choose account type - "+typmenu); 
           switch(in.nextLine()){
            case "1":   type = "CheckIN";   exit=1;
                        break;
            case "2":   type = "Saving";    exit=1;
                        break;
            case "3":   type = "Business";  exit=1;
                        break;
            case "4":   type="NA";
                        exit=1;
                        break;
            default:    System.out.println("Invalid Selection");
                        break;                
            }
        } while(exit!=1) ;       
        
        while(i<database.size()) {
            if(database.get(i).getAccount().getType().toUpperCase().charAt(0)==type.toUpperCase().charAt(0)){
                database.get(i).display(2);
                flag=1;
            }
            i++;
        }
        if(flag==0){
            System.out.println("Accounts not found");
        }
    }

// Search Accounts by name    
    static void listbyname() {
         System.out.println("Enter name - ");
         String name = in.nextLine();
        
         int i=0,flag=0;
         while(i<database.size()){
             
             if(database.get(i).getName().equals(name)){
                 database.get(i).display(2);
                 flag=1;
             }
             i++;             
         }
         if(flag==0){
             System.out.println("Account not found");
         }
    }
    
// Search accounts by date of creation
    static void listbydoc() {
        System.out.println("Enter Date of Creation (dd/mm/yyyy) - ");
        String docr = in.nextLine();
        if(checkformat("dd/MM/yyyy",docr)){

            int i=0,flag=0;

            while(i<database.size()){

                if(database.get(i).getAccount().getDoc().equals(docr)){
                    database.get(i).display(2);
                    flag=1;
                }
                i++;             
            }
            if(flag==0){
                System.out.println("Account not found");
            }         
        }        
     }
    
// Check input date format    
    static boolean checkformat(String format,String value){
        Date date = null;
        try{
            SimpleDateFormat form = new SimpleDateFormat(format);
            date = form.parse(value);
            if(!value.equals(form.format(date))){
                date=null;
            }
        }catch(ParseException e){
            System.out.println("Invalid Date format !!!");
        }
        return date!=null;
    }

//Search Account by balance
    static void listbybal() {
        
        try{
            
            System.out.println("Enter Min balance- ");
            String mn = in.nextLine();
            double min = Double.parseDouble(mn);
            System.out.println("Enter Max balance- ");
            String mx = in.nextLine();
            double max = Double.parseDouble(mx);

            if((min<0)||(max<0)){
                System.out.println("Input Error !!! Negative Balance.");
            }else{

                int i=0,flag=0;

                while(i<database.size()){

                    if((database.get(i).getAccount().getBalance()>=min)&&(database.get(i).getAccount().getBalance()<=max)){
                        database.get(i).display(2);
                        flag=1;
                    }
                    i++;             
                }
                if(flag==0){
                    System.out.println("Account not found");
                }
            }                   
        }catch(NumberFormatException e){
            System.out.println("Invalid Input Format !!!");
        }        
    }
}



