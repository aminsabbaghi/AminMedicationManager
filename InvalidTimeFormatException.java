package exceptions;

/**
 * AminMedicationManager
 * InvalidTimeFormatException
 * 
 * Thrown when the administration time is in an incorrect format.
 */

public class InvalidTimeFormatException extends Exception {
    private static final long serialVersionUID = 1L;

	public InvalidTimeFormatException(String message) {
        super(message);
    }
}
