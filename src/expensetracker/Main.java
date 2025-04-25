package expensetracker;


import expensetracker.service.ExpenseService;
import expensetracker.model.Expense;

import java.util.List;
import java.util.Scanner;

/**
 * Main – это точка входа в приложение Expense Tracker.
 * Здесь реализовано пользовательское меню для работы с расходами через командную строку.
 */
public class Main {
    public static void main(String[] args) {

        ExpenseService expenseService = new ExpenseService();


        Scanner scanner = new Scanner(System.in);
        boolean exit = false;


        while (!exit) {
            printMenu();
            System.out.print("Введите ваш выбор: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":

                    System.out.print("Введите описание расхода: ");
                    String description = scanner.nextLine();
                    double amount = 0.0;
                    System.out.print("Введите сумму расхода: ");
                    try {
                        amount = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Неверная сумма. Попробуйте снова.");
                        break;
                    }
                    expenseService.AddExpense(description, amount);
                    break;
                case "2":

                    System.out.print("Введите ID расхода для обновления: ");
                    int updateId;
                    try {
                        updateId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат ID.");
                        break;
                    }
                    System.out.print("Введите новое описание: ");
                    String newDescription = scanner.nextLine();
                    double newAmount = 0.0;
                    System.out.print("Введите новую сумму: ");
                    try {
                        newAmount = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат суммы.");
                        break;
                    }
                    expenseService.UpdateExpense(updateId, newDescription, newAmount);
                    break;
                case "3":

                    System.out.print("Введите ID расхода для удаления: ");
                    int deleteId;
                    try {
                        deleteId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат ID.");
                        break;
                    }
                    expenseService.RemoveExpense(deleteId);
                    break;
                case "4":

                    List<Expense> expenses = expenseService.GetAllExpenses();
                    if (expenses.isEmpty()) {
                        System.out.println("Расходов нет.");
                    } else {
                        System.out.println("Список всех расходов:");
                        for (Expense expense : expenses) {
                            System.out.println(expense);
                        }
                    }
                    break;
                case "5":

                    double total = expenseService.getTotalExpenses();
                    System.out.printf("Общая сумма расходов: %.2f%n", total);
                    break;
                case "6":
          
                    System.out.print("Введите номер месяца (1-12): ");
                    int month;
                    try {
                        month = Integer.parseInt(scanner.nextLine());
                        if (month < 1 || month > 12) {
                            System.out.println("Месяц должен быть от 1 до 12.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат месяца.");
                        break;
                    }
                    double monthlyTotal = expenseService.getMonthlySummary(month);
                    System.out.printf("Суммарные расходы за месяц %d: %.2f%n", month, monthlyTotal);
                    break;
                case "7":
                    // Выход из приложения
                    exit = true;
                    System.out.println("Выход из Expense Tracker. До свидания!");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }

            System.out.println(); // Пустая строка для разделения операций
        }

        // Закрываем Scanner
        scanner.close();
    }

    /**
     * Метод для печати меню с вариантами выбора.
     */
    private static void printMenu() {
        System.out.println("==== Меню Expense Tracker ====");
        System.out.println("1. Добавить расход");
        System.out.println("2. Обновить расход");
        System.out.println("3. Удалить расход");
        System.out.println("4. Просмотреть все расходы");
        System.out.println("5. Просмотреть сводную информацию по всем расходам");
        System.out.println("6. Просмотреть сводную информацию по расходам за конкретный месяц");
        System.out.println("7. Выход");
    }
}
