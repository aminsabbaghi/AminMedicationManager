package processing;

import models.Medication;
import models.User;
import exceptions.InvalidDosageException;
import exceptions.InvalidTimeFormatException;

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

        // Step 1: Create a user
        System.out.println("Welcome to AminMedicationManager!");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter your gender: ");
        String gender = scanner.nextLine();

        User user = new User(name, age, gender);
        ReminderManager manager = new ReminderManager(user);

        // Step 2: Add medications
        System.out.print("How many medications do you want to add? ");
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
                i--; // retry this medication
            }
        }

        // Step 3: Choose schedule type
        System.out.print("\nChoose reminder schedule (daily/weekly): ");
        String scheduleMode = scanner.nextLine();
        manager.setScheduleMode(scheduleMode);

        // Step 4: Print reminders
        manager.printReminders();

        scanner.close();
    }
}
