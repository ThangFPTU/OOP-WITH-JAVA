/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Fruit;
import Model.Order;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author hoang
 */
public class FruitManager {

    private ArrayList<Fruit> listFruits = new ArrayList<>();
    private ArrayList<Fruit> listItems;
    private Hashtable<String, Order> listOrder = new Hashtable<>();

    public FruitManager() {
        listFruits.add(new Fruit(1, "Apple", 20, 4, "Thailand"));
        listFruits.add(new Fruit(2, "Coconut", 20, 2, "Vietnam"));
        listFruits.add(new Fruit(3, "Orange", 20, 3, "US"));
        listFruits.add(new Fruit(4, "Grape", 20, 6, "France"));
    }

    public ArrayList<Fruit> getListFruits() {
        return listFruits;
    }

    public ArrayList<Fruit> getListItems() {
        return listItems;
    }

    public Hashtable<String, Order> getListOrder() {
        return listOrder;
    }

    public void setListItems(ArrayList<Fruit> listItems) {
        this.listItems = listItems;
    }

    public Fruit getExistedFruitByID(int Id) {
        for (Fruit fruit : listFruits) {
            if (fruit.getId() == Id) {
                return fruit;
            }
        }
        return null;
    }

    public void addFruit(int id, String name, int quantity, float price, String origin) {
        listFruits.add(new Fruit(id, name, quantity, price, origin));
    }

    public void addItem(Fruit fruit) {
        Validate validate = new Validate();
        //Check fruit exist in cart
        if (!validate.isExistedFruitId(listItems, fruit.getId())) {
            listItems.add(fruit);
        } else {
            for (Fruit item : listItems) {
                //Check id
                if (item.getId() == fruit.getId()) {
                    item.setQuantity(fruit.getQuantity()+item.getQuantity());
                    return;
                }
            }
        }
    }

    public void addOrder(String name) {
        Validate validate = new Validate();
        //Check exist fruit in cart
        if (listOrder.containsKey(name)) {
            for (Fruit shoppingItem : listItems) {
                //Check exist customer order
                if (validate.isExistedFruitId(listOrder.get(name).getListItems(), shoppingItem.getId())) {
                    for (Fruit orderItem : listOrder.get(name).getListItems()) {
                        //Check id
                        if (orderItem.getId() == shoppingItem.getId()) {
                            orderItem.setQuantity(shoppingItem.getQuantity());
                            break;
                        }
                    }
                } else {
                    listOrder.get(name).getListItems().add(shoppingItem);
                }
            }
        } else {
            listOrder.put(name, new Order(listItems));
        }
    }

}
