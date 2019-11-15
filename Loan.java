import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.*;

public class Loan
{
   private double amountOfLoan;
   private double interestRate;
   private double years;
   private int  id;

   private static int currentId = 1;

   public Loan()
   {
        amountOfLoan = 0;
        interestRate = 0;
        years = 0;
        id = currentId++;
   }

   public Loan(double amount, double rate, double yrs)
   {
        amountOfLoan = amount;
        interestRate = rate;
        years = yrs;
        id = currentId++;
   }

   public int getId()
   {return id;}

   public double getAmount()
   {  return amountOfLoan; }

   public double getRate()
   {  return interestRate; }

   public double getYears()
   {  return years;   }

   public double getMonthlyPayment()
   {
       double rate = interestRate/12/100;
       return (amountOfLoan * rate)/( 1 - Math.pow( (1 + rate),-12*years) ) ;
   }

   public double getTotalPayment()
   { return getMonthlyPayment() * 12 * years;   }

   public double getTotalInterest()
   {return getTotalPayment() - amountOfLoan;}

   public void setAmount(double amount)
   { amountOfLoan = amount; }

   public void setRate(double rate)
   { interestRate = rate; }

   public void setYears(double yrs)
   { years = yrs; }

   public String toString()
   {
	   String result = getClass().getName() + " id = " + id
	                   + "\nBorrowed = " + amountOfLoan
	                   + "\nInterest Rate = " + interestRate
	                   + "\nLength of loan = " + years + " years";
	   return result;
   }

}