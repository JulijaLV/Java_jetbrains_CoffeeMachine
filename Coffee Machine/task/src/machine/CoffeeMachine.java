package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public enum States {
        ACTION, COFFEE_SELECTION, INIT, SUPPLY_SELECTION
    }
    private enum Operations {
        BUY, TAKE, REMAINING, FILL, ESPRESSO, LATTE, CAPPUCCINO, WATER_SET, MILK_SET, BEANS_SET, CUPS_SET
    }

    int milkQty;
    int beansQty;
    int waterQty;
    int moneyQty;
    int cupsQty;
    public States state;
    Operations operation;

    public CoffeeMachine() {
        initMachine();
        this.state = States.INIT;
    }

    public void initMachine() {
        this.milkQty = 540;
        this.beansQty = 120;
        this.waterQty = 400;
        this.moneyQty = 550;
        this.cupsQty = 9;
    }

    public void updateSupplies(int water, int milk, int beans, int money, int cups) {
        this.waterQty += water;
        this.milkQty  += milk;
        this.beansQty += beans;
        this.moneyQty += money;
        this.cupsQty  += cups;
    }

    private String checkSupplies( int water, int milk, int beans, int cups) {
        String supply = "";
        String result = "";
        if (this.waterQty < water) {
             supply = "water";
         }
        if (this.milkQty < milk) {
            supply = "milk";
        }
        if (this.beansQty < beans) {
            supply = "beans";
        }
        if (this.cupsQty < cups) {
            supply = "cups";
        }
        if (!supply.isEmpty()) {
            result = "Sorry, not enough " + supply + "!";
        }
        return result;
    }

    public void brewCoffee(Operations type) {
        String check;
        int water;
        int milk;
        int beans;
        int money;
        switch (type) {
            case ESPRESSO:
                water = 250; milk = 0; beans = 16; money = 4;
                break;
            case LATTE:
                water = 350; milk = 75; beans = 20; money = 7;
                break;
            case CAPPUCCINO:
                water = 200; milk = 100; beans = 12; money = 6;
                break;
            default:
                this.state = States.INIT;
                return;
        }

        check = checkSupplies(water, milk,beans, 1);
        if (check.isEmpty()) {
            updateSupplies(-(water), -(milk), -(beans), money, -1);
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            System.out.println(check);
        }

    }

    private void takeMoney(){
        System.out.println("I gave you $" + this.moneyQty);
        this.moneyQty = 0;
    }

    private void printStatus() {
        System.out.println( "The coffee machine has:" );
        System.out.println( this.waterQty + " of water");
        System.out.println( this.milkQty  + " of milk");
        System.out.println( this.beansQty + " of coffee beans");
        System.out.println( this.cupsQty  + " of disposable cups");
        System.out.println( this.moneyQty + " of money");
    }
    private void processOperation(States state_, Operations operation_, int value_) {
        if (state_.equals(States.ACTION)) {
            switch (operation_) {
                case TAKE:
                    takeMoney();
                    this.state = States.INIT;
                    break;
                case REMAINING:
                    printStatus();
                    this.state = States.INIT;
                    break;
                case ESPRESSO:
                case LATTE:
                case CAPPUCCINO:
                    brewCoffee(operation_);
                    this.state = States.INIT;
                    break;
                case WATER_SET:
                    this.updateSupplies(value_,0,0,0,0);
                    this.operation = Operations.MILK_SET;
                    System.out.println("Write how many ml of milk do you want to add:");
                    break;
                case MILK_SET:
                    this.updateSupplies(0,value_,0,0,0);
                    this.operation = Operations.BEANS_SET;
                    System.out.println("Write how many grams of coffee beans do you want to add:");
                    break;
                case BEANS_SET:
                    this.updateSupplies(0,0, value_,0,0);
                    this.operation = Operations.CUPS_SET;
                    System.out.println("Write how many disposable cups of coffee do you want to add:");
                    break;
                case CUPS_SET:
                    this.updateSupplies(0,0, 0,0,value_);
                    this.state = States.INIT;
                    break;
                default:
                    break;
            }
            } else if(state_.equals(States.COFFEE_SELECTION)) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        }
        else if(state_.equals(States.SUPPLY_SELECTION)) {
            if (operation_.equals(Operations.FILL)) {
                System.out.println("Write how many ml of water do you want to add:");
                this.state = States.ACTION;
                this.operation = Operations.WATER_SET;
            }
        }

    }
    public void handleOperation(String operation_) {
        int i = 0;
        switch (operation_) {
            case "BUY":
                this.state = States.COFFEE_SELECTION;
                this.operation = Operations.BUY;
                break;
            case "TAKE":
                this.state = States.ACTION;
                this.operation = Operations.TAKE;
                break;
            case "REMAINING":
                this.state = States.ACTION;
                this.operation = Operations.REMAINING;
                break;
            case "1":
                this.state = States.ACTION;
                this.operation = Operations.ESPRESSO;
                break;
            case "2":
                this.state = States.ACTION;
                this.operation = Operations.LATTE;
                break;
            case "3":
                this.state = States.ACTION;
                this.operation = Operations.CAPPUCCINO;
                break;
            case "FILL":
                this.operation = Operations.FILL;
                this.state = States.SUPPLY_SELECTION;
                break;
            case "BACK":
                this.state = States.INIT;
                return;
            default:
             switch (this.operation) {
                 case WATER_SET:
                 case MILK_SET:
                 case BEANS_SET:
                 case CUPS_SET:
                     i = Integer.parseInt(operation_);
                    break;
                 default:
                     break;
             }
        }

        processOperation(this.state, this.operation, i);
    }
}

 class Main {
    static Scanner scan;
    public static void main(String[] args) {
        CoffeeMachine cf = new CoffeeMachine();
        String operation;
        scan = new Scanner(System.in);

        while (true) {
            if (cf.state.equals(CoffeeMachine.States.INIT)) {
                System.out.println("Write action (buy, fill, take, remaining, exit):");
            }
            operation = scan.nextLine().toUpperCase();
            if (operation.equals("EXIT")) {
                break;
            } else {
                cf.handleOperation(operation);
            }
        }
    }

}


