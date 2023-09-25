import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    ArrayList<YearlyReportRecord> reportRecords;
    private String year;

    public YearlyReport(){
        reportRecords = new ArrayList<>();
    }

    public void addRecord(YearlyReportRecord reportRecord){
        reportRecords.add(reportRecord);
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public HashMap<Integer, Integer> getMonthsProfits(){
        HashMap<Integer, Integer> yearsProfits = new HashMap<>();
        for (int i = 0; i < reportRecords.size() - 1; i += 2){
            YearlyReportRecord record1 = reportRecords.get(i);
            YearlyReportRecord record2 = reportRecords.get(i + 1);
            int sum = 0;

            if(record1.isExpense()){
                sum -= record1.getAmount();
                sum += record2.getAmount();
            } else {
                sum += record1.getAmount();
                sum -= record2.getAmount();
            }
            yearsProfits.put(record1.getMonth(), sum);
        }
        return yearsProfits;
    }

    public int getAverageIncome(){
        int sum = 0;
        int count = 0;
        for (YearlyReportRecord record : reportRecords){
            if(!record.isExpense()){
                sum += record.getAmount();
                count++;
            }
        }
        return sum / count;
    }

    public int getAverageExpenses(){
        int sum = 0;
        int count = 0;
        for (YearlyReportRecord record : reportRecords){
            if(record.isExpense()){
                sum += record.getAmount();
                count++;
            }
        }
        return sum / count;
    }
}
