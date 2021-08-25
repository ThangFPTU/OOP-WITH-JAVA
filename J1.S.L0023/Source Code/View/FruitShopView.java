/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.FruitManager;
import Controller.Validate;
import Model.Fruit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

/**
 *
 * @author hoang
 */
public class FruitShopView {

    private FruitManager control = new FruitManager();

    public void MenuView() {
        Validate validate = new Validate();
        while (true) {
            System.out.println("======== FRUIT SHOP SYSTEM ========\n"
                    + "1. Create Fruit\n"
                    + "2. View orders\n"
                    + "3. Shopping (for buyer)\n"
                    + "4. Exit");
            int choice = validate.inputIntLimit("Enter your choice: ", "Please enter number in range [1,4]!", 1, 4);

            switch (choice) {
                case 1:
                    createFruitView();
                    break;
                case 2:
                    ordersView();
                    break;
                case 3:
                    shoppingView();
                    break;
                case 4:
                    return;
            }
        }
    }

    public void createFruitView() {
        System.out.println("\n-------- Create Fruit -------- ");
        Validate validate = new Validate();
        int count = 0;
        while (true) {
            if (count != 0) {
                String choice = validate.inputString("Do you want to continue (Y/N)? ",
                        "Please input Y/N!", "[yYnN]");
                if (!validate.checkYesNo(choice)) {
                    Collections.sort(control.getListFruits());
                    System.out.printf("%-5s%-15s%-15s%-10s%-10s\n", "ID",
                            "Fruit Name", "Origin", "Price", "Quantity");
                    for (Fruit fruit : control.getListFruits()) {
                        int quantity = fruit.getQuantity();
                        //check shop have item or not 
                        if (quantity != 0) {
                            System.out.printf("%-5d%-15s%-15s%-10s%-10d\n", fruit.getId(),
                                    fruit.getName(), fruit.getOrigin(), fruit.getPrice() + "$", fruit.getQuantity());
                        }
                    }
                    System.out.println("");
                    return;
                } else {
                    System.out.println("");
                }
            }
            int id = validate.inputIntLimit("Enter ID: ", "Id must be a number and greater than 0!",
                    0, Integer.MAX_VALUE);
            //Check if ID exist then only enter Quantity

            String name = "", origin = "";
            float price = 0;
            int amount = 0;

            //Check exist id in list fruit created
            if (validate.isExistedFruitId(control.getListFruits(), id)) {
                System.out.println("Id has been exist!");
                count++;
                continue;
            }
            name = validate.inputString("Enter name: ", "Invalid Name", "[a-zA-z]+");

            //Check exist fruit in list fruit created
            if (validate.isExistedFruitName(control.getListFruits(), name)) {
                System.out.println("Fruit " + name + " has been created!");
                count++;
                continue;
            }
            origin = validate.inputString("Enter origin: ", "Invalid origin", "[a-zA-z ]+");
            price = validate.inputFloatLimit("Enter price: ", "Price must be digit!", 1, Float.MAX_VALUE);
            amount = validate.inputIntLimit("Enter amount:", "Amount must be digit and greater than 0!", 0, Integer.MAX_VALUE);
            control.addFruit(id, name, amount, price, origin);
            System.out.println("Add succesfull!");
            count++;
        }

    }

    public void ordersView() {
        System.out.println("-------- List Order --------\n");
        //Check empty
        if (control.getListOrder().isEmpty()) {
            System.out.println("No information!\n");
            return;
        }
        for (String name : control.getListOrder().keySet()) {
            System.out.println("Customer: " + name);
            ArrayList<Fruit> orderList = control.getListOrder().get(name).getListItems();
            displayListOrder(orderList);
            System.out.println("");
        }
        System.out.println("");
    }

    public void shoppingView() {
        Validate validate = new Validate();
        control.setListItems(new ArrayList<>());

        // check empty list fruit created
        if (control.getListFruits().isEmpty()) {
            System.err.println("No information!\n");
            return;
        }
        System.out.println("\n-------- Shopping --------");

        //Loop while user choose no
        while (true) {
            System.out.printf("|%-10s|%-18s|%-14s|%-13s|\n", " ++ Item ++ ",
                    " ++ Fruit name ++ ", " ++ Origin ++ ", " ++ Price ++");
            int item = 0;
            for (Fruit fruit : control.getListFruits()) {
                item++;
                int quantity = fruit.getQuantity();
                //check shop have item or not 
                if (quantity != 0) {
                    System.out.printf("       %-10d %-19s%-15s%-5s\n", item,
                            fruit.getName(), fruit.getOrigin(), fruit.getPrice() + "$");
                }
            }
            int selectedIndex = validate.inputIntLimit("Choose the number you want: ",
                    "Please select [1," + item + "]!", 1, item);
            Fruit selectedFruit = control.getListFruits().get(selectedIndex - 1);
            System.out.println("You selected: " + selectedFruit.getName());
            int quantity = validate.inputIntLimit("Enter quantity: ", "Quantity is digit and less than quantity available!",
                    1, selectedFruit.getQuantity());
            control.addItem(new Fruit(selectedFruit.getId(), selectedFruit.getName(),
                    quantity, selectedFruit.getPrice(), selectedFruit.getOrigin()));
            System.out.println("Add successful!");
            String choice = validate.inputString("Do you want to order now (Y/N)? ",
                    "Please choose Y/N!", "[yYnN]");

            //Check yes no input
            if (!validate.checkYesNo(choice)) {
                break;
            }
        }

        displayListOrder(control.getListItems());
        String name = validate.inputString("Enter name: ", "Invalid name!", "[a-zA-Z ]+");
        control.addOrder(name);
        System.err.println("Order successfull!");
        System.out.println("");
    }

    public void displayListOrder(ArrayList<Fruit> orderList) {
        int total = 0;
        int number = 1;
        System.out.printf("%-12s|%-10s|%-7s|%-7s\n", "Product", "Quantity", "Price", "Amount");

        for (Fruit Fruit : orderList) {
            float price = Fruit.getPrice();
            int quantity = Fruit.getQuantity();
            System.out.printf("%d.%-12s%-10s%-8s%-8s\n", number, Fruit.getName(),
                    Fruit.getQuantity(), Fruit.getPrice() + "$",
                    price * quantity + "$");
            total += price * quantity;
            number++;
        }
        System.out.println("Total: " + total + "$");
    }

    public static void main(String[] args) {
        FruitShopView view = new FruitShopView();
        view.MenuView();
    }

}
