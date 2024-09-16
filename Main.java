import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

enum ProductType {
    SNACK, DRINK, FOOD;

    public String getDescription() {
        switch (this) {
            case SNACK:
                return "A small item of food.";
            case DRINK:
                return "A liquid for drinking.";
            case FOOD:
                return "A substance for eating.";
            default:
                return "Unknown type.";
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager("John");
        Cashier cashier = new Cashier("Alice");
        List<Customer> customers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Adding products
        manager.addProduct(new Product("Chips", 20.0, 50, ProductType.SNACK));
        manager.addProduct(new Product("Soda", 15.0, 30, ProductType.DRINK));
        manager.addProduct(new Product("Burger", 50.0, 20, ProductType.FOOD));

        // In the Main class
        List<Product> exampleProducts = new ArrayList<>();
        exampleProducts.add(new Product("Chips", 20.0, 1, ProductType.SNACK));
        exampleProducts.add(new Product("Soda", 15.0, 1, ProductType.DRINK));
        manager.addBill(new Bill(manager.getNextBillNumber(), exampleProducts, 35.0, new Date())); // Adds a bill with the current date

        
        boolean running = true;

        // Role selection
        while (running) {
            System.out.println("Select your role:");
            System.out.println("1. Customer");
            System.out.println("2. Cashier");
            System.out.println("3. Manager");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int roleChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (roleChoice) {
                case 1: // Customer
                    handleCustomerRole(customers, scanner);
                    break;
                case 2: // Cashier
                    handleCashierRole(cashier, manager, customers, scanner);
                    break;
                case 3: // Manager
                    handleManagerRole(manager, scanner);
                    break;
                case 4: // Exit
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Thank you for using the system!");
    }

    // Function to handle the Customer Role
    private static void handleCustomerRole(List<Customer> customers, Scanner scanner) {
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        Customer customer = customers.stream()
                .filter(c -> c.getName().equals(customerName))
                .findFirst()
                .orElseGet(() -> {
                    Customer newCustomer = new Customer(customerName, 100.0);
                    customers.add(newCustomer);
                    return newCustomer;
                });

        boolean customerRunning = true;
        while (customerRunning) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View Bill History");
            System.out.println("2. Add Money to Wallet");
            System.out.println("3. View Wallet Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int customerChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (customerChoice) {
                case 1:
                    System.out.print("Enter Bill Number to view: ");
                    int billNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    customer.viewBill(billNumber);
                    break;
                case 2:
                    System.out.print("Enter amount to add to wallet: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    customer.addMoney(amount);
                    System.out.println("Money added successfully.");
                    break;
                case 3:
                    System.out.print("Enter membership ID to view wallet balance: ");
                    String membershipId = scanner.nextLine();
                    customer.viewWallet(membershipId);
                    break;
                case 4:
                    customerRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Function to handle the Cashier Role
    private static void handleCashierRole(Cashier cashier, Manager manager, List<Customer> customers, Scanner scanner) {
        System.out.println("\n--- Cashier Menu ---");
        cashier.displayProducts(manager.getProducts());

        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        Customer customer = customers.stream()
                .filter(c -> c.getName().equals(customerName))
                .findFirst()
                .orElseGet(() -> {
                    Customer newCustomer = new Customer(customerName, 100.0);
                    customers.add(newCustomer);
                    return newCustomer;
                });

        boolean cashierRunning = true;
        while (cashierRunning) {
            System.out.println("\n1. Process Payment");
            System.out.println("2. Register Membership");
            System.out.println("3. Check Membership Status");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int cashierChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (cashierChoice) {
                case 1:
                    System.out.println("Enter Product IDs (comma separated): ");
                    String[] productIds = scanner.nextLine().split(",");
                    List<Product> products = new ArrayList<>();
                    for (String productId : productIds) {
                        int id = Integer.parseInt(productId.trim()) - 1;
                        if (id >= 0 && id < manager.getProducts().size()) {
                            products.add(manager.getProducts().get(id));
                        } else {
                            System.out.println("Invalid Product ID: " + (id + 1));
                        }
                    }

                    System.out.print("Pay with Wallet? (yes/no): ");
                    boolean payWithWallet = scanner.next().equalsIgnoreCase("yes");
                    scanner.nextLine(); // Consume newline

                    Bill bill = cashier.processPayment(customer, products, payWithWallet);
                    if (bill != null) {
                        // Receipt will be printed in `processPayment` method
                    }
                    break;
                case 2:
                    cashier.registerMembership(customer, scanner);
                    break;
                case 3:
                    cashier.checkMembershipStatus(customer, scanner);
                    break;
                case 4:
                    cashierRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Function to handle the Manager Role
    private static void handleManagerRole(Manager manager, Scanner scanner) {
        System.out.println("\n--- Manager Menu ---");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. View Income");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
        int managerChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (managerChoice) {
            case 1:
                System.out.print("Enter Product Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Product Price: ");
                double price = scanner.nextDouble();
                System.out.print("Enter Product Quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter Product Type (SNACK, DRINK, FOOD): ");
                String type = scanner.nextLine();
                ProductType productType = ProductType.valueOf(type.toUpperCase());
                manager.addProduct(new Product(name, price, quantity, productType));
                System.out.println("Product added successfully.");
                break;
            case 2:
                manager.displayProducts();
                break;
            case 3:
                System.out.println("Select the period for income view:");
                System.out.println("1. Daily");
                System.out.println("2. Weekly");
                System.out.println("3. Monthly");
                System.out.println("4. Yearly");
                System.out.print("Choose an option: ");
                int viewIncomeChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (viewIncomeChoice) {
                    case 1:
                        manager.viewIncome("daily");
                        break;
                    case 2:
                        manager.viewIncome("weekly");
                        break;
                    case 3:
                        manager.viewIncome("monthly");
                        break;
                    case 4:
                        manager.viewIncome("yearly");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}

