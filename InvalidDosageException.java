package exceptions;

/**
 * AminMedicationManager
 * InvalidDosageException
 * 
 * Thrown when a medication is given an invalid dosage (e.g., negative or zero).
 */

public class InvalidDosageException extends Exception {
    private static final long serialVersionUID = 1L;

	public InvalidDosageException(String message) {
        super(message);
    }
}
