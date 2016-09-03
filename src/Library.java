import java.util.ArrayList;
import java.util.Arrays;

import model.Book;
import model.PremiumMember;
import model.StandardMember;
import model.Video;
import model.base.Holding;
import model.base.Member;

/**
 * Helper method to hold collections of holdings and members
 */
public class Library {

	// Max allowed size for the holdings and members collections
	private static final int MAX_ELEMENTS = 15;

	private Holding[] holdings;

	private Member[] members;

	/**
	 * Default constructor that needs to handle a couple of things.
	 */
	public Library() {
		// First it needs to initialize the holdings and members arrays
		this.holdings = new Holding[] {};
		this.members = new Member[] {};
		// Check if the holdings array retrieved from file is not empty. If so,
		// populate the collection with the array retrieved from file
		if (FileManager.retrieveHoldings().length != 0) {
			setHoldings(FileManager.retrieveHoldings());
		} else {
			// Otherwise insert the default holdings
			createHoldings();
		}
		// Check if the members array retrieved from file is not empty. If so,
		// populate the collection with the array retrieved from file
		if (FileManager.retrieveMembers().length != 0) {
			setMembers(FileManager.retrieveMembers());
		} else {
			// Otherwise insert the default members
			createMembers();
		}
	}

	public Holding[] getHoldings() {
		return holdings;
	}

	public void setHoldings(Holding[] holdings) {
		this.holdings = holdings;
	}

	public Member[] getMembers() {
		return members;
	}

	public void setMembers(Member[] members) {
		this.members = members;
	}

	/**
	 * Search through the available holdings given a holding Id.
	 * 
	 * @param id
	 * @return the Holding object corresponding to that id or null otherwise
	 */
	public Holding searchHoldingById(String id) {
		for (int i = 0; i < holdings.length; i++) {
			if (id.equals(holdings[i].getID())) {
				return holdings[i];
			}
		}
		// If we get to this point it means the holding was not found. Return
		// null in this case
		return null;
	}

	/**
	 * Search through the available members from the library given the member
	 * Id.
	 * 
	 * @param id
	 * @return the Member object corresponding to that id or null otherwise
	 */
	public Member searchMemberById(String id) {
		for (int i = 0; i < members.length; i++) {
			if (id.equals(members[i].getID())) {
				return members[i];
			}
		}
		return null;
	}

	/**
	 * Checks if the holdings collection has enough space to add the holding
	 * passed as parameter
	 * 
	 * @param h
	 *            Holding to add
	 */
	public void addHolding(Holding h) {
		// If array is full display error
		if (holdings.length >= MAX_ELEMENTS) {
			System.out.println("You cannot perform this operation. Holdings collection is full");
		} else {
			// Otherwise append the holding to the holdings array
			appendHolding(h);
		}
	}

	/**
	 * Checks if the members collection has enough space to add the member
	 * passed as parameter
	 * 
	 * @param m
	 *            Member to add
	 */
	public void addMember(Member m) {
		if (members.length >= MAX_ELEMENTS) {
			System.out.println("You cannot perform this operation. Holdings collection is full");
		} else {
			appendMember(m);
		}
	}

	// Operations to add and remove holdings and member in the following methods

	private void appendHolding(Holding holding) {
		ArrayList<Holding> temp = new ArrayList<Holding>(Arrays.asList(holdings));
		temp.add(holding);
		holdings = new Holding[temp.size()];
		temp.toArray(holdings);
	}

	private void appendMember(Member member) {
		ArrayList<Member> temp = new ArrayList<Member>(Arrays.asList(members));
		temp.add(member);
		members = new Member[temp.size()];
		temp.toArray(members);
	}

	protected void removeHolding(Holding holding) {
		ArrayList<Holding> a = new ArrayList<>(Arrays.asList(holdings));
		a.remove(holding);
		holdings = new Holding[a.size()];
		a.toArray(holdings);
	}

	protected void removeMember(Member member) {
		ArrayList<Member> a = new ArrayList<>(Arrays.asList(members));
		a.remove(member);
		members = new Member[a.size()];
		a.toArray(members);
	}

	/**
	 * Used to print information about the current holdings in the library
	 */
	public void printAllHoldings() {
		for (int i = 0; i < holdings.length; i++) {
			if (holdings[i] != null) {
				holdings[i].print();
				System.out.println();
			}
		}
	}

	/**
	 * Used to print information about the current members in the library
	 */
	public void printAllMembers() {
		for (int i = 0; i < members.length; i++) {
			if (members[i] != null) {
				members[i].print();
				System.out.println();
			}
		}
	}

	/**
	 * Helper method to add some holdings if the file was not created yet.
	 */
	private void createHoldings() {
		Book b1 = new Book("000001", "Intro to Java");
		appendHolding(b1);
		Book b2 = new Book("000002", "Learning UML");
		appendHolding(b2);
		Book b3 = new Book("000003", "Design Patterns");
		appendHolding(b3);
		Book b4 = new Book("000004", "Advanced Java");
		appendHolding(b4);
		Video v1 = new Video("000001", "Java 1", 4);
		appendHolding(v1);
		Video v2 = new Video("000002", "Java 2", 6);
		appendHolding(v2);
		Video v3 = new Video("000003", "UML 1", 6);
		appendHolding(v3);
		Video v4 = new Video("000004", "UML 2", 4);
		appendHolding(v4);
	}

	/**
	 * Helper method to add some members if the file was not created yet.
	 */
	private void createMembers() {
		StandardMember s1 = new StandardMember("000001", "Joe Bloggs");
		appendMember(s1);
		StandardMember s2 = new StandardMember("000002", "Jane Smith");
		appendMember(s2);
		PremiumMember p1 = new PremiumMember("000001", "Fred Bloggs");
		appendMember(p1);
		PremiumMember p2 = new PremiumMember("000002", "Fred Smith");
		appendMember(p2);
	}

}
