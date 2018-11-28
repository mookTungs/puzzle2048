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
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.List;
import java.util.ArrayList;
import javafx.util.Pair;
	

public class Puzzle2048 extends JFrame{
	final int size = 4;
	JPanel panel = (JPanel) getContentPane();
	JButton[][] board = new JButton[size][size];
	int[][] grid = new int[size][size];
	List<Pair <Integer, Integer>> empty = new ArrayList<Pair <Integer, Integer>>();
	List<Pair <Integer, Integer>> notEmpty = new ArrayList<Pair <Integer, Integer>>();
	
	
	public Puzzle2048(){
		initAvailableGrid();
		initUI();
	}
	
	private void initAvailableGrid(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				empty.add(new Pair<Integer, Integer>(i,j));
			}
		}
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
		buttonPanel.setLayout(new GridLayout(size,size));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray,10));
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				board[i][j] = new JButton();
				board[i][j].setEnabled(false);
				board[i][j].setBorder(BorderFactory.createLineBorder(Color.darkGray,5));
				board[i][j].setBackground(Color.gray);
				board[i][j].setOpaque(true);
				buttonPanel.add(board[i][j]);
			}
		}
		
		buttonPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int dir = e.getKeyCode();
				if(dir == KeyEvent.VK_UP){
					System.out.println("1");
					addNext();
				} else if(dir == KeyEvent.VK_DOWN){
					System.out.println("2");
					addNext();
				} else if(dir == KeyEvent.VK_LEFT){
					System.out.println("3");
					addNext();
				} else if(dir == KeyEvent.VK_RIGHT){
					System.out.println("4");
					addNext();
				}
            }
        });
		buttonPanel.setFocusable(true);
		addNext();
		
		panel.add(title, BorderLayout.NORTH);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private int getNext(){
		System.out.println("5");
		Random rand = new Random();
		int randNum = rand.nextInt(100);
		int next;
		if(randNum < 79){
			next = 2;
		}else{
			next = 4;
		}
		return next;
	}
		
	private int getRowCol(){
		Random rand = new Random();
		return rand.nextInt(empty.size());
	}
		
	private void addNext(){
		System.out.println("6");
		System.out.println("available: " + empty.size());
		if(empty.size() == 0){
			return;
		}
		int coor = getRowCol();
		Pair<Integer, Integer> p = empty.get(coor);
		grid[p.getKey()][p.getValue()] = getNext();
		empty.remove(coor);
		notEmpty.add(p);		
		updateGrid();
	}
	
	private void updateGrid(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				board[i][j].setText(Integer.toString(grid[i][j]));
			}
		}
	}
	
	public static void main(String[] args){
		Puzzle2048 game = new Puzzle2048();
	}
 }