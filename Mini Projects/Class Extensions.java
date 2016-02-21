// Modified by Hardeep Singh
// Written by Barry Soroka
//
// Solves CS 141, Winter 2013, Homework 16.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw16
{
//-----------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      A a = new A("linda","1233");
      System.out.println("\n" + a);

      SavingsAccount sa = new SavingsAccount("bill","0385",200);
      System.out.println("\n" + sa);

      sa.deposit(100);
      System.out.println("\n" + sa);

      sa.withdraw(50);
      System.out.println("\n" + sa);
   }
//-----------------------------------------------------------------------
} // end class Hw16
/////////////////////////////////////////////////////////////////////////
class A
{
	protected String name;
	protected String accountNumber;
//-----------------------------------------------------------------------
	public A(String name, String accountNumber)
	{
		this.name = name;
		this.accountNumber = accountNumber;
	}
//-----------------------------------------------------------------------
	public String toString()
	{
		return String.format(" %s\n %s", accountNumber, name);
	}
//-----------------------------------------------------------------------
} // end class A
/////////////////////////////////////////////////////////////////////////
class SavingsAccount extends A
{
	double balance;
//-----------------------------------------------------------------------
	public SavingsAccount(String name, String accountNumber, double balance)
	{
		super(name, accountNumber);
		this.balance = balance;
	}
//-----------------------------------------------------------------------
	public void deposit(double deposit)
	{
		balance += deposit;
	}
//-----------------------------------------------------------------------
	public void withdraw(double withdraw)
	{
		balance -= withdraw;
	}
//-----------------------------------------------------------------------
	public String toString()
	{
		return String.format(" %s\n %s\n %.2f", accountNumber, name, balance);
	}
//-----------------------------------------------------------------------
} // end class SavingAccount
/////////////////////////////////////////////////////////////////////////
