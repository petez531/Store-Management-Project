

public class Item{
    private String name;
    private double price;
    private int amount;
    private double discount;

    public Item() {
        this("Default Item", 0, 0, 0);
    }

    public Item(String name, double price, int amount) {
        this(name, price, amount, 0);
    }

    public Item(String name, double price, int amount, double discount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.discount = discount;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getAmount() {
        return this.amount;
    }

    public double getDiscount() {
        return this.discount;
    }

    public double getTruePrice() {
        return this.price - (this.price * this.discount);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < 0) {
            System.out.println("Price is unable to be negative");
            return;
        }
        this.price = price;
        
    }

    public void setAmount(int amount) {
        if (amount < 0) {
            System.out.println("Amount is unable to be negative");
            return;
        }
        this.amount = amount;
    }

    public void setDiscount(double discount) {
        if (discount < 0) {
            System.out.println("Discount is unable to be negative");
            return;
        }
        this.discount = discount;
    }
  
    @Override
    public String toString() {
        return String.format("Item Name: %s\nItem Price: $%.2f\nItem Amount: %,d\nItem Discount: %.2f%%\nItem True Price: $%.2f",
                             this.name, this.price, this.amount, (this.discount * 100), this.getTruePrice());
    }
}
