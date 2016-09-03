package PROGRAMS;

/**
 * Created by Martin +updated by Gabby on 2/09/2016.
 */

/**
 * Base program class. This should be extended to add specific functionality.
 */
public abstract class Program {
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
	
	public String getProgramType() {
		return this.programType;
	}

	public boolean getStatus() {
		return isActive;
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
		System.out.println("Program ID:\t\t  " + getID());
		System.out.println("Program Title:\t\t  " + getTitle());
		System.out.println("Program Type:\t  " + getProgramType());
		System.out.println("Status(active/inactive)\t:  " + getStatus());
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


