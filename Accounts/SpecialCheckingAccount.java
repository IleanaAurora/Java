import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.*;

public class SpecialCheckingAccount extends CheckingAccount
{
  private static double minimumBalance  = 1000;

  private double interestRate;

  public SpecialCheckingAccount(double rate)
  {
      super();
      interestRate = rate;
  }

  public SpecialCheckingAccount(String name, double startAmt, double rate)
  {
      super(name, startAmt);
      interestRate = rate;
  }

  public void addInterest()
  {
      double temp = getBalance();
      if(temp >= minimumBalance)
      {
          double interest = temp * ( interestRate/(100*12) );
          deposit(interest);
      }
  }

  public String toString()
  {
      String result =  super.toString();
      String temp = String.format("Annual Interest Rate: %5.2f%%\n", + interestRate);
      return result + temp;
  }

  public static void setMinimumBalance(double newMinimum)
  { minimumBalance = newMinimum; }

  public static double getMinimumBalance()
  { return minimumBalance; }

}