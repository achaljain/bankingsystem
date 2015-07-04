package bank;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Account{
    private String id;
    private String type;
    private String doc;
    private double balance;
    private int tid = 0;
    
    private List<Transaction> transaction;
    
    private Scanner input = new Scanner(System.in);

    public Account() {
        this.id = "NA";
        this.type = "NA";
        this.doc = "NA";
        this.balance = 0;
        this.transaction =  new ArrayList<>();
    }
    
   public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public void create(int accno){
   
           System.out.println("\nChoose Account Type (1.CheckIN	2.Saving  3.Business) ");
           int exit = 0;
           do{
               switch(input.nextLine()){
                       case "1": this.setType("CheckIN");
                                 exit=1; 
                                 break;
                       case "2": this.setType("Saving");
                                 exit=1;
                                 break;
                       case "3": this.setType("Business");
                                 exit=1; 
                                 break;
                       case "4": exit=1;
                                 break;
                       default:  System.out.println("Invalid Selection");
                                 break;
               }
           }while(exit!=1);
        
           DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
           this.setDoc(dateFormat.format(new Date()));
           this.setBalance(0.0);
	this.setId(String.valueOf(accno));
    }

    public void deposit(){
        
        try{
            
            Transaction trs = new Transaction();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            trs.setDate(dateFormat.format(new Date()));

            System.out.println("Enter Amount : ");
            String amt = input.nextLine();
            double amount = Double.parseDouble(amt);
            if(amount<0){
                System.out.println("Negative Deposit Not Allowed !!!");
            }else{

                this.setBalance(this.getBalance() + amount);

                trs.setAmount(amount);
                trs.setType(" Deposit ");
                trs.setDst(" NA ");
                trs.setBalance(this.getBalance());
                trs.setId(++tid);
                transaction.add(trs);
                System.out.println("\nDeposit Done.");            
            }
        }catch(NumberFormatException e){
            System.out.println("Invalid Input Format !!!");            
        }
    }

    public void withdraw() {
        
        try{
            
            Transaction trs = new Transaction();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            trs.setDate(dateFormat.format(new Date()));

            System.out.println("Enter Amount : ");
            String amt = input.nextLine();
            double amount = Double.parseDouble(amt);
            if(amount<0){
                System.out.println("Negative Withdraw Not Allowed !!!");
            }else{
                double check = this.getBalance() - amount;
                if(check<0){
                    System.out.println("Insufficient Balance. Current Balance = "+this.getBalance());
                }else{
                    this.setBalance(check);

                    trs.setAmount(amount);
                    trs.setType(" Withdraw ");
                    trs.setDst(" NA ");
                    trs.setBalance(this.getBalance());
                    trs.setId(++tid);
                    transaction.add(trs);            
                    System.out.println("\nWithdraw Done.");            
                }
            }
        }catch(NumberFormatException e){
            System.out.println("Invalid Input Format !!!");
        }
    }

    public void statement() {
        if(transaction.isEmpty()){
            System.out.println("No transaction history");
        }else{
            
            System.out.println("\n....Account Statement....\n");
            System.out.println("ID \tDate \tType \tAmount \tDestination \tBalance");
            for(int i=0;i<transaction.size();++i){
                System.out.println(transaction.get(i).getId()+" \t"+transaction.get(i).getDate()+" \t"+transaction.get(i).getType()+" \t"+transaction.get(i).getAmount()+" \t"+transaction.get(i).getDst()+" \t"+transaction.get(i).getBalance());               
            }
        }        
    }

     public void transfer() {
        
        try{
                Transaction trs = new Transaction();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                trs.setDate(dateFormat.format(new Date()));

                System.out.println("Enter Amount : ");
                String amt = input.nextLine();
                double amount = Double.parseDouble(amt);
                if(amount<0){
                    System.out.println("Negative Amount not allowed!!!");
                }else{
                    double check = this.getBalance() - amount;
                    if(check<0){
                        System.out.println("Insufficient Balance. Current Balance = "+this.getBalance());
                    }else{
                        this.setBalance(check);

                        trs.setAmount(amount);
                        trs.setType(" Transfer ");
                        System.out.println("Enter Account No - ");
                        trs.setDst(input.next());
                        trs.setBalance(this.getBalance());
                        trs.setId(++tid);
                        transaction.add(trs);            
                        System.out.println("\nTransfer Done.");            
                    }  
                }                            
          }catch(NumberFormatException e){
              System.out.println("Invalid Input Format !!!");
          }        
    }
}
