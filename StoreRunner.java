import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;


public class StoreRunner {
    Store store;
    Scanner input;

    public static void main(String[] args) {

        StoreRunner runner = new StoreRunner();
        runner.run();
    }

    public void run() {
        input = new Scanner(System.in);
        store = new Store();

        while (true) {
            System.out.println();
            System.out.print("Action (help): ");
            String userInput = input.nextLine().toLowerCase();

            switch (userInput) {
                case "help" -> System.out.println("""
                                       Commands:
                                       inventory\t\t-> prints current inventory
                                       create item\t\t-> adds an item to the inventory
                                       create expirable item\t-> adds an expirable item to the inventory
                                       get item info\t\t-> gets an items information
                                       change item info\t-> changes an items information
                                       remove item\t\t-> removes an item from the inventory
                                       exit\t\t\t-> quits the program""");
                case "inventory" -> System.out.println(store.toString());
                case "create item" -> createItem();
                case "create expirable item" -> createExpirableItem();
                case "get item info" -> getItemInfo();
                case "change item info" -> changeItemInfo();
                case "remove item" -> removeItem();
                case "exit" -> return;
                default -> System.out.println("Enter a valid command");
            }
        }

        input.close();
    }

    public void removeItem() {
        if (this.store.isEmpty()) {
            System.out.println("There are no items to remove.");
            return;
        }


        String item;

        while (true) {
            System.out.print("Item name: ");
            item = input.nextLine().toLowerCase();
            if (item.equals("exit")) {
                return;
            }
            else if (store.hasItem(item)) {
                break;
            }
            System.out.println("This item is not in the inventory");
        }

        store.removeItem(item);
    }

