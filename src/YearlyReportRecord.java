public class YearlyReportRecord {
    private final int month;
    private final int amount;
    private final boolean isExpense;

    public YearlyReportRecord(int month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    public int getAmount() {
        return amount;
    }

    public int getMonth() {
        return month;
    }

    public boolean isExpense() {
        return isExpense;
    }
}
