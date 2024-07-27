import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<Item> inventory;

    public InventoryManager() {
        inventory = new ArrayList<>();
    }

    public void addItem(Item item) {
        for (Item existingItem : inventory) {
            if (existingItem.getItemId() == item.getItemId()) {
                throw new IllegalArgumentException("Item ID already exists.");
            }
        }
        inventory.add(item);
    }

    public void updateItem(int itemId, String name, int quantity, double price, String supplier, int reorderLevel, String barcodeNumber, String expiredDate) {
        for (Item item : inventory) {
            if (item.getItemId() == itemId) {
                item.setName(name);
                item.setQuantity(quantity);
                item.setPrice(price);
                item.setSupplier(supplier);
                item.setReorderLevel(reorderLevel);
                item.setBarcode(barcodeNumber);
                item.setExpirationDate(expiredDate);
                return;
            }
        }
        throw new IllegalArgumentException("Item ID not found.");
    }

    public void deleteItem(int itemId) {
        inventory.removeIf(item -> item.getItemId() == itemId);
    }

    public Item findItemById(int itemId) {
        for (Item item : inventory) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        return null;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        for (Item item : inventory) {
            report.append(item.getItemId()).append("\t")
                  .append(item.getName()).append("\t")
                  .append(item.getQuantity()).append("\t")
                  .append(item.getPrice()).append("\t")
                  .append(item.getSupplier()).append("\t")
                  .append(item.getReorderLevel()).append("\t")
                  .append(item.getBarcode()).append("\t")
                  .append(item.getExpirationDate()).append("\n");
        }
        return report.toString();
    }

    public void loadInventoryFromFile(String data) {
        inventory.clear();
        String[] lines = data.split("\n");
        for (String line : lines) {
            if (line.trim().isEmpty() || line.startsWith("Item ID")) {
                continue; // skip empty lines and header
            }
            String[] parts = line.split("\t");
            if (parts.length != 8) {
                throw new IllegalArgumentException("Incorrect file format");
            }
            Item item = new Item(Integer.parseInt(parts[0].replace("ID", "")), parts[1], Integer.parseInt(parts[2]),
                                 Double.parseDouble(parts[3]), parts[4], Integer.parseInt(parts[5]), parts[6], parts[7]);
            inventory.add(item);
        }
    }
}
