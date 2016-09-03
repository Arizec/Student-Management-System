package model;

import util.DateTime;
import model.base.Program;

/**
 * Course class is an implementation of Program
 */
public class Book extends Holding {

	// Default values specific to course object
//	private static final int DEFAULT_LOAN_FEE = 10;
//	private static final int MAX_LOAN_PERIOD = 28;
//	private static final int FIXED_DAILY_RATE = 2;

	/**
	 * Custom constructor to set the holding id and the defaults for loan fee
	 * and loan period
	 * 
	 * @param programId
	 * @param programTitle
	 */
	public Book(String programId, String programTitle) {
		// First call the constructor of the super class
		super(programId, programTitle);
		// Generate the id
		this.programId = "b" + programId;
		// Setup the default values
		//this.loanFee = DEFAULT_LOAN_FEE;
		//this.maxLoanPeriod = MAX_LOAN_PERIOD;
	}

	// Overridden methods below
	@Override
	public int getDefaultLoanFee() {
		return DEFAULT_LOAN_FEE;
	}

	@Override
	public int getMaxLoanPeriod() {
		return MAX_LOAN_PERIOD;
	}

	@Override
	public int calculateLateFee(DateTime dateReturned) {
		return getNumberOfLateDays(dateReturned) * FIXED_DAILY_RATE;
	}
}
