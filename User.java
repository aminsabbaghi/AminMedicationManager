package models;
import java.util.ArrayList;
import java.util.List;

/**
 * AminMedicationManager
 * User Class
 * 
 * Represents a user of the system.
 * Stores user details and a list of medications.
 */

public class User {
    private String name;
    private int age;
    private String gender;
    private List<Medication> medications;

    public User(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.medications = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    // Add a medication
    public void addMedication(Medication med) {
        if (med != null) {
            medications.add(med);
        }
    }

    // Remove a medication
    public void removeMedication(Medication med) {
        medications.remove(med);
    }

    @Override
    public String toString() {
        return "User: " + name + " | Age: " + age + " | Gender: " + gender + " | Medications Count: " + medications.size();
    }
}

