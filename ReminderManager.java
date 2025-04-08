package processing;

import models.Medication;
import models.User;
import schedule.DailyReminderSchedule;
import schedule.ReminderSchedule;
import schedule.WeeklyReminderSchedule;

import java.util.List;

/**
 * AminMedicationManager
 * ReminderManager
 * 
 * Handles user medication management and schedule generation.
 */

public class ReminderManager {

    private User user;
    private ReminderSchedule schedule;

    public ReminderManager(User user) {
        this.user = user;
    }

    // Add a medication to the user
    public void addMedication(Medication med) {
        user.addMedication(med);
    }

    // Remove a medication
    public void removeMedication(Medication med) {
        user.removeMedication(med);
    }

    // Set scheduling mode: "daily" or "weekly"
    public void setScheduleMode(String mode) {
        if (mode.equalsIgnoreCase("daily")) {
            schedule = new DailyReminderSchedule(user);
        } else if (mode.equalsIgnoreCase("weekly")) {
            schedule = new WeeklyReminderSchedule(user);
        } else {
            System.out.println("Invalid schedule mode.");
            return;
        }

        schedule.generateReminders();
    }

    // Print generated reminders
    public void printReminders() {
        if (schedule == null) {
            System.out.println("Reminder schedule not set.");
            return;
        }

        System.out.println("\nReminders for " + user.getName() + ":");
        List<String> reminders = schedule.getReminders();
        for (String reminder : reminders) {
            System.out.println("- " + reminder);
        }
    }

    // Get current user
    public User getUser() {
        return user;
    }
    public ReminderSchedule getSchedule() {
        return schedule;
    }
}
