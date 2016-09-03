import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import model.Book;
import model.PremiumMember;
import model.StandardMember;
import model.Video;
import model.base.Holding;
import model.base.Member;
import util.DateTime;

/**
 * Helper class to achieve persistence of the data. Contains methods to save
 * holding and member and load arrays from file
 */
public class FileManager {

	// Constants for file names
	private static final String HOLDINGS_FILE = "holdings.txt";
	private static final String MEMBERS_FILE = "members.txt";

	/**
	 * Save to the holdings file the array of holdings passed as parameter
	 * 
	 * @param holdings
	 */
	public static void saveHoldingsToFile(Holding[] holdings) {
		PrintWriter writer;
		try {
			// Initialize the writer object
			writer = new PrintWriter(HOLDINGS_FILE, "UTF-8");
			// Pass through the holdings array
			for (Holding h : holdings) {
				// Write each holding to file
				writer.println(h);
			}
			// Close the writer upon completion
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Save the members array passed as parameter to file. For each member we
	 * also add the current holdings
	 * 
	 * @param members
	 */
	public static void saveMembersToFile(Member[] members) {
		PrintWriter writer;
		try {
			// Initialize the writer object
			writer = new PrintWriter(MEMBERS_FILE, "UTF-8");
			for (Member m : members) {
				// First print information about the member
				writer.print(m);
				// If it has some holdings to print
				if (m.getCurrentHoldings().length > 0) {
					// Pass through the current holdings for the member
					for (Holding h : m.getCurrentHoldings()) {
						// Use a different separator and print holding
						// information
						writer.print(";");
						writer.print(h);
					}
				}
				writer.println();
			}
			// Close the writer upon completion
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method used to retrieve holdings from file. Will create an array of
	 * Holdings after parsing the input file
	 */
	public static Holding[] retrieveHoldings() {
		// Create an empty array
		Holding[] holdings = new Holding[] {};
		// First check if the file exists
		if (new File(HOLDINGS_FILE).exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(HOLDINGS_FILE))) {
				String line;
				while ((line = br.readLine()) != null) {
					// Process each line from the file
					// Split the line to get the holding details array
					String[] holdingDetails = line.split(":");
					Holding h;
					// Check the first position from the id to see what type of
					// Holding we need to create
					if (holdingDetails[0].charAt(0) == 'b') {
						// Create a book with the rest of the id and the title
						// on position 1
						h = new Book(holdingDetails[0].substring(1), holdingDetails[1]);
					} else {
						// Create a video with the rest of the id and the title
						// on position 1
						h = new Video(holdingDetails[0].substring(1), holdingDetails[1],
								Integer.parseInt(holdingDetails[2]));
					}
					// If the holding is not active, deactivate it
					if (!Boolean.valueOf(holdingDetails[4])) {
						h.deactivate();
					}
					// If the holding is not free it means we need to set the
					// borrow date accordingly
					if (!holdingDetails[5].equals("free")) {
						// Parse the date string
						String[] dateDetails = holdingDetails[5].split("-");
						// Create a new date time object given the string
						// retrieved from file and the appropriate constructor
						DateTime d = new DateTime(Integer.parseInt(dateDetails[2]), Integer.parseInt(dateDetails[1]),
								Integer.parseInt(dateDetails[0]));
						h.setBorrowDate(d);
					}
					// Add the holding to the collection
					ArrayList<Holding> temp = new ArrayList<Holding>(Arrays.asList(holdings));
					temp.add(h);
					holdings = new Holding[temp.size()];
					temp.toArray(holdings);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return holdings;
	}

	/**
	 * Retrieve array of members from file. Similar to the method to retrieve
	 * holdings
	 * 
	 * @return
	 */
	public static Member[] retrieveMembers() {
		Member[] members = new Member[] {};
		if (new File(MEMBERS_FILE).exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(MEMBERS_FILE))) {
				String line;
				while ((line = br.readLine()) != null) {
					// first check if we have any holdings to the current member
					String[] lineDetails = line.split(";");
					if (lineDetails.length > 0) {
						// Parse the line further to retrieve member details
						String[] memberDetails = lineDetails[0].split(":");
						Member m;
						// Create the according implementation of member
						if (memberDetails[0].charAt(0) == 's') {
							m = new StandardMember(memberDetails[0].substring(1), memberDetails[1]);
						} else {
							m = new PremiumMember(memberDetails[0].substring(1), memberDetails[1]);
						}
						// Check if we need to deactivate the member status
						if (!Boolean.valueOf(memberDetails[3])) {
							m.deactivate();
						}
						// Set the credit
						m.setCredit(Integer.parseInt(memberDetails[2]));

						//Parse the holdings
						Holding[] holdings = new Holding[] {};
						for (int i = 1; i < lineDetails.length; i++) {
							String[] holdingDetails = lineDetails[i].split(":");
							Holding h;
							if (holdingDetails[0].charAt(0) == 'b') {
								h = new Book(holdingDetails[0].substring(1), holdingDetails[1]);
							} else {
								h = new Video(holdingDetails[0].substring(1), holdingDetails[1],
										Integer.parseInt(holdingDetails[2]));
							}
							if (!Boolean.valueOf(holdingDetails[4])) {
								h.deactivate();
							}
							if (!holdingDetails[5].equals("free")) {
								String[] dateDetails = holdingDetails[5].split("-");
								DateTime d = new DateTime(Integer.parseInt(dateDetails[2]),
										Integer.parseInt(dateDetails[1]), Integer.parseInt(dateDetails[0]));
								h.setBorrowDate(d);
							}
							ArrayList<Holding> temp = new ArrayList<Holding>(Arrays.asList(holdings));
							temp.add(h);
							holdings = new Holding[temp.size()];
							temp.toArray(holdings);
						}
						// Set the holdings to the current holdings of the
						// member
						m.setCurrentHoldings(holdings);
						// Add the member to the collection
						ArrayList<Member> temp = new ArrayList<Member>(Arrays.asList(members));
						temp.add(m);
						members = new Member[temp.size()];
						temp.toArray(members);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return members;
	}

}
