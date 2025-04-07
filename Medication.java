package models;

import exceptions.InvalidDosageException;
import exceptions.InvalidTimeFormatException;

public class Medication {
    private String name;
    private double dosageMg;
    private String frequency;
    private String administrationTime;

    public Medication(String name, double dosageMg, String frequency, String administrationTime)
            throws InvalidDosageException, InvalidTimeFormatException {
        this.name = name;
        setDosageMg(dosageMg);
        this.frequency = frequency;
        setAdministrationTime(administrationTime);
    }

    public String getName() {
        return name;
    }

    public double getDosageMg() {
        return dosageMg;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getAdministrationTime() {
        return administrationTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDosageMg(double dosageMg) throws InvalidDosageException {
        if (dosageMg <= 0) {
            throw new InvalidDosageException("Dosage must be greater than 0 mg.");
        }
        this.dosageMg = dosageMg;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setAdministrationTime(String administrationTime) throws InvalidTimeFormatException {
        if (!administrationTime.matches("^(0?[1-9]|1[0-2]):[0-5][0-9] [APap][Mm]$")) {
            throw new InvalidTimeFormatException("Time must be in format like '08:00 AM'.");
        }
        this.administrationTime = administrationTime;
    }

    @Override
    public String toString() {
        return "Medication: " + name + " | Dosage: " + dosageMg + "mg | Frequency: " + frequency + " | Time: " + administrationTime;
    }
}
