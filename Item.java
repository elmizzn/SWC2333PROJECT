public class Item {
    private int itemId;
    private String name;
    private int quantity;
    private double price;
    private String supplier;
    private int reorderLevel;
    private String barcode;
    private String expirationDate;

    public Item(int itemId, String name, int quantity, double price, String supplier, int reorderLevel, String barcode, String expirationDate) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.supplier = supplier;
        this.reorderLevel = reorderLevel;
        this.barcode = barcode;
        this.expirationDate = expirationDate;
    }

    // Getters and setters for all fields
    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getSupplier() {
        return supplier;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
