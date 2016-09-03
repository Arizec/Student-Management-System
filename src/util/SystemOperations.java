package util;

/**
 * Interface to keep track of system operations. This should be implemented both
 * by student and program entities
 */
public interface SystemOperations {
	/**
	 * Sets the active status for an item on true.
	 */
	public boolean activate();

	/**
	 * Sets the active status for an item on false.
	 */
	public boolean deactivate();
}
