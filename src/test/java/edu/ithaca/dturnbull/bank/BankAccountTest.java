package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse( BankAccount.isEmailValid(""));         // empty string

        assertFalse( BankAccount.isEmailValid("@mansion.com"));         // no prefix equivalence
        assertFalse( BankAccount.isEmailValid("tdog@gmail"));         // incomplete suffix equivalence
        assertFalse( BankAccount.isEmailValid("@vim.x"));         // no prefix, invalid suffix border
        assertFalse( BankAccount.isEmailValid("tjones$@gmail.com"));         // invalid character in in prefix equivalence
        assertFalse( BankAccount.isEmailValid("free%money@moneyman"));         // invalid character in in prefix, invalid suffix border

        assertTrue( BankAccount.isEmailValid("abs@man.com"));         //valid email: Proper prefix, proper suffix
        assertTrue( BankAccount.isEmailValid("Mr.Incredible@supers.com"));         //Valid email: prefix with period
        assertFalse( BankAccount.isEmailValid("dewey@dec.imal.com"));         // Invalid character in suffix

        assertFalse( BankAccount.isEmailValid("terry@crew$.org"));         // Invalid suffix character equivalence, border
        assertFalse( BankAccount.isEmailValid("richguy@money$talks.com"));         // Invalid suffix character equivalence, middle
        assertFalse( BankAccount.isEmailValid("terry@&crews.org"));         // Invalid suffix character equivalence border

        
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