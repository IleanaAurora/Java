import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.*;

public class SavingsAccount extends BankAccount
{
  private double interestRate;

  public SavingsAccount(double rate)
  {
      super();
      interestRate = rate;
  }

  public SavingsAccount(String name, double startAmt, double rate)
  {
      super(name, startAmt);
      interestRate = rate;
  }

  public void addInterest()
  {
      double temp = getBalance();
      double interest = temp * ( interestRate /(100 * 12) );
      deposit(interest);
  }

  public String toString()
  {
      String result =  super.toString();
      String temp = String.format("Annual Interest Rate: %5.2f%%\n", interestRate);
      return result + temp;
  }

  public void setInterestRate(double newRate)
  { interestRate = newRate; }
}