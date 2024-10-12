import java.util.ArrayList;
import java.util.List;

class Customer {
    private String name;
    private double wallet;
    private boolean isMember;
    private String membershipId;
    private int points;
    
    private List<Bill> billHistory = new ArrayList<>();

    public Customer(String name, double wallet) {
        this.name = name;
        this.wallet = wallet;
        this.isMember = false;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int amount) {
        points += amount;
    }
    public void addMoney(double amount) {
        wallet += amount;
    }

    public void deductFromWallet(double amount) {
        if (wallet >= amount) {
            wallet -= amount;
        } else {
            System.out.println("Insufficient funds in wallet.");
        }
    }

    public double getWallet() {
        return wallet;
    }
    
    public void setMembership(boolean isMember, String membershipId) {
        this.isMember = isMember;
        this.membershipId = membershipId;
    }
    public String getMembershipId() {
        return membershipId;
    }

    public boolean isMember() {
        return isMember;
    }

    public String getName() {
        return name;
    }
    public double getWalletBalance() {
        return wallet;
    }

    public void addBill(Bill bill) {
        billHistory.add(bill);
    }

    public void viewBill(int billNumber) {
        for (int i = 0; i < billHistory.size(); i++) {
            Bill bill = billHistory.get(i);
            if (bill.getBillNumber() == billNumber) {
                System.out.println("Bill found: ");
                bill.printBillDetails();
                return;
            }
        }
        System.out.println("No bill found with number: " + billNumber);
    }
    public void viewAllBills() {
        if (billHistory.isEmpty()) {
            System.out.println("No bills found.");
        } else {
            for (int i = 0; i < billHistory.size(); i++) {
                Bill bill = billHistory.get(i);
                System.out.println(bill);
            }
        }
    }
    

    public void viewWallet(String membershipId) {
        if (this.membershipId != null && this.membershipId.equals(membershipId)) {
            System.out.println("Wallet balance for member " + membershipId + ": " + wallet);
            System.out.println("Points: " + points);
        } else {
            System.out.println("Invalid membership ID.");
        }
    }
}
