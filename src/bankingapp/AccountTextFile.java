/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapp;

/**
 *
 * @author Patsf
 */
import java.io.*;
import java.nio.file.*;
import java.util.*;


public final class AccountTextFile implements DAO<Account> {
    private List<Account> accounts = null;
    private Path accountsPath = null;
    private File accountsFile = null;
    private final String FIELD_SEP = "\t";

    public AccountTextFile() {
        accountsPath = Paths.get("accounts.txt");
        accountsFile = accountsPath.toFile();
        accounts = this.getAll();
    }

    @Override
    public List<Account> getAll() {
        // if the products file has already been read, don't read it again
        if (accounts != null) {
            return accounts;
        }

        accounts = new ArrayList<>();
        if (Files.exists(accountsPath)) {
            try (BufferedReader in = new BufferedReader(
                                     new FileReader(accountsFile))) {

                // read products from file into array list
                String line = in.readLine();
                while (line != null) {
                    String[] fields = line.split(FIELD_SEP);
                    String name = fields[0];
                    String address = fields[1];
                    String phone = fields[2];
                    String accountNumber = fields[3];
                    String balance = fields[4];
                    String interestRate = fields[5];

                    Account a = new Account(
                            name, address, phone, accountNumber,
                            Double.parseDouble(balance),
                            Double.parseDouble(interestRate));
                    accounts.add(a);

                    line = in.readLine();
                }
            } catch (IOException e) {
                System.out.println(e);
                return null;
            }
        } else {
            System.out.println(
                    accountsPath.toAbsolutePath() + " doesn't exist.");
            return null;            
        }
        return accounts;
    }

    @Override
    public Account get(String name) {
        for (Account a : accounts) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    private boolean saveAll() {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                               new FileWriter(accountsFile)))) {

            // write all products in the array list
            // to the file
            for (Account a : accounts) {
                out.print(a.getName() + FIELD_SEP);
                out.print(a.getAddress() + FIELD_SEP);
                out.print(a.getPhone() + FIELD_SEP);
                out.print(a.getAccountNumber() + FIELD_SEP);
                out.print(a.getBalance() + FIELD_SEP);
                out.println(a.getInterestRate());
            }
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
    
    @Override
    public boolean add(Account a) {
        accounts.add(a);
        return this.saveAll();
    }

    @Override
    public boolean delete(Account a) {
        accounts.remove(a);
        return this.saveAll();
    }

    @Override
    public boolean update(Account newAccount) {
        // get the old product and remove it
        Account oldAccount = this.get(newAccount.getName());
        int i = accounts.indexOf(oldAccount);
        accounts.remove(i);

        // add the updated product
        accounts.add(i, newAccount);

        return this.saveAll();
    }
}