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

    // ฟังก์ชันใหม่ที่เพิ่มเพื่อคำนวณจำนวนสินค้าทั้งหมดในบิล
    public int getTotalQuantity() {
        int totalQuantity = 0;
        List<GroupedProduct> groupedProducts = groupProducts();
        
        // ใช้ for loop แทนการใช้ for-each
        for (int i = 0; i < groupedProducts.size(); i++) {
            totalQuantity += groupedProducts.get(i).getQuantity();  // บวกจำนวนสินค้าแต่ละประเภท
        }
        return totalQuantity;
    }

    // แก้ไขการจัดกลุ่มสินค้าโดยใช้ for loop แทนการใช้ for-each
    public List<GroupedProduct> groupProducts() {
        List<GroupedProduct> groupedProducts = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Product currentProduct = products.get(i);
            boolean found = false;
            for (int j = 0; j < groupedProducts.size(); j++) {
                GroupedProduct groupedProduct = groupedProducts.get(j);
                if (groupedProduct.getProduct().getName().equals(currentProduct.getName())) {
                    groupedProduct.addQuantity(1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                groupedProducts.add(new GroupedProduct(currentProduct, 1));
            }
        }
        return groupedProducts;
    }

    public void printBillDetails() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printBillDetails'");
    }
}

