import java.io.IOException;
import java.util.Scanner;

public class Card {
   	public String front;
	public String back;
	int count;

    public Card(String contents) {
    //System.out.println(contents);
	String[] ary = contents.split(":");
	if (ary.length != 2) {
		System.out.println("Contents are malformed!");
		System.out.println(ary.length);
	}
	this.front = ary[0];
	this.back = ary[1];
	this.count = 0;
    }

    boolean flash(Scanner sc) throws IOException {
    	boolean removeCard;
    	System.out.println(this.front);
    	System.in.read();
    	//clearConsole();
    	System.out.println(this.back);
    	System.out.println("Enter 'y' to remove this card");
    	String nl = sc.nextLine();
    	//System.out.print(nl);
    	removeCard = (nl.equals("y"));
    	return removeCard;
    }
}
