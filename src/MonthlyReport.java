import java.util.ArrayList;

public class MonthlyReport {
    private final ArrayList<Transaction> monthTransactions;

    public MonthlyReport(){
        monthTransactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction){
        monthTransactions.add(transaction);
    }

    public int getAmountOfIncome(){
        int sum = 0;
        for (Transaction transaction : monthTransactions){
            if (!transaction.isExpense())
                sum += transaction.getProfit();
        }
        return sum;
    }

    public int getAmountOfExpenses(){
        int sum = 0;
        for (Transaction transaction : monthTransactions){
            if (transaction.isExpense())
                sum += transaction.getProfit();
        }
        return sum;
    }

    public static String getMonthName(int monthNumber) {
        String[] monthNames = {
                "Январь", "Февраль", "Март", "Апрель",
                "Май", "Июнь", "Июль", "Август",
                "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
        };
        return monthNames[monthNumber - 1];
    }

    public Transaction getTheMostProfitableTransaction(){
        Transaction maxTransaction = null;
        boolean isNull = true;
        for(Transaction transaction : monthTransactions){
            if(!transaction.isExpense()){
                if(isNull){
                    maxTransaction = transaction;
                    isNull = false;
                }
                if(transaction.getProfit() > maxTransaction.getProfit())
                    maxTransaction = transaction;
            }
        }
        return maxTransaction;
    }

    public Transaction getTheLessProfitableTransaction(){
        Transaction maxTransaction = null;
        boolean isNull = true;
        for(Transaction transaction : monthTransactions){
            if(transaction.isExpense()){
                if(isNull){
                    maxTransaction = transaction;
                    isNull = false;
                }
                if(transaction.getProfit() > maxTransaction.getProfit())
                    maxTransaction = transaction;
            }
        }
        return maxTransaction;
    }
}
