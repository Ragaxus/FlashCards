import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class FlashCards extends JFrame {
	private static final long serialVersionUID = 1L;
	JPanel currentPanel;

	
	public FlashCards() {
		setTitle("FlashCards v0.2\n");
		getContentPane().setLayout(null);
		getContentPane().setBounds(0, 6, 444, 266);
		
		JPanel gspanel = new GameStart(this);
		gspanel.setBounds(0, 6, 444, 266);
		getContentPane().add(gspanel);
		this.currentPanel = gspanel;
		pack();
		setVisible(true);
	}
    /**
	 * 
	 */
	public void startGame(String filename, int pilesize) {
		System.out.println("Game starting!");
		ArrayList<Card> deck = null;
		try {
			deck = generateDeck(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Card> firstpile = pickAPile(pilesize, deck); //TODO Unused currently
//		this.remove(currentPanel);
		this.getContentPane().removeAll();
		JPanel game = new CardGame();
		this.setContentPane(game);
		//add(game);
		currentPanel = game;
		pack();
		setVisible(true);
	}

    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            return;
        }
    }
	


public int[] range(int n) {
        int[] a = new int[n];
	    for (int i = 0; i < n; ++i) {
		        a[i] = i;
			    }
	        return a;
}
 
// Implementing FisherâYates shuffle
static void shuffleArray(int[] ar)
      {
	  Random rnd = new Random();
	      for (int i = ar.length - 1; i > 0; i--)
		  {
			int index = rnd.nextInt(i + 1);
			      // Simple swap
				    int a = ar[index];
					  ar[index] = ar[i];
						ar[i] = a;
						    }
						      }

public static ArrayList<Card> generateDeck(String filename) throws IOException
{
  	//String filename = "/Users/sgold/Documents/GoTQuiz/Strongholds";
    FileInputStream    fis;
    BufferedReader br;
    ArrayList<Card> deck = new ArrayList<Card>();
    Card card;
    String line;
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
  
	return deck;
    }

static List<Card> pickAPile(int pilesize, ArrayList<Card> deck) {
	List<Card> pile = new ArrayList<Card>();
	double[] factors = new double[deck.size()];
	int i = 0;
	double totfac = 0;
	Random rng = new Random();
	for (Card card: deck) {
		factors[i] = Math.exp(card.count);
		totfac += factors[i];
		i++;
	}
	for (i = 0; i < factors.length; i++) {
		factors[i] /= totfac;
	}
	int ind;
	double shot;
	for (i = 0; i < pilesize; i++) {
		ind = -1;
		shot = rng.nextDouble();
		while (shot > 0) {
			ind += 1;
			shot -= factors[ind];
		}
		pile.add(deck.get(ind));
	}
	return pile;
}
private static void createAndShowFlashCardsGUI() {
    //Create and set up the window.
    JFrame frame = new FlashCards();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Display the window.
//    frame.pack();
//    frame.setVisible(true);
}
public static void main(String[] args) {
    //Schedule a job for the event dispatch thread:
    //creating and showing this application's GUI.
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            //Turn off metal's use of bold fonts
            UIManager.put("swing.boldMetal", Boolean.FALSE); 
            createAndShowFlashCardsGUI();
        }
    });
}
    }
