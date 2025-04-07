package schedule;

import models.Medication;
import models.User;
import java.util.ArrayList;
import java.util.List;

/**
 * AminMedicationManager
 * WeeklyReminderSchedule
 * 
 * Assigns medications across the days of a simulated week.
 * Useful for organizing meds when the user takes multiple medications.
 */

public class WeeklyReminderSchedule extends ReminderSchedule {

    private static final String[] DAYS = {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };

    public WeeklyReminderSchedule(User user) {
        super(user);
        reminders = new ArrayList<>();
    }

    @Override
    public void generateReminders() {
        reminders.clear();
        List<Medication> meds = user.getMedications();

        for (int i = 0; i < meds.size(); i++) {
            Medication med = meds.get(i);
            String day = DAYS[i % DAYS.length]; // rotate through the week
            String reminder = day + ": Take " + med.getName() + " (" + med.getDosageMg() + "mg) at " +
                              med.getAdministrationTime() + " - Frequency: " + med.getFrequency();
            reminders.add(reminder);
        }
    }
}
