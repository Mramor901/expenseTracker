package expensetracker.model;

import java.time.LocalDate;
import java.util.Date;

/**
 * Класс Expense представляет отдельную запись расхода.
 * Содержит:
 * - id: уникальный идентификатор расхода;
 * - description: описание расхода;
 * - amount: сумма расхода;
 * - date: дата, когда был совершен расход.
 */

public class Expense {

    private int id;
    private String description;
    private double amount;
    private LocalDate date;

    public  Expense(int id, String description, double amount, LocalDate date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
