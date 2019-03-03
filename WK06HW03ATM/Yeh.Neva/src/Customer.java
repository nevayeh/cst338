/**
 * Title: Customer.java
 * Author: Neva Yeh
 * Date: March 3, 2019
 * Abstract: This class represents a person who is interacting with an ATM machine.
 *           It has a couple of functions to help the ATM class with validation as
 *           well as to help simulate complete transactions.
 */

public class Customer {

    /*
     * =====================================================
     *                       FIELDS
     * =====================================================
     */

    private String name;
    private int pin;
    private double balance;
    private String bank;

    /*
     * =====================================================
     *                    CONSTRUCTOR
     * =====================================================
     */

    public Customer(String name, int pin, double balance, String bank) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.bank = bank;
    }

    /*
     * =====================================================
     *                GENERAL FUNCTIONS
     * =====================================================
     */

    /**
     * Validates whether the given pin matches the customer's pin
     *
     * @param pin
     *
     * @return
     */
    public boolean checkPin(int pin) {
        if(this.pin == pin) {
            return true;
        }
        return false;
    }

    /**
     * Validates whether the customer has enough money
     * (Used with ATM deposit)
     *
     * @param balance - money to be deposited
     *
     * @return - (boolean) returns true if the customer has enough money
     *                     returns false if the customer does not have enough money
     */
    public boolean checkBalance(double balance) {
        if(this.balance >= balance) {
            return true;
        }
        return false;
    }

    /**
     * Adds money to the customer's balance
     * (Used with successful ATM withdrawal)
     *
     * @param balance - money to be added (from withdrawal)
     */
    public void addMoney(double balance) {
        this.balance += balance;
    }

    /**
     * Removes money from the customer's balance
     * (Used with successful ATM deposit)
     *
     * @param balance - money to be taken (from deposit)
     */
    public void removeMoney(double balance) {
        this.balance -= balance;
    }


    /*
     * =====================================================
     *                OVERRIDEN FUNCTIONS
     * =====================================================
     */

    /**
     * (Override)
     * Returns information about this specific customer
     *
     * @return - (String) returns the name of this customer with their
     *                    balance (formatted to show 2 decimal points)
     */
    @Override
    public String toString() {
        return name + ": Balance $" + String.format("%.2f", balance);
    }


    /*
     * =====================================================
     *                GETTERS AND SETTERS
     * =====================================================
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
