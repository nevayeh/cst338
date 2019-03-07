/**
 * Title: ATM.java
 * Author: Neva Yeh
 * Date: March 3, 2019
 * Abstract: This class simulates an ATM machine. It works with the Customer class
 *           to simulate transactions between a specific set of customers--in this
 *           case: (a fixed 10) students on a campus.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class ATM {

    /*
     * =====================================================
     *                        FIELDS
     * =====================================================
     */

    private int serialNumber;
    private String machineName;
    private String machineLocation;
    private double machineBalance = 100.00;
    private HashMap<String, Customer> customers;

    private int withdrawalSuccessCount = 0;
    private int withdrawalFailCount = 0;
    private int depositSuccessCount = 0;
    private int depositFailCount = 0;
    private int transferSuccessCount = 0;
    private int transferFailCount = 0;

    /*
     * =====================================================
     *                    CONSTRUCTORS
     * =====================================================
     */

    public ATM(String machineName) {
        this(0, machineName, "UNKNOWN");
    }

    // Not used in this case
    public ATM(String machineName, String machineLocation) {
        this(0, machineName, machineLocation);
    }

    public ATM(int serialNumber, String machineName, String machineLocation) {
        this.serialNumber = serialNumber;
        this.machineName = machineName;
        this.machineLocation = machineLocation;
        customers = loadCustomers(machineName);
    }


    /*
     * =====================================================
     *                   ATM FUNCTIONS
     * =====================================================
     */

    /**
     * Changes the ATM's serial number and machine location
     *
     * @param serialNumber - the machine's new serial number
     * @param machineLocation - the machine's new location
     */
    public void setATM(int serialNumber, String machineLocation) {
        this.serialNumber = serialNumber;
        this.machineLocation = machineLocation;
    }

    /**
     * Assumes that an administrator adds funds to a machine
     *
     * @param moneyToAdd - amount of money to be added
     */
    public void addFund(double moneyToAdd) {
        machineBalance += moneyToAdd;
    }

    /**
     * Displays the ATM menu
     * (Not actually interactable in this case)
     */
    public void displayMenu() {
        System.out.println("===== ATM Transaction Menu =====");
        System.out.println("\t1. Withdrawal");
        System.out.println("\t2. Deposit");
        System.out.println("\t3. Transfer");
    }

    /**
     * Displays the machine's status
     * Invokes the (overriden) toString() method of this class
     * Adds on information about transactions
     */
    public void status() {
        System.out.println(this);
        System.out.println(getTransactionCount() + " Transactions so far:");
        System.out.println("\tWithdrawal: " + getWithdrawalCount()
                            + " (" + withdrawalSuccessCount + " success, "
                            + withdrawalFailCount + " fail)");
        System.out.println("\tDeposit: " + getDepositCount()
                            + " (" + depositSuccessCount + " success, "
                            + depositFailCount + " fail)");
        System.out.println("\tTransfer: " + getTransferCount()
                            + " (" + transferSuccessCount + " success, "
                            + transferFailCount + " fail)");
    }

    /**
     * Simulates a withdrawal
     * Validates the customer's pin as well as ensures that the machine has
     * enough money that for the withdrawal
     * Also validates that the customer is associated with the same bank
     * that this specific machine is associated with
     *
     * @param customerName - customer that is trying to withdraw money
     * @param pin - pin of customer
     * @param balance - amount of money that the customer wants to take out
     */
    public void withdrawal(String customerName, int pin, double balance) {
        // Customer exists
        if(isCustomer(customerName)) {
            Customer customer = customers.get(customerName);

            // Successful withdrawal
            if(customer.checkPin(pin) && checkMachineBalance(balance)) {
                System.out.println("Succeed - withdrawal");

                machineBalance -= balance;
                customer.removeMoney(balance);

                withdrawalSuccessCount++;
            }
            // UNsuccessful withdrawal
            else {
                System.out.println("Fail - withdrawal");
                withdrawalFailCount++;
            }
        // Customer does NOT exist
        } else {
            System.out.println("Fail - withdrawal");
            withdrawalFailCount++;
        }
    }

    /**
     * Simulates a deposit
     * Validates the customer's pin as well as ensures that the customer has
     * enough funds to deposit
     * Also validates that the customer is associated with the same bank
     * that this specific machine is associated with
     *
     * @param customerName - the customer that is trying to deposit money
     * @param pin - pin of customer
     * @param balance - amout of money that the customer is trying to deposit
     */
    public void deposit(String customerName, int pin, double balance) {
        // Customer exists
        if(isCustomer(customerName)) {
            Customer customer = customers.get(customerName);

            // Successful deposit
            if(customer.checkPin(pin) && customer.checkBalance(balance)) {
                System.out.println("Succeed - deposit");

                machineBalance += balance;
                customer.addMoney(balance);

                depositSuccessCount++;
            }
            // UNsuccessful deposit
            else {
                System.out.println("Fail - deposit");
                depositFailCount++;
            }
        // Customer does NOT exist
        } else {
            System.out.println("Fail - deposit");
            depositFailCount++;
        }
    }

    /**
     * Simulates a transfer between two customers
     * Validates both customers with their respective pins
     * Also validates that both customers are associated with the same bank
     * that this specific machine is associated with
     *
     * @param customer1 - the person giving the money
     * @param pin1 - pin of the person giving money
     * @param balance - amount of money to be transferred
     * @param customer2 - the person receiving the money
     * @param pin2 - pin of the person receiving money
     *
     * @return - (boolean) returns true if the transfer was possible
     *                     returns false if there was an error (not both matching banks, wrong pins, etc)
     */
    public boolean transfer(String customer1, int pin1, double balance, String customer2, int pin2) {
        // Both customers exist
        if(isCustomer(customer1) && isCustomer(customer2)) {
            Customer person1 = customers.get(customer1);
            Customer person2 = customers.get(customer2);

            // Successful transfer
            if(person1.checkPin(pin1)
                    && person2.checkPin(pin2)
                    && person1.checkBalance(balance)) {

                System.out.println("Succeed - transfer");

                person1.removeMoney(balance);
                person2.addMoney(balance);

                transferSuccessCount++;
                return true;
            }
            // UNsuccessful transfer
            else {
                System.out.println("Fail - transfer");
                transferFailCount++;
                return false;
            }
        }
        // Both customers do NOT exist
        // Handles the "different banks" situation
        else {
            System.out.println("Fail - transfer");
            transferFailCount++;
            return false;
        }
    }


    /*
     * =====================================================
     *                   HELPER FUNCTIONS
     * =====================================================
     */

    /**
     * Loads the customers HashMap with all the customers that are associated
     * with the bank that this machine is associated with
     * Takes advantage of the given machine name from constructors
     *
     * @param bankName - name of the bank that is associated with this machine
     *
     * @return - (HashMap<String, Customer>) returns the completed HashMap of
     *                                       all the properly-associated customers
     */
    private static HashMap<String, Customer> loadCustomers(String bankName) {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        HashMap<String, Customer> customers = new HashMap<>();

        allCustomers.add(new Customer("Alice", 1234, 5000, "OtterUnion"));
        allCustomers.add(new Customer("Tom", 2000, 200, "OtterUnion"));
        allCustomers.add(new Customer("Monica", 300, 50, "OtterUnion"));
        allCustomers.add(new Customer("Michael", 7777, 0, "OtterUnion"));
        allCustomers.add(new Customer("John", 8000, 500, "OtterUnion"));
        allCustomers.add(new Customer("Jane", 2222, 500, "OtterUnion"));
        allCustomers.add(new Customer("Robert", 2323, 200, "BOA"));
        allCustomers.add(new Customer("Owen", 4455, 50, "BOA"));
        allCustomers.add(new Customer("Chris", 8787, 10, "BOA"));
        allCustomers.add(new Customer("Rebecca", 8080, 555, "BOA"));

        for(Customer c : allCustomers) {
            if(c.getBank().equals(bankName)) {
                customers.put(c.getName(), c);
            }
        }
        return customers;
    }

    /**
     * Checks if someone is a customer of the bank associated
     * with this machine
     *
     * @param customerName - name of customer to be checked
     *
     * @return - (boolean) returns true if they are a customer of the
     *                        same bank as this machine
     *                     returns false if they are not a customer
     *                        of the same bank as this machine
     */
    public boolean isCustomer(String customerName) {
        if(customers.containsKey(customerName)) {
            return true;
        }
        return false;
    }

    /**
     * Gets Customer object, assuming that the name given is a valid customer
     * Note: No validation in this case because it is already validated
     *       in the client code, but typically this should have
     *       validation (checking isCustomer(name) first)
     *
     * @param customerName - name of customer to get
     *
     * @return - (Customer) returns the Customer object with the given name
     */
    public Customer getCustomer(String customerName) {
        return customers.get(customerName);
    }

    /**
     * Validates whether the machine has enough money
     * (Used with ATM withdrawal)
     *
     * @param machineBalance - money to be withdrawn
     *
     * @return - (boolean) returns true if the machine has enough money
     *                     returns false if the machine does not have enough money
     */
    private boolean checkMachineBalance(double machineBalance) {
        if(this.machineBalance >= machineBalance) {
            return true;
        }
        return false;
    }


    /*
     * =====================================================
     *                OVERRIDDEN FUNCTIONS
     * =====================================================
     */

    /**
     * Verifies if another object is equal to this object
     * Typecasts to this class and compares every single field
     *
     * @param other - other object to be compared to this one
     *
     * @return - (boolean) returns true if both objects are exactly the same
     *                     returns false if not exactly the same (besides memory location)
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if(!(other instanceof ATM)) {
            return false;
        }

        ATM o = (ATM)other;

        return serialNumber == o.serialNumber
                && machineName.equals(o.machineName)
                && machineLocation.equals(o.machineLocation)
                && machineBalance == o.machineBalance
                && customers.equals(o.customers)
                && withdrawalSuccessCount == o.withdrawalSuccessCount
                && withdrawalFailCount == o.withdrawalFailCount
                && depositSuccessCount == o.depositSuccessCount
                && depositFailCount == o.depositFailCount
                && transferSuccessCount == o.transferSuccessCount
                && transferFailCount == o.transferFailCount;
    }

    /**
     * (Override)
     * Returns information about the machine
     * Formats the Balance to always show 2 decimal points
     *
     * @return - (String) specifically formatted information about this machine
     */
    @Override
    public String toString() {
        return "Serial Number: " + serialNumber
                + "\nBank Name: " + machineName
                + "\nBank Location: " + machineLocation
                + "\nBalance: " + String.format("%.2f", machineBalance);
    }


    /*
     * =====================================================
     *                GETTERS AND SETTERS
     * =====================================================
     */

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineLocation() {
        return machineLocation;
    }

    public void setMachineLocation(String machineLocation) {
        this.machineLocation = machineLocation;
    }

    public double getMachineBalance() {
        return machineBalance;
    }

    public void setMachineBalance(double machineBalance) {
        this.machineBalance = machineBalance;
    }

    public HashMap<String, Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(HashMap<String, Customer> customers) {
        this.customers = customers;
    }

    public int getWithdrawalSuccessCount() {
        return withdrawalSuccessCount;
    }

    public int getWithdrawalFailCount() {
        return withdrawalFailCount;
    }

    public int getDepositSuccessCount() {
        return depositSuccessCount;
    }

    public int getDepositFailCount() {
        return depositFailCount;
    }

    public int getTransferSuccessCount() {
        return transferSuccessCount;
    }

    public int getTransferFailCount() {
        return transferFailCount;
    }

    public int getTransactionCount() {
        return withdrawalSuccessCount + withdrawalFailCount
                + depositSuccessCount + depositFailCount
                + transferSuccessCount + transferFailCount;
    }

    public int getWithdrawalCount() {
        return withdrawalSuccessCount + withdrawalFailCount;
    }

    public int getDepositCount() {
        return depositSuccessCount + depositFailCount;
    }

    public int getTransferCount() {
        return transferSuccessCount + transferFailCount;
    }
}
