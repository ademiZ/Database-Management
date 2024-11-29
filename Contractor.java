package org.example;

public class Contractor extends Employee {
    private double hourlyRate;
    private double maxHours;

    public Contractor(String name, double hourlyRate, double maxHours) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.maxHours = maxHours;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * maxHours;
    }
}
