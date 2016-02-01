package com.harvest.actors;

/**
 * Created by Patty on 2/1/2016.
 */

public class Wallet {
    //NEEDS TESTING
    int cashWorth;

    public Wallet(int currentCashWorth){
        cashWorth = currentCashWorth;
    }

    public int getTotalValue(){
        return cashWorth;
    }

    public String getWalletAmount(){
        String bills = String.valueOf(cashWorth/PlayerVars.CASH_VALUE);
        String coins = String.valueOf(cashWorth%PlayerVars.CASH_VALUE);

        return "$" + bills + "." + coins;
    }

    public void addCash(int addedValue){
        cashWorth += addedValue;
    }

    public boolean makeTransaction(int value){
        if(getTotalValue() < value){
            return false;
        }
        cashWorth -= value;
        return true;
    }
}
