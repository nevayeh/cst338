/**
 * Title: ATMTest.java
 * Author: Neva Yeh
 * Date: March 3, 2019
 * Abstract: This is a JUnit Test class using JUNit 5.
 *           All test cases are things that have been tested with the given
 *           client code, named ATMDemo.java
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {

    /*
     * DOESN'T SEEM TO WORK
     * (says 'cannot resolve symbol')
     * ^ out of scope?
     */
    @BeforeEach
    void setUp() {
        ATM machine1 = new ATM("OtterUnion");
        ATM machine2 = new ATM(200, "BOA", "Library");
    }

    /*
     * =====================================================
     *                GENERAL FUNCTIONS
     * =====================================================
     */

    @Test
    void machineDefaultBalance() {
        ATM machine1 = new ATM("OtterUnion");
        ATM machine2 = new ATM(200, "BOA", "Library");

        assertEquals(true, machine1.getMachineBalance() == 100
                        && machine2.getMachineBalance() == 100);
    }

    @Test
    void machineDefaultLocation() {
        ATM machine1 = new ATM("OtterUnion");

        assertEquals("UNKNOWN", machine1.getMachineLocation());
    }

    @Test
    void equalityCheck() {
        ATM machine1 = new ATM("OtterUnion");
        ATM machine2 = new ATM(200, "BOA", "Library");

        assertEquals(false , machine1.equals(machine2));
    }

    @Test
    void setATMCheckSerialNumber() {
        ATM machine1 = new ATM("OtterUnion");

        machine1.setATM(100, "BIT");

        assertEquals(100, machine1.getSerialNumber());
    }

    @Test
    void setATMCheckLocation() {
        ATM machine1 = new ATM("OtterUnion");

        machine1.setATM(100, "BIT");

        assertEquals("BIT", machine1.getMachineLocation());
    }

    @Test
    void addFund() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        machine1.addFund(400);

        assertEquals(500.00, machine1.getMachineBalance());
    }


    @Test
    void isCustomer() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        assertEquals(true, machine1.isCustomer("Alice"));
    }


    /*
     * =====================================================
     *                WITHDRAWAL FUNCTION
     * =====================================================
     */

    // TESTING WITHDRAWAL FAIL
    @Test
    void widthdrawalFail() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        machine1.withdrawal("Alice", 7777, 10.50);

        assertEquals(1, machine1.getWithdrawalFailCount());
    }

    // TESTING WITHDRAWAL SUCCESS (MACHINE LOGIC)
    @Test
    void withdrawalMachineBalance() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        machine1.withdrawal("Alice", 1234, 10);

        assertEquals(90, machine1.getMachineBalance());
    }

    // TESTING WITHDRAWAL SUCCESS (CUSTOMER LOGIC)
    @Test
    void withdrawalCustomerBalance() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");
        Customer alice = machine1.getCustomer("Alice");

        machine1.withdrawal("Alice", 1234, 10);

        assertEquals(5010, alice.getBalance());
    }

    @Test
    void withdrawalSuccessCount() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        machine1.withdrawal("Alice", 7777, 10.50);
        machine1.withdrawal("Robert", 2323, 10.50);
        machine1.withdrawal("Alice", 1234, 10000);
        machine1.withdrawal("Alice", 1234, 10);
        machine1.withdrawal("Alice", 1234, 2000);

        assertEquals(1, machine1.getWithdrawalSuccessCount());
    }

    @Test
    void withdrawalFailCount() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        machine1.withdrawal("Alice", 7777, 10.50);
        machine1.withdrawal("Robert", 2323, 10.50);
        machine1.withdrawal("Alice", 1234, 10000);
        machine1.withdrawal("Alice", 1234, 10);
        machine1.withdrawal("Alice", 1234, 2000);

        assertEquals(4, machine1.getWithdrawalFailCount());
    }


    @Test
    void withdrawalCount() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        machine1.withdrawal("Alice", 7777, 10.50);
        machine1.withdrawal("Robert", 2323, 10.50);
        machine1.withdrawal("Alice", 1234, 10000);
        machine1.withdrawal("Alice", 1234, 10);
        machine1.withdrawal("Alice", 1234, 2000);

        assertEquals(5, machine1.getWithdrawalCount());
    }

    @Test
    void totalTransactionCount() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        machine1.withdrawal("Alice", 7777, 10.50);
        machine1.withdrawal("Robert", 2323, 10.50);
        machine1.withdrawal("Alice", 1234, 10000);
        machine1.withdrawal("Alice", 1234, 10);
        machine1.withdrawal("Alice", 1234, 2000);

        assertEquals(5, machine1.getTransactionCount());
    }

    /*
     * =====================================================
     *                   DEPOSIT FUNCTION
     * =====================================================
     */

    // TESTING WITHDRAWAL FAIL
    @Test
    void DepositFail() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        machine1.deposit("Alice", 7777, 10.50);

        assertEquals(1, machine1.getDepositFailCount());
    }

    // TESTING WITHDRAWAL SUCCESS (MACHINE LOGIC)
    @Test
    void depositMachineBalance() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        machine1.setMachineBalance(490);
        machine1.deposit("Alice", 1234, 10);

        assertEquals(500, machine1.getMachineBalance());
    }

    // TESTING WITHDRAWAL SUCCESS (CUSTOMER LOGIC)
    @Test
    void depositCustomerBalance() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");
        Customer alice = machine1.getCustomer("Alice");

        machine1.setMachineBalance(500);
        machine1.deposit("Alice", 1234, 10);

        assertEquals(4990, alice.getBalance());

    }

    @Test
    void depositSuccessCount() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        machine1.deposit("Alice", 1234, 10);

        assertEquals(1, machine1.getDepositSuccessCount());
    }

    @Test
    void depositFailCount() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        machine1.deposit("Alice", 1234, 10);

        assertEquals(0, machine1.getDepositFailCount());
    }

    @Test
    void depositCount() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        machine1.deposit("Alice", 1234, 10);

        assertEquals(1, machine1.getDepositCount());
    }

    @Test
    void totalTransactionCount2() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        machine1.withdrawal("Alice", 7777, 10.50);
        machine1.withdrawal("Robert", 2323, 10.50);
        machine1.withdrawal("Alice", 1234, 10000);
        machine1.withdrawal("Alice", 1234, 10);
        machine1.withdrawal("Alice", 1234, 2000);
        machine1.deposit("Alice", 1234, 10);

        assertEquals(6, machine1.getTransactionCount());
    }

    /*
     * =====================================================
     *                   TRANSFER FUNCTION
     * =====================================================
     */

    @Test
    void transferPass() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        boolean result = machine1.transfer("Alice", 1234, 10, "Tom", 2000);

        assertEquals(true, result);
    }

    @Test
    void transferFail() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        boolean result = machine1.transfer("Chris", 8787, 10, "Tom", 2000);

        assertEquals(false, result);
    }

    @Test
    void transferSuccessCount() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        boolean placeHolder = machine1.transfer("Alice", 1234, 10, "Tom", 2000);
        boolean placeHolder2 = machine1.transfer("Chris", 8787, 10, "Tom", 2000);

        assertEquals(1, machine1.getTransferSuccessCount());
    }

    @Test
    void transferFailCount() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        boolean placeHolder = machine1.transfer("Alice", 1234, 10, "Tom", 2000);
        boolean placeHolder2 = machine1.transfer("Chris", 8787, 10, "Tom", 2000);

        assertEquals(1, machine1.getTransferFailCount());
    }

    @Test
    void transferCount() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        boolean placeHolder = machine1.transfer("Alice", 1234, 10, "Tom", 2000);
        boolean placeHolder2 = machine1.transfer("Chris", 8787, 10, "Tom", 2000);

        assertEquals(2, machine1.getTransferCount());
    }

    @Test
    void totalTransactionCount3() {
        ATM machine1 = new ATM(100, "OtterUnion", "BIT");

        machine1.withdrawal("Alice", 7777, 10.50);
        machine1.withdrawal("Robert", 2323, 10.50);
        machine1.withdrawal("Alice", 1234, 10000);
        machine1.withdrawal("Alice", 1234, 10);
        machine1.withdrawal("Alice", 1234, 2000);

        machine1.deposit("Alice", 1234, 10);

        boolean placeHolder1 = machine1.transfer("Alice", 1234, 10, "Tom", 2000);
        boolean placeHolder2 = machine1.transfer("Chris", 8787, 10, "Tom", 2000);

        assertEquals(8, machine1.getTransactionCount());
    }

}