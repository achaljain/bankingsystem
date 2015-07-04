package bank;

import java.util.Scanner;

public class User{
    private String name;
    private String id;
    private String address;
    private String contact;
    private Account account;
    
    private Scanner input = new Scanner(System.in);
       
    private final String cusmenu = "\n1.Deposit \t2.Withdraw \t.3.Transfer \t4.Statement \t5.Back";
    
    public User() {
        this.name = "NA";
        this.id = "NA";
        this.address = "NA";
        this.contact = "NA";
        this.account = new Account();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
  
    
   public void create(String uname,int accno){
        
           System.out.print("\nEnter name - ");
	this.setName(input.nextLine());
		
	System.out.print("\nEnter Address - ");
	this.setAddress(input.nextLine());
				
	System.out.print("\nEnter Contact Numbers - ");
	this.setContact(input.nextLine());
				
	this.setId(uname);
	
           this.getAccount().create(accno);
    }
    
    public void usermenu(){
        
       int exit=0;
        do{
           System.out.println(cusmenu);
           switch(input.nextLine()){
            case "1":   this.getAccount().deposit();                        
                        break;
            case "2":   this.getAccount().withdraw();                        
                        break;
            case "3":   this.getAccount().transfer();                        
                        break;
            case "4":   this.getAccount().statement();                        
                        break;
            case "5":   exit=1;
                        break;
            default:    System.out.println("Invalid Selection");
                        break;
           }
        }while(exit!=1);       
    }
    
    public void display(int mode){
        if(mode==1){
            System.out.println("\nWelcome "+this.getId());
        }
        System.out.println("\nUser Details\nName - "+this.getName()+"\nAddress - "+this.getAddress()+"\nContact - "+this.getContact());
        System.out.println("Account Details\nAccount Type - "+this.getAccount().getType()+"\t DOC : "+this.getAccount().getDoc()+" \nAccount number : "+this.getAccount().getId()+" \t Balance: "+this.getAccount().getBalance());
    }
}
