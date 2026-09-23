package by.aleksandr.length;

public enum Unit {
    CENTIMETER("Сантиметр", 0.01),
    METER("Метр", 1.0),
    INCH("Дюйм", 0.0254),
    YARD("Ярд", 0.9144),
    COCKROACH("Американский таракан", 0.04),
    GIRAFFE_NECK("Шея жирафа", 2.0),
    LONGEST_SNAKE("Самая длинная змея", 10.0),
    HUMAN_TONGUE("Человеческий язык", 0.1),
    FOOTBALL_FIELD("Футбольное поле", 100.0);

    private final String name;
    private final double multiplierToMeters;

    Unit(String name, double multiplierToMeters) {
        this.name = name;
        this.multiplierToMeters = multiplierToMeters;
    }

    public double getMultiplier() { return multiplierToMeters; }
    @Override
    public String toString() { return name; }
}