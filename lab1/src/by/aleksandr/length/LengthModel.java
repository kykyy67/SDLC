package by.aleksandr.length;

import java.util.ArrayList;
import java.util.List;

public class LengthModel {
    private String lastInput = "";
    private Unit lastFromUnit = Unit.METER;
    private Unit lastToUnit = Unit.CENTIMETER;
    private Double result = null;
    private String errorMessage = null;

    private final List<ModelObserver> observers = new ArrayList<>();

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (ModelObserver obs : observers) {
            obs.onModelChanged();
        }
    }

    public void calculate(String input, Unit from, Unit to) {
        this.lastInput = input;
        this.lastFromUnit = from;
        this.lastToUnit = to;
        this.errorMessage = null;

        try {
            double value = Double.parseDouble(input.replace(",", "."));
            this.result = (value * from.getMultiplier()) / to.getMultiplier();
        } catch (NumberFormatException e) {
            this.errorMessage = "Ошибка: Введены некорректные данные!";
        }
        notifyObservers();
    }

    // Новый метод для очистки результатов
    public void clear() {
        this.result = null;
        this.errorMessage = null;
        notifyObservers();
    }

    public String getLastInput() { return lastInput; }
    public Unit getLastFromUnit() { return lastFromUnit; }
    public Unit getLastToUnit() { return lastToUnit; }
    public Double getResult() { return result; }
    public String getErrorMessage() { return errorMessage; }
}