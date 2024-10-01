import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

class Manager {
    private String name;
    private int nextBillNumber;
    private List<Bill> allBills = new ArrayList<>();
    private List<Product> products = new ArrayList<>();


    public Manager(String name) {
        this.name = name;
        this.products = new ArrayList<>();
        this.allBills = new ArrayList<>(); // Initialize the list
        this.nextBillNumber = 1;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void displayProducts() {
        System.out.println("\n--- Product List ---");
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.printf("%d. %s\n", (i + 1), product);
        }
        System.out.println("----------------------");
    }
    public void addBill(Bill bill) {
        allBills.add(bill); // เพิ่มบิลเข้าไปในรายการทั้งหมด
    }
    public void viewIncome(String period) {
        double totalIncome = 0.0;
        Date now = new Date();
        
        // ฟอร์แมตวันเวลา
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat weekFormat = new SimpleDateFormat("yyyy-ww");
        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    
        // เก็บวันเวลาปัจจุบัน
        String currentDate = sdf.format(now);
        String currentWeek = weekFormat.format(now);
        String currentMonth = monthFormat.format(now);
        String currentYear = yearFormat.format(now);
    
        // Loop ผ่านบิลทั้งหมด
        for (Bill bill : allBills) {
            String billDate = sdf.format(bill.getDate());
            String billWeek = weekFormat.format(bill.getDate());
            String billMonth = monthFormat.format(bill.getDate());
            String billYear = yearFormat.format(bill.getDate());
    
            // คำนวณรายได้ตามช่วงเวลาที่เลือก
            switch (period.toLowerCase()) {
                case "daily":
                    if (billDate.equals(currentDate)) {
                        totalIncome += bill.getTotalAmount();
                    }
                    break;
                case "weekly":
                    if (billWeek.equals(currentWeek)) {
                        totalIncome += bill.getTotalAmount();
                    }
                    break;
                case "monthly":
                    if (billMonth.equals(currentMonth)) {
                        totalIncome += bill.getTotalAmount();
                    }
                    break;
                case "yearly":
                    if (billYear.equals(currentYear)) {
                        totalIncome += bill.getTotalAmount();
                    }
                    break;
                default:
                    System.out.println("Invalid period. Choose from daily, weekly, monthly, yearly.");
                    return;
            }
        }
    
        System.out.println("Total " + period + " income: " + totalIncome);
    }
    

    public int getNextBillNumber() {
        return nextBillNumber++;
    }
}
