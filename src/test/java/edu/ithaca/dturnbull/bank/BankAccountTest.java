package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001); //middle case

        BankAccount bankAccount2 = new BankAccount("a@b.com", 0);

        assertEquals(0, bankAccount2.getBalance(), 0.001); //zero balance equivalent

        BankAccount bankAccount3 = new BankAccount("a@b.com", 1200.54);

        assertEquals(1200.54, bankAccount3.getBalance(), 0.001); //decimal balance equivalent
    }
    
    @Test 
    void isAmountValidTest(){
        //Positive
        assertTrue(BankAccount.isAmountValid(25));
        assertTrue(BankAccount.isAmountValid(50));
        assertTrue(BankAccount.isAmountValid(75));
        assertTrue(BankAccount.isAmountValid(100));
        assertTrue(BankAccount.isAmountValid(.01));
        assertTrue(BankAccount.isAmountValid(100.01));

        //Negative
        assertFalse(BankAccount.isAmountValid(-25));
        assertFalse(BankAccount.isAmountValid(-50));
        assertFalse(BankAccount.isAmountValid(-75));
        assertFalse(BankAccount.isAmountValid(-100));
        assertFalse(BankAccount.isAmountValid(-.01));
        assertFalse(BankAccount.isAmountValid(-100.01));


        //Over 2 decimal places
        assertFalse(BankAccount.isAmountValid(1.5632));
        assertFalse(BankAccount.isAmountValid(3.67493));
        assertFalse(BankAccount.isAmountValid(12.0000100010001)); 

        //0
        assertTrue(BankAccount.isAmountValid(0.00));
        assertTrue(BankAccount.isAmountValid(0));

    }
    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001); //middle case
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300)); //middle case

        //Withdraw exact balance
        BankAccount bankAccount2 = new BankAccount("a@b.com", 200);
        bankAccount2.withdraw(200);

        assertEquals(0, bankAccount2.getBalance(), 0.001);
        
        //Withdraw 1 cent above, 1 cent below
        BankAccount bankAccount3 = new BankAccount("a@b.com", 200);
        assertThrows(InsufficientFundsException.class, () -> bankAccount3.withdraw(200.01));
        assertEquals(200, bankAccount3.getBalance(), 0.001);

        bankAccount3.withdraw(199.99);
        assertEquals(0.01, bankAccount3.getBalance(), 0.001);

        //Withdraw 0
        BankAccount bankAccount4 = new BankAccount("a@b.com", 200);
        bankAccount4.withdraw(0);

        assertEquals(200, bankAccount4.getBalance(), 0.001);

        //Withdraw -$0.01
        bankAccount4.withdraw(-0.01);
        assertEquals(200.01, bankAccount4.getBalance(), 0.001);
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertTrue( BankAccount.isEmailValid("abs@man.com"));  //valid email: Proper prefix, proper suffix

        assertFalse( BankAccount.isEmailValid(""));         // empty string

        //Only 1 @
        assertTrue( BankAccount.isEmailValid("abs@man.com"));         //valid email: One @
        assertFalse( BankAccount.isEmailValid("Mr@@supers.com"));         //Two @s
        assertFalse( BankAccount.isEmailValid("Mrsupers.com"));         //Zero @s


        //Prefix        
        assertFalse( BankAccount.isEmailValid("Mr.@supers.com"));         //No character after period
        assertTrue( BankAccount.isEmailValid("_Mr@supers.com"));         //No character before underscore
        assertTrue( BankAccount.isEmailValid("Mr.Incredible@supers.com"));         //Valid email: prefix with period in the middle
        assertFalse( BankAccount.isEmailValid("@mansion.com"));         // no prefix equivalence

        assertFalse( BankAccount.isEmailValid("tjones$@gmail.com"));         // invalid character in in prefix equivalence

        //Domain
        assertFalse( BankAccount.isEmailValid("terry@crew$.org"));         // Invalid suffix character equivalence, border
        assertFalse( BankAccount.isEmailValid("richguy@money$talks.com"));         // Invalid suffix character equivalence, middle
        assertFalse( BankAccount.isEmailValid("terry@&crews.org"));         // Invalid suffix character equivalence border

        assertFalse( BankAccount.isEmailValid("tdog@gmail"));         // incomplete suffix equivalence

        assertFalse( BankAccount.isEmailValid("tdog@vim.x"));         // no prefix, invalid suffix border
        assertFalse( BankAccount.isEmailValid("tdog@vim."));         // no prefix, invalid suffix border

        assertFalse( BankAccount.isEmailValid("dewey@dec.imal.com"));         // Invalid character in suffix
        assertFalse( BankAccount.isEmailValid("dewey@%decimal.com"));         // Invalid character in suffix, border
        assertFalse( BankAccount.isEmailValid("dewey@decimal.com$"));         // Invalid character in suffix, border

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}