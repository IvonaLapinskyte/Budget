import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Budget {
    static Long id = 0L;
    static List<Transactions> transactionsList = new ArrayList<>();
    static List<Transactions> incomeList = new ArrayList<>();
    static List<Transactions> expensesList = new ArrayList<>();

    static File saveTransactions = new File ("transaction.txt");

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        startProgram();

    }
    public static Long generateId() {
        id++;
        return id;
    }
    public static void startProgram() {


        String option = "";

        System.out.println("\nWelcome to BUDGET program!\n");
        while(!option.equals("8")) {
            System.out.println("1. View all transactions");
            System.out.println("2. View list of income");
            System.out.println("3. View list of expenses");
            System.out.println("4. New transaction");
            System.out.println("5. Balance");
            System.out.println("6. Save transactions to file");
            System.out.println("7. Load transactions from file");
            System.out.println("8. Exit");

            option = sc.nextLine();


            switch (option) {
                case "1":
                    System.out.println("Test");
                    printTransactions(transactionsList);
                    break;
                case "2":
                    sortByIncome();
                    printTransactions(incomeList);
                    incomeList.clear();
                    expensesList.clear();
                    break;
                case "3":
                    sortByIncome();
                    printTransactions(expensesList);
                    incomeList.clear();
                    expensesList.clear();
                    break;
                case "4":
                    createTransaction();
                    break;
                case"5" :
                    balance();
                    break;
                case"6" :
                    saveToFile();
                    break;
                case"7" :
                    readFromFile();
                    break;
                default:
                    if(option.equals("8")){
                        break;
                    }else {
                        System.out.println("That is not an option!");
                    }
            }
        }
    }
    public static void printTransactions(List<Transactions> transactions) {
        System.out.println("\nList of transactions:\n");
        if(transactions.size() < 1) {
            System.out.println("There are no transactions.");
        }
        else {
            System.out.println("ID\t\t+/-\t\t\tSum\t\t\tDate\t\t\t\tType\t\tCategory");
            System.out.println("--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--");
            for(Transactions transaction : transactions) {
                transaction.printTransactions();
            }
        }
        System.out.println();
        System.out.println();
    }

    public static void sortByIncome() {
        for(int i = 0; i<transactionsList.size(); i++) {
            if(transactionsList.get(i).getSum() < 0) {
                expensesList.add(transactionsList.get(i));
            }
            else{
                incomeList.add(transactionsList.get(i));
            }
        }
    }
    public static void createTransaction(){
            System.out.println("\nCreating new transaction: \n");
            System.out.println("Enter amount:");
            Double sum = Double.parseDouble(sc.nextLine());
            System.out.println("Enter notes (enter to skip):");
            String notes = sc.nextLine();

            boolean payMethod = true;
            boolean isBank = true;
            while (payMethod){
                System.out.println("Choose payment method:");
                System.out.println("1. Bank");
                System.out.println("2. Cash");
                String option = sc.nextLine();
                if (option.equals("1")) {
                    isBank = true;
                    payMethod = false;
                } else if (option.equals("2")) {
                    isBank = false;
                    payMethod = false;
                }else{
                    System.out.println("Not an option!");
                }

        }
            System.out.println("Choose category:");
        Category category;
            if(sum < 0 ){
                System.out.println("1. Housing");
                System.out.println("2. Transportation");
                System.out.println("3. Food");
                System.out.println("4. Household");
                System.out.println("5. Clothing");
                System.out.println("6. Health");
                System.out.println("7. Insurance");
                System.out.println("8. Personal");
                System.out.println("9. Education");
                System.out.println("10. Savings");
                System.out.println("11. Gifts");
                System.out.println("12. Entertainment");
                System.out.println("13. Investing");
                System.out.println("14. Others");

                String optionCategory = sc.nextLine();
                switch (optionCategory){
                    case "1" : category = Category.HOUSING;break;
                    case "2" : category = Category.TRANSPORTATION;break;
                    case "3" : category = Category.FOOD;break;
                    case "4" : category = Category.HOUSEHOLD;break;
                    case "5" : category = Category.CLOTHING;break;
                    case "6" : category = Category.HEALTH;break;
                    case "7" : category = Category.INSURANCE;break;
                    case "8" : category = Category.PERSONAL;break;
                    case "9" : category = Category.EDUCATION;break;
                    case "10" : category = Category.SAVINGS;break;
                    case "11" : category = Category.GIFTS;break;
                    case "12" : category = Category.ENTERTAINMENT;break;
                    case "13" : category = Category.INVESTING;break;
                    case "14" : category = Category.OTHERS;break;
                    default:
                        category = Category.OTHERS;
                        System.out.println("That is not an option! Using category \"Others\"!");
                }
            }
            else{
                System.out.println("1. Housing");
                System.out.println("2. Insurance");
                System.out.println("3. Gifts");
                System.out.println("4. Salary");
                System.out.println("5. Investing");
                System.out.println("6. Selling");
                System.out.println("7. Others");

                String optionCategory = sc.nextLine();
                switch (optionCategory){
                    case "1" : category = Category.HOUSING;break;
                    case "2" : category = Category.INSURANCE;break;
                    case "3" : category = Category.GIFTS;break;
                    case "4" : category = Category.SALARY;break;
                    case "5" : category = Category.INVESTING;break;
                    case "6" : category = Category.SELLING;break;
                    case "7" : category = Category.OTHERS;break;
                    default: category = Category.OTHERS;
                    System.out.println("That is not an option! Using category \"Others\"!");
                }
            }
            Transactions tran1 = new Transactions(generateId(),sum,notes,isBank,category);
            transactionsList.add(tran1);
    }

    public static void balance() {
        double balance = 0d;

        for(int i = 0; i<transactionsList.size(); i++){
            balance += transactionsList.get(i).getSum();

        }
        System.out.println("Your balance is"+balance);
    }

    public static void saveToFile() {
        try{

            saveTransactions.createNewFile();
            FileWriter myWriter = new FileWriter("transaction.txt");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        for (int i=0; i<transactionsList.size(); i++){
            myWriter.write("::" +transactionsList.get(i).getSum());
            myWriter.write("::" + transactionsList.get(i).getDate().format(formatter));
            myWriter.write("::" + transactionsList.get(i).getNotes());
            myWriter.write("::" + transactionsList.get(i).isBank());
            myWriter.write("::" + transactionsList.get(i).getCategory() + "\n");
        }
            System.out.println("Successfully wrote to file!");
            myWriter.close();
        }catch (Exception e){
            System.out.println("An error occured!");

        }
    }
    public static void readFromFile(){
        try {
            Scanner reader = new Scanner(saveTransactions);
            reader.useDelimiter("::");
            String[] split;
            while (reader.hasNext()){
                split = reader.nextLine().split("::");

                System.out.println(split[1]);
                System.out.println(split[2]);
                System.out.println(split[3]);
                System.out.println(split[4]);
                System.out.println(split[5]);


                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                transactionsList.add(new Transactions(generateId(),Double.parseDouble(split[1]), LocalDateTime.parse(split[2],dateFormat),split[3],Boolean.valueOf(split[4]),Category.valueOf(split[5])));

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
