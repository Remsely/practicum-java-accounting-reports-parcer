public class Transaction {
    private final String itemName;
    private final boolean isExpense;
    private final int quantity;
    private final int unitPrice;

    public Transaction(String itemName, boolean isExpense, int quantity, int unitPrice) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public int getProfit(){
        return quantity * unitPrice;
    }
}
