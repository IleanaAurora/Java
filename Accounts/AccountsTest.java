import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.*;

public class AccountsTest
{
  public static void main(String[] args)
  {
     BankAccount billsBankAccount    = new BankAccount("Bill", 1000);
     SavingsAccount marysSavingsAccount = new SavingsAccount("Mary", 2000, 5);
     CheckingAccount joesCheckingAccount = new CheckingAccount("Joe", 500);
     SpecialCheckingAccount tomsSpecialAccount =
                         new SpecialCheckingAccount("Tom", 4500, 2.5);
     CheckingAccount annsCheckingAccount = new CheckingAccount("Ann", 1000);

     marysSavingsAccount.addInterest();
     System.out.println(marysSavingsAccount);

     marysSavingsAccount.transferTo(billsBankAccount, 50);
     System.out.println();
     System.out.println(billsBankAccount);

     joesCheckingAccount.withdraw(1000);
     System.out.println();
     System.out.println(joesCheckingAccount);
     CheckingAccount.setOverdraftFee(75);
     annsCheckingAccount.withdraw(1500);
     System.out.println(annsCheckingAccount);


     for (int i = 0; i < 10; i++)
        tomsSpecialAccount.withdraw(400);
     System.out.println();
     System.out.println(tomsSpecialAccount);


     BankAccount[] accounts = new BankAccount[4];
     accounts[0] = marysSavingsAccount;
     accounts[1] = billsBankAccount;
     accounts[2] = joesCheckingAccount;
     accounts[3] = tomsSpecialAccount;
     System.out.print("\n\n\nPrint all accounts\n\n");

     for(int i = 0; i < accounts.length; i++)
     {
        System.out.println(accounts[i].toString());
        System.out.print("\n\n");
     }
     if(marysSavingsAccount instanceof SavingsAccount)
        System.out.println("Mary is a SavingsAccount");

     //for(BankAccount b : accounts)
     //   System.out.println(getType(b));
  }

  public static String getType(BankAccount b)
  {	  return "Bank Account"; }

  public static String getType(SavingsAccount s)
  {	  return "Savings Account"; }

  public static String getType(CheckingAccount c)
  {	  return "Checking Account"; }

  public static String getType(SpecialCheckingAccount sc)
  {	  return "Special Checking Account"; }



}