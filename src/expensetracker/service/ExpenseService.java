package expensetracker.service;

import expensetracker.model.Expense;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 ExpenseService инкапсулирует всю бизнес-логику приложения.
 Здесь реализованы методы для:
 1. Добавления нового расхода;
 2. Обновления существующего расхода;
 3. Удаления расхода;
 4. Получения списка всех расходов;
 5. Расчёта общей суммы расходов;
 6. Расчёта суммы расходов за заданный месяц текущего года.
 */

public class ExpenseService {

    private List<Expense> expenses;
    private int nextId;

    /**
     Конструктор.
      Изначально создаётся пустой список расходов, а id начинается с 1.
     */
    public ExpenseService() {
        expenses = new ArrayList<>();
        nextId = 1;
    }

    //Добавление нового расхода
    public void AddExpense(String description, double amount) {
    Expense expense = new Expense(nextId++, description, amount, LocalDate.now());
    expenses.add(expense);
    System.out.println("Expense added: " + expense);
    }


    public boolean UpdateExpense(int id, String newDescription, double newAmount) {
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                expense.setDescription(newDescription);
                expense.setAmount(newAmount);
                System.out.println("Expense update: " + expense);
                return true;
            }
        }
        System.out.println("Expense with id " + id + " not found");
        return false;
    }

    public boolean RemoveExpense(int id) {
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                expenses.remove(expense);
                System.out.println("Expense remove: " + expense);
                return true;
            }
        }
        System.out.println("Expense with id " + id + " not found");
        return false;
    }


    public List<Expense> GetAllExpenses() {
        return new ArrayList<>(expenses);
    }


    public double getTotalExpenses() {
        double total = 0.0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    /**
     Возвращает сумму расходов за данный месяц текущего года.
     Фильтрует расходы по году и месяцу, затем суммирует их.
     @param month номер месяца (1-12).
     @return общая сумма расходов в указанном месяце.
     */
    public double getMonthlySummary(int month) {
        int currentYear = LocalDate.now().getYear();
        double total = expenses.stream()
                .filter(expense -> expense.getDate().getYear() == currentYear && expense.getDate().getMonthValue() == month)
                .mapToDouble(Expense::getAmount)
                .sum();
        return total;
    }


}
