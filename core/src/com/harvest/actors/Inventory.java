package com.harvest.actors;

import com.harvest.hud_elements.HUDVars;

import java.util.ArrayList;

/**
 * Created by Patty on 2/1/2016.
 */
public class Inventory {

    ArrayList<InventoryItem> list;
    int currentItemIndex;
    PlayerState player;
    int currentWeight;

    public Inventory(PlayerState currentPlayer){
        list = new ArrayList<InventoryItem>();
        player = currentPlayer;
        currentItemIndex = 0;
        addItem(new InventoryItem("Backpack", 1, 500,"Items/Backpack.png"));
        addItem(new InventoryItem("Backpack1", 2, 500,"Items/Backpack.png"));
        addItem(new InventoryItem("Backpack2", 3, 500,"Items/Backpack.png"));
        addItem(new InventoryItem("Backpack3", 4, 500,"Items/Backpack.png"));
        addItem(new InventoryItem("Backpack4", 5, 500,"Items/Backpack.png"));
        addItem(new InventoryItem("Backpack5", 6, 500,"Items/Backpack.png"));
        addItem(new InventoryItem("Backpack6", 7, 500,"Items/Backpack.png"));
        addItem(new InventoryItem("Backpack7", 8, 500,"Items/Backpack.png"));
        addItem(new InventoryItem("Backpack8", 9, 500,"Items/Backpack.png"));
        addItem(new InventoryItem("Backpack9", 10, 500,"Items/Backpack.png"));



    }

    public boolean addItem(InventoryItem item){
        /*if((item.getWeight() + currentWeight) > player){
            return false;
        }*/
        list.add(item);
        return true;
    }

    public ArrayList<InventoryItem> getList(){
        return list;
    }

    public void indexJumpUp(){
        if(currentItemIndex - HUDVars.INVENTORY_ROW_SIZE < 0){
            int numCompletedRows = list.size()/HUDVars.INVENTORY_ROW_SIZE - 1;
            currentItemIndex = currentItemIndex + (numCompletedRows * HUDVars.INVENTORY_ROW_SIZE);
            if(currentItemIndex + HUDVars.INVENTORY_ROW_SIZE < list.size()){
                currentItemIndex = currentItemIndex + HUDVars.INVENTORY_ROW_SIZE;
            }
        }else{
            currentItemIndex = currentItemIndex- HUDVars.INVENTORY_ROW_SIZE;
        }

    }

    public void indexJumpDown() {
        if ((currentItemIndex + HUDVars.INVENTORY_ROW_SIZE) > list.size()-1) {
            currentItemIndex = (currentItemIndex % HUDVars.INVENTORY_ROW_SIZE);
        } else {
            currentItemIndex = currentItemIndex + HUDVars.INVENTORY_ROW_SIZE;
        }
    }

    public void indexNext(){
        if(++ currentItemIndex  > list.size()-1){
            currentItemIndex = 0;
        }
    }

    public void indexPrevious(){
        if(-- currentItemIndex  < 0){
            currentItemIndex = list.size()-1;
        }
    }

    public int getItemIndex(){
        return currentItemIndex;
    }


}
