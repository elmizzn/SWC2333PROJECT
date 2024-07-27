import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class InventoryApp {
    private InventoryManager manager;
    private JFrame frame;
    private JTextField itemIdField, nameField, quantityField, priceField, supplierField, reorderField, searchField, deleteField, updateField;
    private JTable inventoryTable;
    private DefaultTableModel tableModel;

    public InventoryApp() {
        manager = new InventoryManager();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Supermarket Inventory Management System");
        frame.setBounds(100, 100, 1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel itemIdLabel = new JLabel("Item ID:");
        itemIdLabel.setBounds(10, 20, 80, 25);
        frame.getContentPane().add(itemIdLabel);

        itemIdField = new JTextField();
        itemIdField.setBounds(155, 20, 150, 25);
        frame.getContentPane().add(itemIdField);

        JLabel nameLabel = new JLabel("Item Name:");
        nameLabel.setBounds(10, 60, 80, 25);
        frame.getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(155, 60, 150, 25);
        frame.getContentPane().add(nameField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(10, 100, 80, 25);
        frame.getContentPane().add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(155, 100, 150, 25);
        frame.getContentPane().add(quantityField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 140, 80, 25);
        frame.getContentPane().add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(155, 140, 150, 25);
        frame.getContentPane().add(priceField);

        JLabel supplierLabel = new JLabel("Supplier:");
        supplierLabel.setBounds(10, 180, 80, 25);
        frame.getContentPane().add(supplierLabel);

        supplierField = new JTextField();
        supplierField.setBounds(155, 180, 150, 25);
        frame.getContentPane().add(supplierField);

        JLabel reorderLabel = new JLabel("Reorder Level:");
        reorderLabel.setBounds(10, 220, 100, 25);
        frame.getContentPane().add(reorderLabel);

        reorderField = new JTextField();
        reorderField.setBounds(155, 220, 150, 25);
        frame.getContentPane().add(reorderField);

        JButton addButton = new JButton("Add Item");
        addButton.setBounds(10, 260, 100, 25);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
        frame.getContentPane().add(addButton);

        JLabel searchLabel = new JLabel("Search by Item ID:");
        searchLabel.setBounds(406, 20, 120, 25);
        frame.getContentPane().add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(510, 20, 150, 25);
        frame.getContentPane().add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(684, 20, 80, 25);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchItemById();
            }
        });
        frame.getContentPane().add(searchButton);

        JLabel deleteLabel = new JLabel("Delete by Item ID:");
        deleteLabel.setBounds(406, 60, 120, 25);
        frame.getContentPane().add(deleteLabel);

        deleteField = new JTextField();
        deleteField.setBounds(510, 60, 150, 25);
        frame.getContentPane().add(deleteField);

        JButton deleteByIdButton = new JButton("Delete");
        deleteByIdButton.setBounds(684, 60, 80, 25);
        deleteByIdButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteItemById();
            }
        });
        frame.getContentPane().add(deleteByIdButton);

        JLabel updateLabel = new JLabel("Update by Item ID:");
        updateLabel.setBounds(406, 100, 120, 25);
        frame.getContentPane().add(updateLabel);

        updateField = new JTextField();
        updateField.setBounds(510, 100, 150, 25);
        frame.getContentPane().add(updateField);

        JButton updateByIdButton = new JButton("Update");
        updateByIdButton.setBounds(684, 100, 80, 25);
        updateByIdButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateItemById();
            }
        });
        frame.getContentPane().add(updateByIdButton);

        // JTable for inventory
        String[] columnNames = {"Item ID", "Item Name", "Quantity", "Price", "Supplier", "Reorder Level", "Barcode Number", "Expired Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        inventoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(inventoryTable);
        scrollPane.setBounds(10, 300, 950, 300);
        frame.getContentPane().add(scrollPane);

        JButton reportButton = new JButton("Generate Report");
        reportButton.setBounds(10, 620, 150, 25);
        reportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });
        frame.getContentPane().add(reportButton);

        JButton retrieveButton = new JButton("Retrieve Data");
        retrieveButton.setBounds(170, 620, 150, 25);
        retrieveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retrieveData();
            }
        });
        frame.getContentPane().add(retrieveButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(330, 620, 150, 25);
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshInventory();
            }
        });
        frame.getContentPane().add(refreshButton);

        frame.setVisible(true);
    }

    private void addItem() {
        try {
            int itemId = Integer.parseInt(itemIdField.getText().replace("ID", ""));
            String name = nameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            String supplier = supplierField.getText();
            int reorderLevel = Integer.parseInt(reorderField.getText());
            String barcodeNumber = "000000000000"; // Placeholder for barcode number
            String expiredDate = "2025-01-01"; // Placeholder for expired date
            manager.addItem(new Item(itemId, name, quantity, price, supplier, reorderLevel, barcodeNumber, expiredDate));
            refreshInventory();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers for item ID, quantity, price, and reorder level.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }
    }

    private void updateItemById() {
        try {
            int itemId = Integer.parseInt(updateField.getText().replace("ID", ""));
            String name = nameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            String supplier = supplierField.getText();
            int reorderLevel = Integer.parseInt(reorderField.getText());
            String barcodeNumber = "000000000000"; // Placeholder for barcode number
            String expiredDate = "2025-01-01"; // Placeholder for expired date
            manager.updateItem(itemId, name, quantity, price, supplier, reorderLevel, barcodeNumber, expiredDate);
            refreshInventory();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers for item ID, quantity, price, and reorder level.");
        }
    }

    private void deleteItemById() {
        try {
            int itemId = Integer.parseInt(deleteField.getText().replace("ID", ""));
            manager.deleteItem(itemId);
            refreshInventory();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number for item ID.");
        }
    }

    private void generateReport() {
        String report = manager.generateReport();
        saveReportToFile(report);
    }

    private void retrieveData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("inventory_report.txt"))) {
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
            manager.loadInventoryFromFile(data.toString());
            refreshInventory();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error retrieving data from file.");
        }
    }

    private void refreshInventory() {
        tableModel.setRowCount(0); // Clear existing data
        for (Item item : manager.getInventory()) {
            Object[] row = {String.format("ID%03d", item.getItemId()), item.getName(), item.getQuantity(), item.getPrice(), item.getSupplier(), item.getReorderLevel(), item.getBarcode(), item.getExpirationDate()};
            tableModel.addRow(row);
        }
    }

    private void saveReportToFile(String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory_report.txt"))) {
            writer.write(report);
            JOptionPane.showMessageDialog(frame, "Report saved to inventory_report.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving report to file.");
        }
    }

    private void searchItemById() {
        try {
            int itemId = Integer.parseInt(searchField.getText().replace("ID", ""));
            Item item = manager.findItemById(itemId);
            if (item != null) {
                tableModel.setRowCount(0); // Clear existing data
                Object[] row = {String.format("ID%03d", item.getItemId()), item.getName(), item.getQuantity(), item.getPrice(), item.getSupplier(), item.getReorderLevel(), item.getBarcode(), item.getExpirationDate()};
                tableModel.addRow(row);
            } else {
                JOptionPane.showMessageDialog(frame, "Item not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number for item ID.");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InventoryApp window = new InventoryApp();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
