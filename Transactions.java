import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transactions {


    Long id;
    double sum;
    LocalDateTime date = LocalDateTime.now();
    String notes;
    boolean isBank;
    Category category;




    public Transactions() {

    }

    public Transactions(Long id, double sum, LocalDateTime date, String notes, boolean isBank, Category category) {
        this.id = id;
        this.sum = sum;
        this.date = date;
        this.notes = notes;
        this.isBank = isBank;
        this.category = category;
    }

    public Transactions(Long id, double sum, String notes, boolean isBank, Category category) {
        this.id = id;
        this.sum = sum;
        this.notes = notes;
        this.isBank = isBank;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public LocalDateTime getDate() {
        return date;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isBank() {
        return isBank;
    }

    public void setBank(boolean bank) {
        isBank = bank;
    }

    public void printTransactions() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
                String dateString = ""+ date.format(formatter);
                String isBankString = (isBank) ? "Bank" : "Cash";
                String catToString = category.toString().toLowerCase();
                String categoryString = ("" + catToString.charAt(0)).toUpperCase() + catToString.substring(1);
                String isIncome = (sum < 0 ) ? "Expense" : "Income";

                System.out.println(getId() + ".\t\t" + isIncome + "\t\t" + sum + "\t\t" + dateString + "\t" + isBankString + "\t\t" + categoryString);
                if (notes != null) {
                    System.out.println("\t\t\t*** Notes:" + notes+"***");
                }
            System.out.println("\n");
        }

    }
