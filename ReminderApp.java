package processing;

import models.Medication;
import models.User;
import exceptions.InvalidDosageException;
import exceptions.InvalidTimeFormatException;
import utils.FileHandler;

import java.util.Scanner;

/**
 * AminMedicationManager
 * ReminderApp (Main Class)
 * 
 * Entry point to run the medication reminder system.
 */

public class ReminderApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String medFilePath = "medications.txt";

        System.out.println("Welcome to AminMedicationManager!");

        // Step 1: Create a user
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter your gender: ");
        String gender = scanner.nextLine();

        User user = new User(name, age, gender);
        ReminderManager manager = new ReminderManager(user);

        // Step 2: Load medications from file?
        System.out.print("Do you want to load medications from a file? (yes/no): ");
        String loadChoice = scanner.nextLine();
        if (loadChoice.equalsIgnoreCase("yes")) {
            FileHandler.loadMedicationsFromFile(user, medFilePath);
        }

        // Step 3: Add new medications
        System.out.print("How many new medications do you want to add? ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count; i++) {
            System.out.println("\nMedication #" + (i + 1));
            System.out.print("Name: ");
            String medName = scanner.nextLine();
            System.out.print("Dosage (mg): ");
            double dosage = Double.parseDouble(scanner.nextLine());
            System.out.print("Frequency (e.g., Daily): ");
            String freq = scanner.nextLine();
            System.out.print("Administration Time (e.g., 08:00 AM): ");
            String time = scanner.nextLine();

            try {
                Medication med = new Medication(medName, dosage, freq, time);
                manager.addMedication(med);
            } catch (InvalidDosageException | InvalidTimeFormatException e) {
                System.out.println("Error: " + e.getMessage());
                i--; // retry current med
            }
        }

        // Step 4: Set schedule type
        System.out.print("\nChoose reminder schedule (daily/weekly): ");
        String scheduleMode = scanner.nextLine();
        manager.setScheduleMode(scheduleMode);

        // Step 5: Print reminders
        manager.printReminders();

        // Step 6: Save medications
        System.out.print("\nDo you want to save medications to a file? (yes/no): ");
        String saveChoice = scanner.nextLine();
        if (saveChoice.equalsIgnoreCase("yes")) {
            FileHandler.saveMedicationsToFile(user, medFilePath);
        }

        // Step 7: Save reminders
        System.out.print("\nDo you want to save these reminders to a file? (yes/no): ");
        String reminderSaveChoice = scanner.nextLine();
        if (reminderSaveChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter file name (e.g., reminders.txt): ");
            String reminderFile = scanner.nextLine();
            FileHandler.saveRemindersToFile(manager.getSchedule().getReminders(), reminderFile);
        }

        scanner.close();
    }
}
