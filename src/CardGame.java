import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;




public class CardGame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel cardtext;
	/**
	 * Create the panel.
	 */
	public CardGame() {
		System.out.println("CardGame panel made");
		//setLayout(null);
		this.cardtext = new JLabel("Card text goes here");
		this.cardtext.setBounds(163, 5, 123, 16);
		this.add(cardtext);
		
		JButton btnFlash = new JButton("Flash");
		btnFlash.setBounds(163, 189, 117, 29);
		add(btnFlash);
		
		JButton btnNext = new JButton(">>");
		btnNext.setBounds(292, 189, 117, 29);
		add(btnNext);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 165, 429, 12);
		add(separator);

	}
	
	static boolean playAGame(List<Card> pile) throws IOException {
		int thisind;
		Random rng = new Random();
		Card thiscard;
		Scanner sc = new Scanner(System.in);
		for (Card card: pile) {
			System.out.printf("%s \t %s \t %d \n", card.front, card.back, card.count);
			
		}
		System.in.read();
		//clearConsole();
		while (pile.size() != 0) {
			thisind = rng.nextInt(pile.size());
			thiscard = pile.get(thisind);
			
			if (thiscard.flash(sc)){
				pile.remove(thisind);
			} else {
				thiscard.count += 1;
			}
		}
		System.out.println("Game complete!");
		System.out.println("Play again? [y/n]");
		return (sc.nextLine().equals("y"));
	}

	public static void whatusedtobemain(String filename,int pilesize) throws IOException
	{
	  	//String filename = "/Users/sgold/Documents/GoTQuiz/Strongholds";
	    FileInputStream    fis;
	    BufferedReader br;
	    ArrayList<Card> deck = new ArrayList<Card>();
	    Card card;
	    String line;
	    boolean playAgain;
	    fis = new FileInputStream(filename);
	    br = new BufferedReader(new InputStreamReader(fis));
	    while ((line = br.readLine()) != null) {
		   //Deal with the line
		   card = new Card(line);
		   deck.add(card);
	    }
		    // Done with the file
		    br.close();
		    br = null;
		    fis = null;
	    //int[] deck = shuffleArray(range(n));
	//   Collections.shuffle(deck);
	//   pile = deck.subList(0, pilesize);
	  
	    playAgain = true;
	    while (playAgain) {
	    	playAgain = playAGame(FlashCards.pickAPile(pilesize,deck));
	    }
	    }
}
