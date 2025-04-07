package schedule;

import models.User;
import java.util.List;

/**
 * AminMedicationManager
 * ReminderSchedule (Abstract Class)
 * 
 * Base class for different reminder scheduling strategies.
 * Subclasses will implement how reminders are generated (daily, weekly, etc.).
 */

public abstract class ReminderSchedule {
    protected User user;
    protected List<String> reminders;

    public ReminderSchedule(User user) {
        this.user = user;
    }

    // Abstract method to generate reminders
    public abstract void generateReminders();

    public List<String> getReminders() {
        return reminders;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Reminders for " + user.getName() + ": " + reminders;
    }
}
