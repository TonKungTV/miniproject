import java.util.ArrayList;
import java.util.List;

class Customer {
    private String name;
    private double wallet;
    private boolean isMember;
    private String membershipId;
    private List<Bill> billHistory = new ArrayList<>();

    public Customer(String name, double wallet) {
        this.name = name;
        this.wallet = wallet;
        this.isMember = false;
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

    public boolean isMember() {
        return isMember;
    }

    public String getName() {
        return name;
    }

    public void addBill(Bill bill) {
        billHistory.add(bill);
    }

    public void viewBill(int billNumber) {
        for (Bill bill : billHistory) {
            if (bill.getBillNumber() == billNumber) {
                System.out.println("Bill found: ");
                bill.printBillDetails();
                return;
            }
        }
        System.out.println("No bill found with number: " + billNumber);
    }

    public void viewWallet(String membershipId) {
        if (this.membershipId != null && this.membershipId.equals(membershipId)) {
            System.out.println("Wallet balance for member " + membershipId + ": " + wallet);
        } else {
            System.out.println("Invalid membership ID.");
        }
    }
}
