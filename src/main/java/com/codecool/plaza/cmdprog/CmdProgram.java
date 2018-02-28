package com.codecool.plaza.cmdprog;

import com.codecool.plaza.api.PlazaImpl;
import com.codecool.plaza.api.Product;
import com.codecool.plaza.api.Shop;
import com.codecool.plaza.api.ShopImpl;
import com.codecool.plaza.api.exceptions.*;

import java.util.List;
import java.util.Scanner;

public class CmdProgram {

    private List<Product> cart;
    private List<Float> prices;
    private Scanner sc = new Scanner(System.in);
    private PlazaImpl myPlaza;
    //Map<Long, ShopImpl.ShopEntryImpl> products;

    public CmdProgram(String[] args) {
    }

    public void run() {
        while (true) {
            menu();
        }
    }

    public void menu() {
        System.out.println("\nThere are no plaza created yet! Press\n" +
                "1) to create a new plaza, or\n" +
                "2) to exit\n");

        switch (getNumberFromUser()) {
            case 1:
                System.out.println("Please enter the name of the plaza!");
                String name = sc.nextLine();
                System.out.println("Please enter the owner's name of the plaza!");
                String owner = sc.nextLine();
                boolean open = false;
                myPlaza = new PlazaImpl(name, owner, open);
                plazaMenu();
                break;
            case 2:
                exitPlaza();
            default:
                break;
        }
    }

    public void plazaMenu() {

            System.out.println("\nWelcome here!");
        while (true) {
            System.out.println("\nYou are at Plaza " + myPlaza.getName() + "! Press\n" +
                    "\n1) to list all shops.\n" +
                    "2) to add a new shop.\n" +
                    "3) to remove an existing shop.\n" +
                    "4) enter a shop by name.\n" +
                    "5) to open the plaza.\n" +
                    "6) to close the plaza.\n" +
                    "7) to check if the plaza is open or not.\n" +
                    "8) leave plaza.\n");

            switch (getNumberFromUser()) {
                case 1:
                    try {
                        if (!(myPlaza.getShops().size() == 0)) {
                            System.out.println("Your shops in " + myPlaza.getName() + ": \n");
                            for (Shop s : myPlaza.getShops()) {
                                System.out.println(s.getName());
                            }
                        } else {
                            System.out.println("You have no shops in this plaza. You can create a shop pressing 2).");
                        }
                    } catch (PlazaIsClosedException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("You can create a new shop. Please enter the name of the shop!");
                    String name = sc.nextLine();
                    System.out.println("Please enter the owner's name of the shop!");
                    String owner = sc.nextLine();
                    ShopImpl myShop = new ShopImpl(name, owner);
                    try {
                        myPlaza.addShop(myShop);
                    } catch (ShopAlreadyExistsException | PlazaIsClosedException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Your shop has been successfully created.");
                    break;
                case 3:
                    try {
                        System.out.println("Which shop would you like to remove? Please enter it's name!");
                        String shopToRemove = sc.nextLine();
                        Shop shop = myPlaza.findShopByName(shopToRemove);
                        myPlaza.removeShop(shop);
                        System.out.println(shopToRemove + " has removed from " + myPlaza.getName());
                    } catch (NoSuchShopException | PlazaIsClosedException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Which shop do you want to enter?");
                    String shop = sc.nextLine();
                    try {
                        shopMenu(myPlaza.findShopByName(shop));
                    } catch (NoSuchShopException | PlazaIsClosedException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    myPlaza.open();
                    System.out.println("You have opened " + myPlaza.getName() + " successfully.");
                    break;
                case 6:
                    myPlaza.close();
                    System.out.println("You have closed " + myPlaza.getName() + " successfully.");
                    break;
                case 7:
                    if (myPlaza.isOpen()) {
                        System.out.println(myPlaza.getName() + " is open.");
                    } else {
                        System.out.println(myPlaza.getName() + " is closed. You can open it by pressing 5)!");
                    }
                    break;
                case 8:
                    exitPlaza();
                default:
                    System.out.println("Please type a number from menu!");
                    break;
            }
        }
    }

    public void shopMenu(Shop myShop) {
        System.out.println("\nWelcome here! ");
        while (true) {
            System.out.println("This is the " + myShop.getName() + "! Type\n" +
                    "1) to list available products.\n" +
                    "2) to find products by name.\n" +
                    "3) to display the shop's owner.\n" +
                    "4) to open the shop.\n" +
                    "5) to close the shop.\n" +
                    "6) to add new product to the shop.\n" +
                    "7) to add existing products to the shop.\n" +
                    "8) to buy a product by barcode.\n" +
                    "9) check price by barcode.\n" +
                    "10) go back to plaza.\n");

            switch (getNumberFromUser()) {
                case 1:
                    myShop.getProducts();
                    break;
                case 2:
                    String name = "";
                    try {
                        myShop.findByName(name);
                    } catch (NoSuchProductException | ShopIsClosedException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    myShop.getOwner();
                    break;
                case 4:
                    myShop.open();
                    break;
                case 5:
                    myShop.close();
                    break;
                case 6:
                    //Product product = Product.valueOf(sc.nextLine());
                    int quantity = Integer.parseInt(sc.nextLine());
                    float price = Float.parseFloat(sc.nextLine());
                    //myShop.addNewProduct(product, quantity, price);
                    break;
                case 7:
                    //myShop.addProduct(long barcode, int quantity);
                    break;
                case 8:
                    //myShop.buyProduct(long barcode);
                    break;
                case 9:
                    //myShop.getPrice(long barcode);
                    break;
                case 10:
                    plazaMenu();
                    break;
                default:
                    System.out.println("Please type a number from menu!");
                    break;
            }
        }
    }

    public int getNumberFromUser() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Wrong input! Please enter a number!");
            }
        }
    }

    public void exitPlaza() {
        System.out.println("Exiting now... Bye!");
        System.exit(0);
    }

}
