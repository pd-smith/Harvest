package com.harvest.actors;

import java.util.ArrayList;

/**
 * Created by Patty on 2/1/2016.
 */
public class Inventory {

    ArrayList<InventoryItem> list;
    PlayerState player;
    int currentWeight;

    public Inventory(PlayerState currentPlayer){
        list = new ArrayList<InventoryItem>();
        player = currentPlayer;
    }

    public boolean addItem(InventoryItem item){
        /*if((item.getWeight() + currentWeight) > player){
            return false;
        }*/
        return true;
    }


}
