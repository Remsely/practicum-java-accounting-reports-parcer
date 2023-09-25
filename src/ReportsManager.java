import java.util.ArrayList;
import java.util.HashMap;

public class ReportsManager {
    HashMap<Integer, MonthlyReport> monthReportsTable;
    YearlyReport yearlyReport;
    FileReader fileReader;
    boolean yearlyReportIsBeenRead;
    boolean monthlyReportsAreBeenRead;

    public ReportsManager(){
        monthReportsTable = new HashMap<>();
        fileReader = new FileReader();
        yearlyReport = new YearlyReport();
        yearlyReportIsBeenRead = false;
        monthlyReportsAreBeenRead = false;
    }

    public void readMonthlyReports(){
        for (int i = 1; i <= 3; i++){
            MonthlyReport monthlyReport = new MonthlyReport();

            String fileName = "m.20210" + i + ".csv";
            ArrayList<String> lines = fileReader.readFileContents(fileName);
            lines.remove(0);

            for (String line : lines){
                String[] lineContent = line.split(",");

                String itemName = lineContent[0];
                boolean isExpense = Boolean.parseBoolean(lineContent[1]);
                int quantity = Integer.parseInt(lineContent[2]);
                int unitPrice = Integer.parseInt(lineContent[3]);

                monthlyReport.addTransaction(new Transaction(itemName, isExpense, quantity, unitPrice));
            }
            monthReportsTable.put(i, monthlyReport);
        }
        monthlyReportsAreBeenRead = true;
    }

    public void readYearlyReport(){
        String yearNumber = "2021";
        String fileName = "y." + yearNumber + ".csv";

        ArrayList<String> lines = fileReader.readFileContents(fileName);
        lines.remove(0);

        for (String line : lines){
            String[] lineContent = line.split(",");

            int month = Integer.parseInt(lineContent[0]);
            int amount = Integer.parseInt(lineContent[1]);
            boolean isExpense = Boolean.parseBoolean(lineContent[2]);

            yearlyReport.reportRecords.add(new YearlyReportRecord(month, amount, isExpense));
        }
        yearlyReport.setYear(yearNumber);
        yearlyReportIsBeenRead = true;
    }

    public void compareReports(){
        if(monthlyReportsNotRead() | yearlyReportNotRead())
            return;

        for (YearlyReportRecord record : yearlyReport.reportRecords){
            if (record.isExpense()){
                if(record.getAmount() != monthReportsTable.get(record.getMonth()).getAmountOfExpenses())
                    System.out.println("В " + record.getMonth() + "-ом месяце не сходятся расходы!");
            } else {
                if(record.getAmount() != monthReportsTable.get(record.getMonth()).getAmountOfIncome())
                    System.out.println("В " + record.getMonth() + "-ом месяце не сходятся доходы!");
            }
        }
        System.out.println("Несоответствий в отчетах необнаружено!");
    }

    private boolean monthlyReportsNotRead(){
        if(!monthlyReportsAreBeenRead){
            System.out.println("Вы не считали месячные отчеты!");
            return true;
        }
        return false;
    }

    private boolean yearlyReportNotRead(){
        if(!yearlyReportIsBeenRead){
            System.out.println("Вы не считали годовой отчет!");
            return true;
        }
        return false;
    }

    public void printMonthlyReportsInfo(){
        if(monthlyReportsNotRead())
            return;

        for (Integer month : monthReportsTable.keySet()){
            System.out.println("Месяц: " + MonthlyReport.getMonthName(month));
            MonthlyReport monthlyReport = monthReportsTable.get(month);

            Transaction profitableTransaction = monthlyReport.getTheMostProfitableTransaction();
            System.out.println("Самый прибыльный товар: " + profitableTransaction.getItemName() +
                    "; Доход - " + profitableTransaction.getProfit());

            Transaction unprofitableTransaction = monthlyReport.getTheLessProfitableTransaction();
            System.out.println("Самый большая трата: " + unprofitableTransaction.getItemName() +
                    "; Расход - " + unprofitableTransaction.getProfit());
            System.out.println();
        }
    }

    public void printYearlyReportInfo(){
        if(yearlyReportNotRead())
            return;

        System.out.println("Год: " + yearlyReport.getYear());
        HashMap<Integer, Integer> monthsProfits = yearlyReport.getMonthsProfits();
        System.out.println();
        for (Integer monthNumber : monthsProfits.keySet()){
            System.out.println("Месяц: " + MonthlyReport.getMonthName(monthNumber) + "; Прибыль: "
                    + monthsProfits.get(monthNumber));
        }
        System.out.println();
        System.out.println("Средний расход за все имеющиеся операции в году: " + yearlyReport.getAverageExpenses());
        System.out.println("Средний доход за все имеющиеся операции в году: " + yearlyReport.getAverageIncome());
    }
}
