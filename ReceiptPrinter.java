class ReceiptPrinter {
    public void printReceipt(Bill bill) {
        String storeName = "NextMart";
        String storeAddress = "123 Main St, City, Country";
        String storePhone = "Phone: 123-456-7890";
    
        String separator = "--------------------------------------";
        String lineSeparator = "======================================";
        
        System.out.printf("Bill Number: %-29s", bill.getBillNumber());
        System.out.println("\n" + lineSeparator);
        System.out.printf("%-20s %20s\n", storeName, " ");
        System.out.printf("%-20s %20s\n", storeAddress, " ");
        System.out.printf("%-20s %20s\n", storePhone, " ");
        System.out.println(lineSeparator);
        System.out.printf("Date: %-29s\n", bill.getDate());
        System.out.println(separator);
        System.out.println("Items:");
        
        for (int i = 0; i < bill.getProducts().size(); i++) {
            Product product = bill.getProducts().get(i);
            System.out.printf("%-25s %10.2f\n", product.getName(), product.getPrice(null)); // ส่ง null เพราะไม่ต้องใช้ข้อมูล customer
        }
        
        System.out.printf("Total Amount: %-25.2f\n", bill.getTotalAmount());
        System.out.println(separator);
        System.out.println("Thank you for shopping at " + storeName + "!");
        System.out.println(lineSeparator);
    }
}
