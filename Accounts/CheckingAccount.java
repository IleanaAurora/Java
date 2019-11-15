import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.*;

public class CheckingAccount extends BankAccount
{
  private static double overdraftFee    = 50.00;
  private static int    maxTransactions = 25;
  private static double transactionFee  =  5.00;

  private int transactionCount;

  public CheckingAccount()
  {
      super();
      transactionCount = 0;
  }

  public CheckingAccount(String name, double startAmt)
  {
      super(name, startAmt);
      transactionCount = 0;
  }

  public boolean withdraw(double amount)
  {
     boolean done = super.withdraw(amount);
     if(!done)
       super.withdraw(overdraftFee);
     transactionCount++;
     if(transactionCount > maxTransactions)
       super.withdraw(transactionFee);
     return done;
  }

  public void resetTransactionCount()
  { transactionCount = 0; }

  public String toString()
  {
      String result =  super.toString();
      String temp = String.format("Transactions: %5d\n", transactionCount);
      return result + temp;
  }

  public static void setOverdraftFee(double newFee)
  { overdraftFee = newFee; }

  public static void setTransactionFee(double newFee)
  { transactionFee = newFee; }

  public static void setMaxTransactions(int newMax)
  { maxTransactions = newMax; }

  public static double getOverdraftFee()
  { return overdraftFee; }

  public static double getTransactionFee()
  { return transactionFee; }

  public static int getMaxTransactions()
  { return maxTransactions; }

}