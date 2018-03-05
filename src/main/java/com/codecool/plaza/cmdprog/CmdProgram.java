package com.codecool.plaza.cmdprog;

import com.codecool.plaza.api.*;
import com.codecool.plaza.api.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CmdProgram {

    private List<Product> cart = new ArrayList<>();
    private List<Float> prices = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private PlazaImpl myPlaza;

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
                        if (!(myPlaza.getShops().isEmpty())) {
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
                        System.out.println("Your shop has been successfully created.");
                    } catch (ShopAlreadyExistsException | PlazaIsClosedException e) {
                        System.out.println(e.getMessage());
                    }
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
                    try {
                        if (myPlaza.getShops().size() == 0) {
                            System.out.println("There are no shops in this plaza, you have to create one first!");
                            break;
                        }
                    } catch (PlazaIsClosedException e) {
                        System.out.println(e.getMessage());
                    }
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
        while (true) {
            try {
                System.out.println("You are at the shop " + myShop.getName() + "! Type\n\n" +
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
                        isShopEmpty(myShop);
                        System.out.println("Available products in " + myShop.getName() + ": \n");
                        for (Product p : myShop.getProducts()) {
                            System.out.println(myShop.toString());
                            System.out.println();
                        }
                        break;
                    case 2:
                        isShopEmpty(myShop);
                        try {
                            System.out.println("Which product would you like to find? Please enter it's name!\n");
                            String name = sc.nextLine();
                            System.out.printf("Your product is: %s%n", myShop.findByName(name));
                        } catch (NoSuchProductException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.printf("The owner of the shop is %s\n%n", myShop.getOwner());
                        break;
                    case 4:
                        myShop.open();
                        System.out.printf("You have opened %s successfully.\n%n", myShop.getName());
                        break;
                    case 5:
                        myShop.close();
                        System.out.printf("You have closed %s successfully.\n%n", myShop.getName());
                        break;
                    case 6:
                        long barcode = getBarcodeFromUser();
                        if (myShop.hasProduct(barcode)) {
                            System.out.println("This shop has a product with this barcode!\nIf you are trying to add an existing product please choose 7)!");
                            break;
                        }
                        String name = getStringFromUser("name");
                        String manufacturer = getStringFromUser("manufacturer");
                        System.out.println("What is the quantity of the product?\n");
                        int quantity = getNumberFromUser();
                        System.out.println("What is the price of the product?\n");
                        float price = Float.parseFloat(sc.nextLine());
                        if (foodOrClothing()) {
                            //foodproduct
                            int calories = getCaloriesFromUser();
                            Product product = new FoodProduct(barcode, manufacturer, name, calories);
                            productAdded(myShop, quantity, price, product);
                        } else {
                            // clothingproduct
                            String material = getStringFromUser("material");
                            String type = getStringFromUser("type");
                            Product product = new ClothingProduct(barcode, manufacturer, name, material, type);
                            productAdded(myShop, quantity, price, product);
                        }
                        break;
                    case 7:
                        barcode = getBarcodeFromUser();
                        quantity = getNumberFromUser();
                        myShop.addProduct(barcode, quantity);
                        break;
                    case 8:
                        isShopEmpty(myShop);
                        //myShop.buyProduct(long barcode);
                        break;
                    case 9:
                        isShopEmpty(myShop);
                        System.out.println("Which product's price would you like to know?}\nPlease type it's barcode!\n");
                        long barcodeToPrice = Long.parseLong(sc.nextLine());
                        try {
                            System.out.printf("The price what you would like to know is: %s HUF.%n\n", myShop.getPrice(barcodeToPrice));
                        } catch (NoSuchProductException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 10:
                        plazaMenu();
                        break;
                    default:
                        System.out.println("Please type a number from menu!\n");
                        break;
                }
            } catch (ShopIsClosedException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private void productAdded(Shop myShop, int quantity, float price, Product product) {
        try {
            myShop.addNewProduct(product, quantity, price);
            System.out.println("Your product was added successfully.\n");
        } catch (ProductAlreadyExistsException | ShopIsClosedException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNumberFromUser() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Wrong input! Please enter a number!\n");
            }
        }
    }

    public void exitPlaza() {
        System.out.println("Exiting now... Bye!\n");
        System.exit(0);
    }

    public boolean foodOrClothing() {
        String kind = "";
        while (true) {
            try {
                System.out.println("Which kind of product would you like to add:\ntype a letter f if you add food, or a letter c if you add clothing!\n");
                kind = sc.nextLine();
                if (kind.equals("f")) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                if (!(kind.equals("f") || kind.equals("c"))) {
                    System.out.println("Wrong input! Please type a letter c or a letter f!\n");
                }
            }
        }
    }

    public long getBarcodeFromUser() {
        while (true) {
            try {
                System.out.println("What is the barcode of the product?\n");
                return Long.parseLong(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Wrong input!\n");
            }
        }
    }

    public String getStringFromUser(String string) {
        System.out.println("Enter the " + string + " of the product!\n");
        string = sc.nextLine();
        return string;
    }

    public int getCaloriesFromUser() {
        System.out.println("Please enter the calorie content of the product!\n");
        return getNumberFromUser();
    }

    public void isShopEmpty(Shop myShop) {
        if (myShop.getProducts().isEmpty()) {
            System.out.println("You have no products in this shop.\n");
            shopMenu(myShop);
        }
    }
}

