import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StockDashboard extends JFrame implements ActionListener {

    JComboBox<String> stockBox;
    JTextField quantityField;
    JButton buyBtn, sellBtn;

    JTable table;
    DefaultTableModel model;

    JLabel balanceLabel, sharesLabel, profitLabel;

    int balance = 100000;
    int totalShares = 0;
    int profitLoss = 0;

    StockDashboard() {
        setTitle("Stock Trading Platform");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();

        stockBox = new JComboBox<>(new String[]{
                "Apple - 200",
                "Tesla - 500",
                "Google - 300"
        });

        quantityField = new JTextField(5);

        buyBtn = new JButton("Buy");
        sellBtn = new JButton("Sell");

        top.add(new JLabel("Stock"));
        top.add(stockBox);
        top.add(new JLabel("Quantity"));
        top.add(quantityField);
        top.add(buyBtn);
        top.add(sellBtn);

        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"Stock", "Qty", "Price", "Type", "Total"}, 0
        );

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        balanceLabel = new JLabel("Balance: ₹100000");
        sharesLabel = new JLabel("Shares: 0");
        profitLabel = new JLabel("Profit/Loss: ₹0");

        bottom.add(balanceLabel);
        bottom.add(sharesLabel);
        bottom.add(profitLabel);

        add(bottom, BorderLayout.SOUTH);

        buyBtn.addActionListener(this);
        sellBtn.addActionListener(this);

        setVisible(true);
    }

    int getPrice() {
        String stock = stockBox.getSelectedItem().toString();
        if (stock.contains("200")) return 200;
        if (stock.contains("500")) return 500;
        return 300;
    }

    void updateStats() {
        balanceLabel.setText("Balance: ₹" + balance);
        sharesLabel.setText("Shares: " + totalShares);
        profitLabel.setText("Profit/Loss: ₹" + profitLoss);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String stock = stockBox.getSelectedItem().toString();
            int qty = Integer.parseInt(quantityField.getText());
            int price = getPrice();
            int total = qty * price;

            if (e.getSource() == buyBtn) {
                if (balance >= total) {
                    balance -= total;
                    totalShares += qty;

                    model.addRow(new Object[]{
                            stock, qty, price, "BUY", total
                    });
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance");
                }
            }

            if (e.getSource() == sellBtn) {
                if (totalShares >= qty) {
                    balance += total;
                    totalShares -= qty;
                    profitLoss += qty * 50;

                    model.addRow(new Object[]{
                            stock, qty, price, "SELL", total
                    });
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough shares");
                }
            }

            updateStats();
            quantityField.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Enter valid quantity");
        }
    }
}