package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    /**
     * 
     * @param amount
     */

    public static boolean isAmountValid(double amount){
        //negaitve
        if(amount<0){
            return false;
        }
        //0
        if(amount==0){
            return true;
        }
        //converts it to a string and counts the number of numbers after the decimal
        String str=Double.toString(amount);
        for(int i=0; i<str.length();i++){
            if(str.contains(".")){
                String decimal=str.substring(str.lastIndexOf(".")+1);
                double dvalues=decimal.length();
                if(dvalues<=2){
                    return true;
                }
                return false;
            }
            
        }
        
        return true;
    }

    /**
     * Deposits money into the bank account
     * @param amount
     * @throws IllegalArgumentException
     */
    public void deposit(double amount){
        //checks if amount is valid
        if(isAmountValid(amount)){
            balance += amount;
        }
        else{
            throw new IllegalArgumentException("Invalid Deposit Amount");
        }
    }

    public static void transfer(BankAccount account1, BankAccount account2, double amount) throws InsufficientFundsException{

        if(isAmountValid(amount)){
            //sending money
            account1.withdraw(amount);

            //receiving money
            account2.deposit(amount);
        }

        else{
            throw new IllegalArgumentException("Invalid Amount");
        }
            //checks to make sure they're two different accounts
        if(account1 == account2){
            throw new IllegalArgumentException("Same account");
        }

    }
    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * if the withdraw amount is greater than than the balance then it should throw an 
     * InsufficientFundsException and the balance is unchanged
     * if the withdraw amount is less than 0 then it should print an error
     * and the balance is unchanged
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        //Checks if amount is valid
        if(isAmountValid(amount)){
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }
    else{
        throw new IllegalArgumentException("Invalid Amount");
    }  
    }


    public static boolean isEmailValid(String email){
        //checks if there's an @
        if (email.indexOf('@') == 0){
            return false;
        }
        //invalid characters
        else if (email.contains("$")==true||email.contains("%")==true||email.contains("&")==true){
            return false;
        }
        //if there's no period
        else if (!email.contains(".")==true){
            return false;
        }
        //if it starts with an @
        if (email.indexOf('@') == -1 || email.indexOf('@') == 0){
            return false;
        }
        else {
            //if there is more than 1 .
            int count = 0;
            for(int i = 0; i<email.length(); i++){
                if(email.charAt(i) == '.'){
                    count++;
                }
            }
            //if there's an @ after the period
            if(count > 1 && email.indexOf(".")+1==email.indexOf("@") )
                return false;
            
            else if(count>1 && email.indexOf("@")<email.indexOf(".")){
                return false;
            }
        }
        //if theres more than 3 letters after the period
        if(email.lastIndexOf('.') > email.length() - 3){
            return false;
        }
        else {
            
            int count = 0;
            for(int i = 0; i<email.length(); i++){
                if(email.charAt(i) == '@'){
                    count++;
                }
            }
            //if there's more than 1 @
            if(count > 1)
                return false;
        }
        return true;
    }
}