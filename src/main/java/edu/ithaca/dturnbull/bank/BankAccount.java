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
        if(amount<0){
            return false;
        }
        if(amount==0){
            return true;
        }
        String str=Double.toString(amount);
        for(int i=0; i<str.length();i++){
            if(str.contains(".")){
                String decimal=str.substring(str.lastIndexOf(".")+1);
                double dvalues=Double.valueOf(decimal);
                if(dvalues>2){
                    return false;
                }
                return true;
            }
            
        }
        
        return true;
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
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == 0){
            return false;
        }
        else if (email.contains("$")==true||email.contains("%")==true||email.contains("&")==true){
            return false;
        }
        else if (!email.contains(".")==true){
            return false;
        }
        
        if (email.indexOf('@') == -1 || email.indexOf('@') == 0){
            return false;
        }
        else {
            //if there is more than 1 @
            int count = 0;
            for(int i = 0; i<email.length(); i++){
                if(email.charAt(i) == '.'){
                    count++;
                }
            }

            if(count > 1 && email.indexOf(".")+1==email.indexOf("@") )
                return false;

            else if(count>1 && email.indexOf("@")<email.indexOf(".")){
                return false;
            }
        }
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

            if(count > 1)
                return false;
        }
        return true;
    }
}