    public void changeItemInfo() {
        String item, variable, value;
        String[] validVariables = {"name", "price", "amount", "discount"};

        while (true) {
            System.out.print("Item name: ");
            item = input.nextLine().toLowerCase();
            if (item.equals("exit")) {
                return;
            }
            else if (store.hasItem(item)) {
                break;
            }
            System.out.println("This item is not in the inventory");
        }

        while (true) {
            System.out.print("Item variable (help): ");
            variable = input.nextLine().toLowerCase();
            if (variable.equals("exit") {
                return;
            }
            else if (variable.equals("help")) {
                System.out.println("Variables: name, price, amount, discount, expiration date");
            }
            else if (Arrays.asList(validVariables).contains(variable)) {
                break;
            }
            else if (store.isItemExpirable(item) && variable.equals("expiration date")) {
                break;
            }
        }

        while (true) {
            System.out.print(variable + " value: ");
            value = input.nextLine().toLowerCase();
            switch (variable) {
                case "exit" -> {return;}
                case "name" -> {
                    if (!value.equals("")) {
                        store.changeItemInfo(item, variable, value);
                        return;
                    }
                    System.out.println("Name must have a length greater than 0");
                }
                case "price" -> {
                    try {
                        double valueCasted = Double.parseDouble(value);
                        if (valueCasted >= 0) {
                            store.changeItemInfo(item, variable, value);
                            return;
                        }
                        System.out.println("Price must be above 0");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("price is of invalid format.");
                    }
                }
                case "amount" -> {
                    try {
                        int valueCasted = Integer.parseInt(value);
                        if (valueCasted >= 0) {
                            store.changeItemInfo(item, variable, value);
                            return;
                        }
                        System.out.println("Amount must be above 0");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("amount is of invalid format.");
                    }
                }
                case "discount" -> {
                    try {
                        double valueCasted = Double.parseDouble(value);
                        if (valueCasted >= 0) {
                            store.changeItemInfo(item, variable, value);
                            return;
                        }
                        System.out.println("Discount must be above 0");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("discount is of invalid format.");
                    }
                }
                case "expiration date" -> {
                    try {
                        LocalDate.parse(value);
                        store.changeItemInfo(item, variable, value);
                        return;
                    } catch (DateTimeException ex) {
                        System.out.println("Unable to parse expiration date. Expiration date format is invalid. It must be in the form yyyy-mm-dd");
                    }
                }
              }
        }
    }

    public void getItemInfo() {
        String item;

        while (true) {
            System.out.print("Item name: ");
            item = input.nextLine();
            if (item.equals("exit")) {
                return;
            }
            else if (store.hasItem(item)) {
                break;
            }
            System.out.println("This item is not in the inventory");
        }
        System.out.println(this.store.getItemString(item));
    }

    public void createItem() {
        String name;
        double price, discount;
        int amount;

        while (true) {
            System.out.print("Name of the item: ");
            name = input.nextLine();
            if (name.equals("exit") {
                return;
            }
            if (name.equals("")) {
                System.out.println("Name must have a length greater than 0");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Price of the item: ");
            String priceString = input.nextLine();
            if (priceString.equals("exit") {
                return;
            }
            try {
                price = Double.parseDouble(priceString);
            }
            catch (IllegalArgumentException ex) {
                System.out.println("price is of invalid format.");
                continue;
            }
            if (price >= 0) {
                break;
            }
            System.out.println("Price must be above 0");
        }

        while (true) {
            System.out.print("Item amount: ");
            String amountString = input.nextLine();
            if (amountString.equals("exit") {
                return;
            }
            try {
                amount = Integer.parseInt(amountString);
            }
            catch (IllegalArgumentException ex) {
                System.out.println("amount is of invalid format.");
                continue;
            }
            if (amount >= 0) {
                break;
            }
            System.out.println("Amount must be above 0");
        }

        while (true) {
            System.out.print("Item discount: ");
            String discountString = input.nextLine();
            if (discountString.equals("exit") {
                return;
            }
            try {
                discount = Double.parseDouble(discountString);
            }
            catch (IllegalArgumentException ex) {
                System.out.println("discount is of invalid format.");
                continue;
            }
            if (discount >= 0) {
                break;
            }
            System.out.println("Discount must be above 0");
        }

        Item item = new Item(name, price, amount, discount);
        store.addItem(item);
    }

    public Item createExpirableItem() {
        String name;
        double price, discount;
        int amount;
        LocalDate expirationDate;

        while (true) {
            System.out.print("Name of the item: ");
            name = input.nextLine();
            if (name.equals("exit") {
                return;
            }
            else if (name.equals("")) {
                System.out.println("Name must have a length greater than 0");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Price of the item: ");
            String priceString = input.nextLine();
            if (priceString.equals("exit") {
                return;
            }
            try {
                price = Double.parseDouble(priceString);
            }
            catch (IllegalArgumentException ex) {
                System.out.println("price is of invalid format.");
                continue;
            }
            if (price >= 0) {
                break;
            }
            System.out.println("Price must be above 0");
        }

        while (true) {
            System.out.print("Item amount: ");
            String amountString = input.nextLine();
            if (amountString.equals("exit") {
                return;
            }
            try {
                amount = Integer.parseInt(amountString);
            }
            catch (IllegalArgumentException ex) {
                System.out.println("amount is of invalid format.");
                continue;
            }
            if (amount >= 0) {
                break;
            }
            System.out.println("Amount must be above 0");
        }

        while (true) {
            System.out.print("Item discount: ");
            String discountString = input.nextLine();
            if (discountString.equals("exit") {
                return;
            }
            try {
                discount = Double.parseDouble(discountString);
            }
            catch (IllegalArgumentException ex) {
                System.out.println("discount is of invalid format.");
                continue;
            }
            if (discount >= 0) {
                break;
            }
            System.out.println("Discount must be above 0");
        }

        while (true) {
            System.out.print("Item expiration date: ");
            String expirationDateString = input.nextLine();
            if (expirationDateString.equals("exit") {
                return;
            }
            try {
                expirationDate = LocalDate.parse(expirationDateString);
            }
            catch (DateTimeException ex) {
                System.out.println("Unable to parse expiration date. Expiration date format is invalid. It must be in the form yyyy-mm-dd");
                continue;
            }
            break;
        }

        ExpirableItem expirableItem = new ExpirableItem(name, price, amount, discount, expirationDate);
        store.addItem(expirableItem);
    }
}
