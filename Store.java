import java.util.HashMap;

public class Store {
    private HashMap<String, Item> inventory;
    
    public Store() {
        this.inventory = new HashMap<String, Item>();
    }

    public void addItem(Item item) {
        this.inventory.put(item.getName(), item);
    }

    public void removeItem(String item) {
        this.inventory.remove(item);
    }

    public String[] getInventory() {
        return this.inventory.keySet().toArray(new String[0]);
    }

    public boolean hasItem(String item) {
        return this.inventory.containsKey(item);
    }

    public String getItemString(String item) {
        return this.inventory.get(item).toString();
    }

    public void changeItemInfo(String item, String variable, String value) {
        if (!isItemExpirable(item) && variable.equals("expiration date")) {
            System.out.println(item + " does not have the variable 'expiration date'");
            return;
        }
      
        switch (variable) {
            case "name":
                this.inventory.get(item).setName(value);
                break;
            case "price":
                this.inventory.get(item).setPrice(Double.parseDouble(value));
                break;
            case "amount":
                this.inventory.get(item).setAmount(Integer.parseInt(value));
                break;
            case "discount":
                this.inventory.get(item).setDiscount(Double.parseDouble(value));
                break;
            case "expiration date":
                if (this.inventory.get(item) instanceof ExpirableItem) {
                    ExpirableItem expirableItem = (ExpirableItem) this.inventory.get(item);
                    expirableItem.setExpirationDate(value);
                }
                break;
        }
    }

    public boolean isItemExpirable(String item) {
        return (this.inventory.get(item).getClass().getSimpleName() == "ExpirableItem") ? true : false;
    }

    public String toString() {
        return String.format("Inventory: %s", String.join(", ", this.getInventory()));
    }
