import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GameStart extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField pileSizeField;
	private JTextField CardSourceField;
	private JFileChooser fc;
	public GameStart(final FlashCards fcrd) {
		setOpaque(false);
		fc = new JFileChooser();
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pile Size:");
		lblNewLabel.setBounds(128, 11, 55, 16);
		add(lblNewLabel);
		
		pileSizeField = new JTextField();
		pileSizeField.setBounds(188, 5, 134, 28);
		pileSizeField.setText("5");
		add(pileSizeField);
		pileSizeField.setColumns(10);
		
		JLabel lblDeckFile = new JLabel("Deck File:");
		lblDeckFile.setBounds(121, 39, 62, 16);
		add(lblDeckFile);
		
		CardSourceField = new JTextField();
		CardSourceField.setBounds(188, 33, 134, 28);
		add(CardSourceField);
		CardSourceField.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse...");
		btnBrowse.setBounds(327, 34, 117, 29);
		add(btnBrowse);
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(GameStart.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                String filename = fc.getSelectedFile().getAbsolutePath();
	                CardSourceField.setText(filename); 
				}
			}
		});
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(188, 67, 117, 29);
		add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fcrd.startGame(CardSourceField.getText(),Integer.parseInt(pileSizeField.getText()));

			}
		});
	}
}
