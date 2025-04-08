package utils;

import models.Medication;
import models.User;
import exceptions.InvalidDosageException;
import exceptions.InvalidTimeFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AminMedicationManager
 * FileHandler Utility
 * 
 * Provides methods to save and load a user's medication list to/from a text file.
 */
public class FileHandler {

    // Save all medications to a text file
    public static void saveMedicationsToFile(User user, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Medication med : user.getMedications()) {
                writer.write(med.getName() + "," +
                             med.getDosageMg() + "," +
                             med.getFrequency() + "," +
                             med.getAdministrationTime());
                writer.newLine();
            }
            System.out.println("Medications saved successfully to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving medications: " + e.getMessage());
        }
    }

    // Load medications from a text file into the user
    public static void loadMedicationsFromFile(User user, String filePath) {
        List<Medication> loadedMeds = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length != 4) continue;

                String name = parts[0].trim();
                double dosage = Double.parseDouble(parts[1].trim());
                String frequency = parts[2].trim();
                String time = parts[3].trim();

                try {
                    Medication med = new Medication(name, dosage, frequency, time);
                    loadedMeds.add(med);
                } catch (InvalidDosageException | InvalidTimeFormatException e) {
                    System.out.println("Skipping invalid medication: " + e.getMessage());
                }
            }

            for (Medication med : loadedMeds) {
                user.addMedication(med);
            }

            System.out.println("Loaded " + loadedMeds.size() + " medication(s) from " + filePath);

        } catch (IOException e) {
            System.out.println("Error loading medications: " + e.getMessage());
        }
    }
    public static void saveRemindersToFile(List<String> reminders, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String reminder : reminders) {
                writer.write(reminder);
                writer.newLine();
            }
            System.out.println("Reminders saved successfully to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving reminders: " + e.getMessage());
        }
    }

}
