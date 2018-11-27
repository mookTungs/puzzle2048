import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.BorderLayout;


public class Puzzle2048 extends JFrame{
	int[][] grid = new int[4][4];
	JPanel panel = (JPanel) getContentPane();
	JButton[][] board = new JButton[4][4];
	
	public Puzzle2048(){
		initUI();
	}
	
	private void initUI(){
		setTitle("2048");
		setPreferredSize(new Dimension(500, 650));
		setResizable(false);
		
		
		JLabel title = new JLabel("2048");
		title.setFont(title.getFont().deriveFont(Font.BOLD, 80f));
				
		//initialize button
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(500, 500));
		buttonPanel.setLayout(new GridLayout(4,4));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray,10));
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				board[i][j] = new JButton();
				board[i][j].setEnabled(false);
				board[i][j].setBorder(BorderFactory.createLineBorder(Color.darkGray,5));
				board[i][j].setBackground(Color.gray);
				board[i][j].setOpaque(true);
				buttonPanel.add(board[i][j]);
			}
		}
		
		panel.add(title, BorderLayout.NORTH);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private int nextNum(){
		Random rand = new Random();
		int randNum = rand.nextInt(101);
		return randNum;
	}
	
	public static void main(String[] args){
		Puzzle2048 p = new Puzzle2048(); 
		
	}
 }