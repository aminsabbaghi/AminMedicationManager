package schedule;

import models.Medication;
import models.User;
import java.util.ArrayList;
import java.util.List;

/**
 * AminMedicationManager
 * DailyReminderSchedule
 * 
 * Generates daily reminders for all medications assigned to the user.
 */

public class DailyReminderSchedule extends ReminderSchedule {

    public DailyReminderSchedule(User user) {
        super(user); // call the superclass constructor
        reminders = new ArrayList<>();
    }

    @Override
    public void generateReminders() {
        reminders.clear(); // clear any existing ones

        for (Medication med : user.getMedications()) {
            String reminder = "Take " + med.getName() + " (" + med.getDosageMg() + "mg) at " + med.getAdministrationTime() +
                              " - Frequency: " + med.getFrequency();
            reminders.add(reminder);
        }
    }
}
