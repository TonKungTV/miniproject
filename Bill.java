import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Bill {
    private int billNumber;
    private List<Product> products;
    private double totalAmount;
    private Date date;

    public Bill(int billNumber, List<Product> products, double totalAmount, Date date) {
        this.billNumber = billNumber;
        this.products = products;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Date getDate() {
        return date;
    }

    public void printBillDetails() {
        System.out.println("Bill Number: " + billNumber);
        System.out.println("Date: " + date);
        System.out.println("Products:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.printf("%s - $%.2f\n", product.getName(), product.getPrice(null)); // ส่ง null เพราะไม่ต้องใช้ข้อมูล customer
        }
        System.out.printf("Total Amount: $%.2f\n", totalAmount);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bill Number: ").append(billNumber).append("\n");
        sb.append("Products Purchased:\n");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            sb.append("- ").append(product.getName()).append("\n");
        }
        sb.append("Total Amount: ").append(totalAmount).append("\n");
        sb.append("Date: ").append(date).append("\n");
        return sb.toString();
    }
    
}
