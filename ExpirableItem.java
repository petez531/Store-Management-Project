import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ExpirableItem extends Item {
    private LocalDate expirationDate;

    public ExpirableItem() {
        this("Default Expirable Item", 0, 0, 0, LocalDate.now().toString());
    }

    public ExpirableItem(String name, double price, int amount, double discount, String expirationDate) {
        super(name, price, amount, discount);
        try {
            this.expirationDate = LocalDate.parse(expirationDate);
        }
        catch (DateTimeException ex) {
            System.out.print("Unable to parse expiration date. Expiration date format is invalid. It must be in the form yyyy-mm-dd");
            throw ex;
        } 
    }

    public ExpirableItem(String name, double price, int amount, double discount, LocalDate expirationDate) {
        super(name, price, amount, discount);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public long daysUntilExpiration() {
        if (this.isExpired()) {
            return 0;
        }
        return LocalDate.now().until(this.expirationDate, ChronoUnit.DAYS);
    }

    public boolean isExpired() {
        return this.expirationDate.isBefore(LocalDate.now());
    }

    public void setExpirationDate(String expirationDate) {
        try {
            this.expirationDate = LocalDate.parse(expirationDate);
        }
        catch (DateTimeException e) {
            System.out.println("Unable to parse expiration date. Expiration date format is invalid. It must be in the form yyyy-mm-dd");
        }
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return String.format("%s\nItem Expiration Date: %s\nIs Expired: %b%s",
                             super.toString(), this.expirationDate.format(DateTimeFormatter.ISO_DATE), this.isExpired(), (this.isExpired() ? "" : String.format("\nDays Until Expired: %,d", this.daysUntilExpiration())));
    }
}
