package model.base; //put in package called model.base

// import exceptions //
import util.SystemOperations;

/**
 * Base holding class. This should be extended to add specific functionality.
 */
public abstract class Program implements SystemOperations {
	protected String programId;
	protected String programTitle;
	protected String programType; //(i.e masters/bachelors/phd)
	protected boolean isActive;

	/**
	 * Base constructor to set the title. The id will be set in child classes.
	 * By default the holding is active upon object creation
	 * 
	 * @param holdingId
	 * @param title
	 */
	public Holding(String programId, String programTitle) {
		this.programTitle = programTitle;
		isActive = true;
	}

	public String getID() {
		return programId;
	}

	public String getTitle() {
		return this.programTitle;
	}

	public boolean getStatus() {
		return isActive;
	}

	/**
	 * Method to update the holding status if a member borrowed it.
	 * 
	 * @return
	 * @throws ItemInactiveException
	 *             of the holding is inactive
	 * @throws OnLoanException
	 *             if the holding is already on loan
	 */
	public boolean borrowHolding() throws ItemInactiveException, OnLoanException {
		if (!isActive) {
			throw new ItemInactiveException(title + " is inactive!");
		} else if (isOnLoan()) {
			throw new OnLoanException(title + " is already on loan!");
		} else {
			// Update the borrow date
			this.borrowDate = new DateTime();
			return true;
		}
	}

	/**
	 * Item is on loan if the borrowDate is not null
	 */
	public boolean isOnLoan() {
		return this.borrowDate != null;
	}

	public DateTime getBorrowDate() {
		return this.borrowDate;
	}

	public void setBorrowDate(DateTime d) {
		this.borrowDate = d;
	}

	/**
	 * Method to update the status of the holding after a member returns it.
	 * 
	 * @param dateReturned
	 * @return true if the operation succeeded or false otherwise
	 * @throws ItemInactiveException
	 *             if the item is inactive
	 * @throws OnLoanException
	 *             if the item is not on loan
	 */
	public boolean returnHolding(DateTime dateReturned) throws ItemInactiveException, OnLoanException {
		// First check if the holding is inactive
		if (!isActive) {
			throw new ItemInactiveException(title + " is inactive!");
		} else if (!isOnLoan()) { // Check if it's not already on loan
			throw new OnLoanException(title + " is not on loan!");
		} else if (DateTime.diffDays(dateReturned, getBorrowDate()) >= 0) {
			// Update the borrow date to null as the holding is returned now
			this.borrowDate = null;
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param dateReturned
	 * @return the difference between dateReturned and the borrowDate for the
	 *         holding if the days difference is bigger than max loan period, 0
	 *         otherwise
	 */
	public int getNumberOfLateDays(DateTime dateReturned) {
		// First compute the time difference
		int diffDays = DateTime.diffDays(dateReturned, borrowDate);
		// Make the check and return the according value
		return diffDays > getMaxLoanPeriod() ? (diffDays - getMaxLoanPeriod()) : 0;
	}

	/**
	 * Overridden method, the string representation of this object will be used
	 * for persisting the objects in files. Very important are the ":"
	 * characters which are tokens separating fields, used to retrieve the
	 * objects from file
	 */
	@Override
	public String toString() {
		return holdingId + ":" + title + ":" + loanFee + ":" + maxLoanPeriod + ":" + isActive + ":"
				+ (borrowDate != null ? borrowDate : "free");
	}

	/**
	 * Print information about the current holding to the console
	 */
	public void print() {
		System.out.println("ID:\t\t  " + getID());
		System.out.println("Title:\t\t  " + getTitle());
		System.out.println("Loan Fee:\t  " + getDefaultLoanFee());
		System.out.println("Max Loan Period:  " + getMaxLoanPeriod());
	}

	@Override
	public boolean activate() {
		this.isActive = true;
		return isActive;
	}

	@Override
	public boolean deactivate() {
		this.isActive = false;
		return isActive;
	}

	/**
	 * Should be implemented in child classes
	 * 
	 * @return the default fee for borrowing the holding
	 */
	public abstract int getDefaultLoanFee();

	/**
	 * Should be implemented in child classes
	 * 
	 * @return the max number of days to return the holding
	 */
	public abstract int getMaxLoanPeriod();

	/**
	 * This should be implemented in child classes as it should have different
	 * behavior. Computes the fee the user has to pay to return this holding
	 * 
	 * @param dateReturned
	 * @return
	 */
	public abstract int calculateLateFee(DateTime dateReturned);
}
