import java.util.ArrayList;
import java.util.List;
import java.util.Date;

class PaymentProcessor {
    private static int billCounter = 0; // ตัวนับหมายเลขบิล

    public Bill processPayment(Customer customer, List<Product> products, boolean payWithWallet) {
        double totalAmount = 0.0;
        int totalPoints = 0;

        for (Product product : products) {
            double productPrice = product.getPrice(customer); // ราคาลดสำหรับสมาชิก
            totalAmount += productPrice;
            
            // Reduce stock by 1 for each purchased product
            product.reduceStock(1);

            // Accumulate points (1 point per product price)
            totalPoints += productPrice;
        }

        customer.addPoints(totalPoints);
        System.out.println("Earned " + totalPoints + " points!");

        if (payWithWallet) {
            if (customer.getWallet() >= totalAmount) {
                customer.deductFromWallet(totalAmount); // หักเงินจาก Wallet
                System.out.println("Payment successful from Wallet!");
            } else {
                System.out.println("Insufficient funds in wallet.");
                return null;
            }
        } else {
            System.out.println("Payment successful with Cash!");
        }

        Bill bill = new Bill(++billCounter, products, totalAmount, new Date()); // สร้างบิลใหม่และเพิ่มหมายเลขบิล
        customer.addBill(bill); // เพิ่มบิลเข้าไปในประวัติลูกค้า

        return bill;
    }
}

