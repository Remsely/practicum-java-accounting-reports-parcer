import java.util.Scanner;

public class Main {
    public static void printMenu(){
        System.out.println("-------------------------------------------------");
        System.out.println("Выберите действие:");
        System.out.println("1 - Считать все месячные отчеты;");
        System.out.println("2 - Считать годовой отчет;");
        System.out.println("3 - Сверить отчеты;");
        System.out.println("4 - Вывести информацию обо всех месячных отчетах;");
        System.out.println("5 - Вывести информацию о годовом отчете;");
        System.out.println("0 - Завершить работу программы;");
        System.out.println("-------------------------------------------------");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportsManager reportsManager = new ReportsManager();


        boolean isWorking = true;
        while (isWorking){
            printMenu();
            int userChoice = scanner.nextInt();
            switch (userChoice){
                case 1:
                    reportsManager.readMonthlyReports();
                    break;
                case 2:
                    reportsManager.readYearlyReport();
                    break;
                case 3:
                    reportsManager.compareReports();
                    break;
                case 4:
                    reportsManager.printMonthlyReportsInfo();
                    break;
                case 5:
                    reportsManager.printYearlyReportInfo();
                    break;
                case 0:
                    isWorking = false;
                    System.out.println("Завершение работы программы...");
                    break;
                default:
                    System.out.println("Вы ввели некорректную команду! Попробуйте снова!");
                    break;
            }
        }
    }
}

