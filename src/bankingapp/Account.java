/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapp;

import java.text.NumberFormat;

/**
 *
 * @author Patsf
 */
public class Account {
    private String name;
    private String address;
    private String phone;
    private String accountNumber;
    private double balance;
    private double interestRate;
    private double interest;

    public Account(String name, String address, String phone,
            String accountNumber, double balance, double interestRate){
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = interestRate;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getPhone(){
        return phone;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
    }
    
    public double getBalance(){
        return balance;
    }
    
    public void setBalance(double balance){
        this.balance = balance;
    }
    
    public String getAccountNumber(){
        return accountNumber;
    }
    
    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }
    
    public void setInterestRate(double interestRate){
        this.interestRate = interestRate;
    }
    
    public double getInterestRate(){
        return interestRate;
    }
    
    public void setInterest(double interest){
        this.interest = interest;
    }
    
    public double getInterest(){
        return interest;
    }
    
    public void withdraw(double amount){
        balance = balance - amount;
    }
        
    public void deposit(double amount){
        balance = balance + amount;
    }
}
