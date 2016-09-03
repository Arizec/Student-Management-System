//put in a package called model.base, unsure how to create package

package model.base;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Base Student class. Should be extended to add extra functionality.
 */
public abstract class Student /*implements something */ {

	protected String studentId;
	protected String fullName;
	protected int credit;
	protected String program;
	protected boolean isActive;
	/*protected Course[] currentCourse; */

	/**
	 * Base constructor to set the student name, initialize the array of current
	 * Program/courses and set the credit. The id and the actual credit value will be
	 * set by child classes. Upon creation the student is by default active.
	 * 
	 * @param studentID
	 * @param fullName
	 * @param credit
	 */
	public Member(String memberID, String fullName, int credit) {
		this.fullName = fullName;
		this.program = program;
		this.currentCourse = new Course[] {};
		this.credit = credit;
		this.isActive = true;
	}

	public String getID() {
		return this.studentId;
	}

	public String getFullName() {
		return this.fullName;
	}

	public int calculateRemainingCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	/**
	 * Sets the credit back to the initial max value
	 */
	public void resetCredit() {
		this.credit = getMaxCredit();
	}

	public Holding[] getCurrentHoldings() {
		return this.currentHoldings;
	}

	public void setCurrentHoldings(Holding[] holdings) {
		this.currentHoldings = holdings;
	}

	/**
	 * Upon borrowing a holding this method will update the credit for the
	 * member. If the loan will cause the credit to overdraw the credit must be
	 * reset as per requirements.
	 * 
	 * @param loanFee
	 * @return
	 */
	public boolean updateRemainingCredit(int loanFee) {
		if (checkAllowedCreditOverdraw(loanFee)) {
			credit -= loanFee;
			return true;
		} else {
			resetCredit();
			return false;
		}
	}

	/**
	 * If the current loan will cause the credit to become negative it means we
	 * have an overdraw and return true. Return false otherwise
	 * 
	 * @param loanFee
	 * @return
	 */
	public boolean checkAllowedCreditOverdraw(int loanFee) {
		return credit - loanFee > 0;
	}

	/**
	 * Method that adds the holding passed as parameter to the list of the
	 * current holdings for the member or throws the exception if the user does
	 * not have sufficient credit to borrow the holding
	 * 
	 * @param holding
	 *            Holding to add
	 * @return True if the operation succeeded or false otherwise
	 * @throws InsufficientCreditException
	 *             If the member doesn't have sufficient credit to borrow the
	 *             holding
	 */
	public boolean borrowHolding(Holding holding) throws InsufficientCreditException {
		if (calculateRemainingCredit() < holding.getDefaultLoanFee()) {
			throw new InsufficientCreditException("Insufficient credit to borrow", calculateRemainingCredit());
		}
		if (isActive && getStatus() && calculateRemainingCredit() > holding.getDefaultLoanFee()) {
			updateRemainingCredit(holding.getDefaultLoanFee());
			appendHolding(holding);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Prints information about the member to the console
	 */
	public void print() {
		System.out.println("ID:\t\t  " + memberId);
		System.out.println("Full Name:\t  " + fullName);
		System.out.println("Max Credit:\t  " + getMaxCredit());
		System.out.println("Available Credit: " + calculateRemainingCredit());
	}

	/**
	 * String representation of the current member. Very important are the ":"
	 * characters that act as tokens to be able to parse the file to retrieve
	 * the members
	 */
	@Override
	public String toString() {
		return memberId + ":" + fullName + ":" + calculateRemainingCredit() + ":" + isActive;
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
	 * Search through the current holdings for the member given the holding Id
	 * 
	 * @param holdingId
	 * @return The Holding object if it was found between the current holdings
	 *         of the member, null otherwise
	 */
	public Holding searchThroughCurrentHoldings(String holdingId) {
		for (Holding h : currentHoldings) {
			if (holdingId.equals(h.getID())) {
				return h;
			}
		}
		return null;
	}

	/**
	 * Will add the Holding passed as parameter to the array of holdings for the
	 * current member
	 * 
	 * @param holding
	 */
	private void appendHolding(Holding holding) {
		ArrayList<Holding> temp = new ArrayList<Holding>(Arrays.asList(currentHoldings));
		temp.add(holding);
		currentHoldings = new Holding[temp.size()];
		temp.toArray(currentHoldings);
	}

	/**
	 * Will remove the holding passed as parameter from the array of holdings
	 * for the current member
	 * 
	 * @param holding
	 */
	protected void removeHolding(Holding holding) {
		// Convert array to array list
		ArrayList<Holding> a = new ArrayList<>(Arrays.asList(currentHoldings));
		// Safely call remove on this list
		a.remove(holding);
		// Recreate the array given the new size
		currentHoldings = new Holding[a.size()];
		a.toArray(currentHoldings);
	}

	/**
	 * Should be implemented in child classes. Will return the status of the
	 * current member, this will reflect if a user can or cannot make another
	 * borrow
	 * 
	 * @return
	 */
	public abstract boolean getStatus();

	/**
	 * Should be implemented in child classes. Will return default max credit
	 * values based on the member type
	 * 
	 * @return
	 */
	public abstract int getMaxCredit();

	/**
	 * This should be implemented in child classes. Used to return a holding
	 * from the member at a given return date
	 * 
	 * @param holding
	 * @param returnDate
	 * @return true if the operation succeeded or false otherwise
	 */
	public abstract boolean returnHolding(Holding holding, DateTime returnDate);
}
