/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Order {
    private ArrayList<Fruit> listItems;

    public Order() {
        listItems = new ArrayList<>();
    }

    public Order(ArrayList<Fruit> listItems) {
        this.listItems = listItems;
    }

    public ArrayList<Fruit> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<Fruit> listItems) {
        this.listItems = listItems;
    }
    
    
    
}
