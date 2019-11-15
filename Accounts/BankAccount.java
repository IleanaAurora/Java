import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.*;

public class BankAccount
{
  private String ownerName;
  private double balance;
  private int    accountNumber;

  private static int currentAccountNumber = 1;

  public BankAccount()
  {
      accountNumber = currentAccountNumber++;
      ownerName = "";
      balance = 0;
  }

  public BankAccount(String name, double startAmt)
  {
      accountNumber = currentAccountNumber++;
      ownerName = name;
      balance = startAmt;
  }

  public void setName(String name)
  { ownerName = name; }

  public void deposit(double amount)
  { balance = balance + amount; }

  public boolean withdraw(double amount)
  {
      if(amount > balance)
        return false;
      balance = balance - amount;
      return true;
  }

  public double getBalance()
  { return balance; }

  public String getOwner()
  { return ownerName; }

  public int getAccountNumber()
  { return accountNumber; }


  public boolean transferTo(BankAccount other, double amount)
  {
      if(amount > balance)
        return false;
      other.balance = other.balance + amount;
      balance = balance - amount;
      return true;
  }

  public String toString()
  {
      String result =  String.format("Account Number: %7d\nOwner %-20s\n$%,11.2f\n",
                                                accountNumber, ownerName, balance);
      return result;
  }

  public boolean equals(Object otherObject)
  {
	  if(otherObject == this)
	     return true;
	  if(otherObject == null)
	     return false;
	  if(this.getClass() != otherObject.getClass())
	     return false;
	  BankAccount otherAccount = (BankAccount)otherObject;
	  return accountNumber == otherAccount.accountNumber;
  }

}