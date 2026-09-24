package by.aleksandr.length;

public class LengthController {
    private final LengthModel model;

    public LengthController(LengthModel model) {
        this.model = model;
    }

    public void processConversion(String input, Unit from, Unit to) {
        model.calculate(input, from, to);
    }

    // Новый метод контроллера для передачи команды очистки
    public void clear() {
        model.clear();
    }
}