/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapp;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author sepobanz
 */
public class BankingApp extends JFrame{
    //private variables here
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField accountNumField;
    private JTextField balanceField;
    private JTextField wDField;
    private JButton searchCustomerB;
    private JButton previousB;
    private JButton nextB;
    private JButton addB;
    private JButton updateB;
    private JButton openAcctB;
    private JButton depositB;
    private JButton withdrawB;
    private JButton calcInterestB;
    private JTextField interestField;
    private JTextField calcInterestField;
    private static int nextAccountNumber = 10001;
    private static DAO<Account> accountFile = new AccountTextFile();
    
    
    
    public BankingApp() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        initComponents();
        
    }
    
    private void initComponents() {
        setTitle("Banking Application");
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel overallPanel = new JPanel();
        
        Dimension dim = new Dimension(130,20);
        Dimension dim2 = new Dimension(80,20);
        
        JPanel namesAndFields = new JPanel();
        JLabel nameLabel = new JLabel("Customer Name: ");
        nameField = new JTextField();
        nameField.setPreferredSize(dim);
        JLabel addressLabel = new JLabel("Address: ");
        addressField = new JTextField();
        addressField.setPreferredSize(dim);
        JLabel phoneLabel = new JLabel("Phone Number: ");
        phoneField = new JTextField();
        phoneField.setPreferredSize(dim);
        JLabel accountNumLabel = new JLabel("Account Number: ");
        accountNumField = new JTextField();
        accountNumField.setPreferredSize(dim);
        JLabel balanceLabel = new JLabel("Balance: ");
        balanceField = new JTextField();
        balanceField.setPreferredSize(dim);
        balanceField.setEditable(false);
        JLabel wDLabel = new JLabel("Withdrawal/Deposit: ");
        wDField = new JTextField();
        wDField.setPreferredSize(dim);
        
        namesAndFields.setLayout(new GridBagLayout());
        namesAndFields.add(nameLabel, getConstraints(0,0));
        namesAndFields.add(nameField, getConstraints(1,0));
        namesAndFields.add(addressLabel, getConstraints(0,1));
        namesAndFields.add(addressField, getConstraints(1,1));
        namesAndFields.add(phoneLabel, getConstraints(0,2));
        namesAndFields.add(phoneField, getConstraints(1,2));
        namesAndFields.add(accountNumLabel, getConstraints(0,3));
        namesAndFields.add(accountNumField, getConstraints(1,3));
        namesAndFields.add(balanceLabel, getConstraints(0,4));
        namesAndFields.add(balanceField, getConstraints(1,4));
        namesAndFields.add(wDLabel, getConstraints(0,5));
        namesAndFields.add(wDField, getConstraints(1,5));
        //---BUTTON PANELS------------------------------------
        JPanel buttonsP = new JPanel();
        
        searchCustomerB = new JButton("Search Customer");
        searchCustomerB.setPreferredSize(dim);
        searchCustomerB.addActionListener(e -> searchButtonClicked());
        previousB = new JButton("Previous Customer");
        previousB.setPreferredSize(dim);
        previousB.addActionListener(e -> previousClicked());
        nextB = new JButton("Next Customer");
        nextB.setPreferredSize(dim);
        nextB.addActionListener(e -> nextButtonClicked());
        addB = new JButton("Add Customer");
        addB.setPreferredSize(dim);
        addB.addActionListener(e -> addButtonClicked());
        updateB = new JButton("Update Customer");
        updateB.setPreferredSize(dim);
        updateB.addActionListener(e -> updateClicked());
        openAcctB = new JButton("Open Account");
        openAcctB.setPreferredSize(dim);
        openAcctB.addActionListener(e -> openAcctButtonClicked());
        
        buttonsP.setLayout(new GridBagLayout());
        buttonsP.add(searchCustomerB, getConstraints(0,0));
        buttonsP.add(previousB, getConstraints(0,1));
        buttonsP.add(nextB, getConstraints(0,2));
        buttonsP.add(addB, getConstraints(0,3));
        buttonsP.add(updateB, getConstraints(0,4));
        buttonsP.add(openAcctB, getConstraints(0,5));
        
        JPanel buttonsP2 = new JPanel();
        depositB = new JButton("Deposit");
        depositB.setPreferredSize(dim);
        depositB.addActionListener(e -> depositButtonClicked());
        withdrawB = new JButton("Withdraw");
        withdrawB.setPreferredSize(dim);
        withdrawB.addActionListener(e -> withdrawButtonClicked());
        
        buttonsP2.setLayout(new GridBagLayout());
        buttonsP2.add(depositB, getConstraints(0,0));
        buttonsP2.add(withdrawB, getConstraints(0,1));
        
        JPanel buttonsP3 = new JPanel();
        calcInterestB = new JButton("Calculate Interest");
        calcInterestB.setPreferredSize(dim);
        calcInterestB.addActionListener(e -> calculateInterestClicked());
        
        buttonsP3.setLayout(new GridBagLayout());
        buttonsP3.add(calcInterestB, getConstraints(0,0));
        //---INTEREST PANEL-----------------------------------
        JPanel interest = new JPanel();
        JLabel interestMonthL = new JLabel("Interest Month: ");
        interestField = new JTextField();
        interestField.setPreferredSize(dim2);
        JLabel calcInterestL = new JLabel("Calculated Interest: ");
        calcInterestField = new JTextField();
        calcInterestField.setPreferredSize(dim2);
        calcInterestField.setEditable(false);
        
        interest.setLayout(new GridBagLayout());
        interest.add(interestMonthL, getConstraints(0,0));
        interest.add(interestField, getConstraints(1,0));
        interest.add(calcInterestL, getConstraints(0,1));
        interest.add(calcInterestField, getConstraints(1,1));
        
        //---MAIN PANEL---------------------------------------
        overallPanel.setLayout(new GridBagLayout());
        overallPanel.add(namesAndFields, getConstraints(0,0));
        overallPanel.add(buttonsP, getConstraints(1,0));
        overallPanel.add(buttonsP2, getConstraints(1,1));
        overallPanel.add(buttonsP3, getConstraints(1,2));
        overallPanel.add(interest, getConstraints(0,1));
        add(overallPanel, BorderLayout.CENTER);
        setResizable(false);
        
        setSize(450,350);
    }

    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 3, 5);
        c.gridx = x;
        c.gridy = y;
        return c;
    }

    public static void main(String[] args) {
        // TODO code application logic here
            java.awt.EventQueue.invokeLater(() -> {
            BankingApp frame = new BankingApp();
            frame.setVisible(true);
        });
    }
    
    private static long getNewNumber() {
        long newNumber = nextAccountNumber;
        nextAccountNumber++;
        return newNumber;
    }

    private void previousClicked() {
        List<Account> accounts = accountFile.getAll();
        Account a1=null;
        int index;
        for(int i = 0; i <accounts.size(); i--)
        {
            Account a2 = accounts.get(i);
            a1=a2;
            nameField.setText(a2.getName());
            addressField.setText(a2.getAddress());
            phoneField.setText(a2.getPhone());
            accountNumField.setText(a2.getAccountNumber());
            balanceField.setText(Double.toString(a2.getBalance()));
            interestField.setText(Double.toString(a2.getInterestRate()));
        }
    }

    private void nextButtonClicked() {
        List<Account> accounts = accountFile.getAll();
        Account a1=null;
        int index;
        for(int i = 0; i <accounts.size(); i++)
        {
            Account a2 = accounts.get(i);
            a1=a2;
            nameField.setText(a2.getName());
            addressField.setText(a2.getAddress());
            phoneField.setText(a2.getPhone());
            accountNumField.setText(a2.getAccountNumber());
            balanceField.setText(Double.toString(a2.getBalance()));
            interestField.setText(Double.toString(a2.getInterestRate()));
        }
        
    }

    public void searchButtonClicked() {
        Validation v = new Validation();
        String errorMsg = "";
        errorMsg += v.isPresent(nameField.getText(), 
                "Name");
        if (errorMsg.isEmpty()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader("accounts.txt"));
                String n = nameField.getText();
                Account a = accountFile.get(n);
                nameField.setText(a.getName());
                addressField.setText(a.getAddress());
                phoneField.setText(a.getPhone());
                accountNumField.setText(a.getAccountNumber());
                balanceField.setText(Double.toString(a.getBalance()));
                interestField.setText(Double.toString(a.getInterestRate()));
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, ex, 
                    "File Not Located", JOptionPane.ERROR_MESSAGE);
            }    
        } else {
            JOptionPane.showMessageDialog(this, errorMsg, 
                    "Invalid Data", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openAcctButtonClicked() {
        Validation v = new Validation();
        String errorMsg = "";
        errorMsg += v.isPresent(nameField.getText(), 
                "Name");
        errorMsg += v.isPresent(addressField.getText(), 
                "Address");
        errorMsg += v.isPresent(phoneField.getText(), 
                "Phone Number");
        errorMsg += v.isPresent(phoneField.getText(), 
                "Phone Number");
        errorMsg += v.isDouble(wDField.getText(), 
                "Balance");
        
        if (errorMsg.isEmpty()) {
            String name = nameField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String account = accountNumField.getText();
            double balance = Double.parseDouble(wDField.getText());
            Account a = new Account(name, address, phone, account, balance, 0.9);
            a.setName(name);
            a.setAddress(address);
            a.setPhone(phone);
            a.setAccountNumber(account);
            a.setBalance(balance);
            a.setInterestRate(a.getInterestRate());
            accountFile.add(a);
            if (true) {JOptionPane.showMessageDialog(this,
            "Success!", "Alert", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, errorMsg, 
                    "Invalid Data", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addButtonClicked() {
        Validation v = new Validation();
        String errorMsg = "";
        errorMsg += v.isPresent(nameField.getText(), 
                "Name");
        errorMsg += v.isPresent(addressField.getText(), 
                "Address");
        errorMsg += v.isPresent(phoneField.getText(), 
                "Phone Number");
        errorMsg += v.isPresent(accountNumField.getText(), 
                "Account Number");
        errorMsg += v.isDouble(wDField.getText(), 
                "Balance");
        
        if (errorMsg.isEmpty()) {
            String name = nameField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String account = accountNumField.getText();
            double balance = Double.parseDouble(wDField.getText());
            Account a = new Account(name, address, phone, account, balance, 0.9);
            a.setName(name);
            a.setAddress(address);
            a.setPhone(phone);
            a.setAccountNumber(account);
            a.setBalance(balance);
            a.setInterestRate(a.getInterestRate());
            accountFile.add(a);
            if (true) {
                JOptionPane.showMessageDialog(this,
                "Name Added To Account", "Alert", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, errorMsg, 
                    "Invalid Data", JOptionPane.ERROR_MESSAGE);
            }
        }

    private void updateClicked() {
        Validation v = new Validation();
        String errorMsg = "";
        errorMsg += v.isPresent(nameField.getText(), 
                "Name");
        errorMsg += v.isPresent(addressField.getText(), 
                "Address");
        errorMsg += v.isPresent(phoneField.getText(), 
                "Phone Number");
        errorMsg += v.isPresent(accountNumField.getText(), 
                "Account Number");
        
        if (errorMsg.isEmpty()) {
            List<Account> accounts = accountFile.getAll();
            Account a1=null;
            int index;
            //This will give the right account
            for(int i = 0; i <accounts.size(); i++)
            {
                Account a2 = accounts.get(i);
                if(accountNumField.getText().equalsIgnoreCase(a2.getAccountNumber()))
                {
                    a1=a2;
                    //save the index location of the account
                    index = i;
                }
            }
            //Once the account is pulled out set the variables to the updated data
            a1.setName(nameField.getText());//example
            a1.setAddress(addressField.getText());
            a1.setPhone(phoneField.getText());
            a1.setAccountNumber(accountNumField.getText());
            a1.getBalance();
            a1.getInterestRate();
            accountFile.update(a1);
            if (true) {
                JOptionPane.showMessageDialog(this,
                "Account Updated", "Alert", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, errorMsg, 
                    "Invalid Data", JOptionPane.ERROR_MESSAGE);
            }
        }

    private void depositButtonClicked() {
        Validation v = new Validation();
        String errorMsg = "";
        errorMsg += v.isPresent(accountNumField.getText(), 
                "Account Number");
        errorMsg += v.isDouble(wDField.getText(), 
                "Balance");
        
        if (errorMsg.isEmpty()) {
            List<Account> accounts = accountFile.getAll();
            Account a1=null;
            int index;
            //This will give the right account
            for(int i = 0; i <accounts.size(); i++)
            {
                Account a2 = accounts.get(i);
                if(accountNumField.getText().equalsIgnoreCase(a2.getAccountNumber()))
                {
                    a1=a2;
                    //save the index location of the account
                    index = i;
                }
            }
            //Once the account is pulled out set the variables to the updated data
            a1.getName();//example
            a1.getAddress();
            a1.getPhone();
            a1.setAccountNumber(accountNumField.getText());
            a1.getBalance();
            a1.deposit(Double.parseDouble(wDField.getText()));
            accountFile.update(a1);
            if (true) {
                JOptionPane.showMessageDialog(this,
                "Deposit Successful!" + a1.getName(), "Alert", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, errorMsg, 
                    "Invalid Data", JOptionPane.ERROR_MESSAGE);
            }
    }

    private void withdrawButtonClicked() {
        Validation v = new Validation();
        String errorMsg = "";
        errorMsg += v.isPresent(accountNumField.getText(), 
                "Account Number");
        errorMsg += v.isDouble(wDField.getText(), 
                "Balance");
        
        if (errorMsg.isEmpty()) {
            List<Account> accounts = accountFile.getAll();
            Account a1=null;
            int index;
            //This will give the right account
            for(int i = 0; i <accounts.size(); i++)
            {
                Account a2 = accounts.get(i);
                if(accountNumField.getText().equalsIgnoreCase(a2.getAccountNumber()))
                {
                    a1=a2;
                    //save the index location of the account
                    index = i;
                }
            }
            //Once the account is pulled out set the variables to the updated data
            a1.getName();//example
            a1.getAddress();
            a1.getPhone();
            a1.setAccountNumber(accountNumField.getText());
            a1.getBalance();
            a1.withdraw(Double.parseDouble(wDField.getText()));
            accountFile.update(a1);
            if (true) {
                JOptionPane.showMessageDialog(this,
                "Withdrawal Successful " + a1.getName(), "Alert", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, errorMsg, 
                    "Invalid Data", JOptionPane.ERROR_MESSAGE);
            }
    }

    private void calculateInterestClicked() {
        Validation v = new Validation();
        String errorMsg = "";
        errorMsg += v.isPresent(accountNumField.getText(), 
                "Account Number");
        errorMsg += v.isInteger(interestField.getText(), 
                "Interest Month");
        
        if (errorMsg.isEmpty()) {
            List<Account> accounts = accountFile.getAll();
            Account a1=null;
            int index;
            //This will give the right account
            for(int i = 0; i <accounts.size(); i++)
            {
                Account a2 = accounts.get(i);
                if(accountNumField.getText().equalsIgnoreCase(a2.getAccountNumber()))
                {
                    a1=a2;
                    //save the index location of the account
                    index = i;
                }
            }
            //Once the account is pulled out set the variables to the updated data
            a1.getName();//example
            a1.getAddress();
            a1.getPhone();
            a1.setAccountNumber(accountNumField.getText());
            a1.getBalance();
            accountFile.update(a1);
            double a = a1.getInterestRate();
            double s = Double.parseDouble(interestField.getText());
            double c = (a/s)/100;
            calcInterestField.setText(Double.toString(c));
            if (true) {
                JOptionPane.showMessageDialog(this,
                "Success!", "Alert", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, errorMsg, 
                    "Invalid Data", JOptionPane.ERROR_MESSAGE);
            }
    }
    
}
