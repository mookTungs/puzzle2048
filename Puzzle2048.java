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
				board[i][j].setFont(title.getFont().deriveFont(Font.BOLD, 40f));
				buttonPanel.add(board[i][j]);
			}
		}
		
		buttonPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int dir = e.getKeyCode();
				if(dir == KeyEvent.VK_UP){
					moveUp();
				} else if(dir == KeyEvent.VK_DOWN){
					moveDown();
				} else if(dir == KeyEvent.VK_LEFT){
					moveLeft();
				} else if(dir == KeyEvent.VK_RIGHT){
					moveRight();
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
	
	private void moveUp(){
		boolean move = false;
		//merge all available block
		for(int c = 0; c < size; c++){
			int i = 0;
			int j = 0;
			while(i < size-1){
				j = i + 1;
				if(grid[i][c] != 0){
					while(j < size){
						if(grid[j][c] == 0){
							j++;
							continue;
						}
						if(grid[i][c] == grid[j][c]){
							grid[i][c] = grid[i][c]+grid[j][c];
							grid[j][c] = 0;
							empty.add(new Pair<Integer,Integer>(j,c));
							move = true;
							break;
						}
						if(grid[i][c] != grid[j][c]){
							break;
						}
						j++;
					}
				}
				i++;
			}
			
			//move up all available blocks to empty spot 
			for(int r = 1; r < size; r++){
				if(grid[r][c] != 0){
					int x = -1;
					int y = 0;
					while(y < size){
						if(grid[y][c] == 0){
							x = y;
							break;
						}
						y++;
					}
					if(x != -1 && x < r){
						grid[x][c] = grid[r][c];
						grid[r][c] = 0;
						empty.remove(new Pair<Integer,Integer>(x,c));
						empty.add(new Pair<Integer,Integer>(r,c));
						move = true;
					}
				}
			}
		}
		
		if(move){
			addNext();
		}else if(empty.size() == 0){
			System.out.	println("GAME OVER");
		}
	}
	
	private void moveDown(){
		boolean move = false;
		//merge all available block
		for(int c = 0; c < size; c++){
			int i = size-1;
			int j = 0;
			while(i > 0){
				j = i - 1;
				if(grid[i][c] != 0){
					while(j >= 0){
						if(grid[j][c] == 0){
							j--;
							continue;
						}
						if(grid[i][c] == grid[j][c]){
							grid[i][c] = grid[i][c]+grid[j][c];
							grid[j][c] = 0;
							empty.add(new Pair<Integer,Integer>(j,c));
							move = true;
							break;
						}
						if(grid[i][c] != grid[j][c]){
							break;
						}
						j--;
					}
				}
				i--;
			}
		
			//move down all available blocks to empty spot 
			for(int r = size-2; r >= 0; r--){
				if(grid[r][c] != 0){
					int x = -1;
					int y = size-1;
					while(y >= 0){
						if(grid[y][c] == 0){
							x = y;
							break;
						}
						y--;
					}
					if(x != -1 && x > r){
						grid[x][c] = grid[r][c];
						grid[r][c] = 0;
						empty.remove(new Pair<Integer,Integer>(x,c));
						empty.add(new Pair<Integer,Integer>(r,c));
						move = true;
					}
				}
			}
		}
		
		if(move){
			addNext();
		}else if(empty.size() == 0){
			System.out.	println("GAME OVER");
		}
	}
	
	private void moveLeft(){
		boolean move = false;
		//merge all available block
		for(int c = 0; c < size; c++){
			int i = 0;
			int j = 0;
			while(i < size-1){
				j = i + 1;
				if(grid[c][i] != 0){
					while(j < size){
						if(grid[c][j] == 0){
							j++;
							continue;
						}
						if(grid[c][i] == grid[c][j]){
							grid[c][i] = grid[c][i]+grid[c][j];
							grid[c][j] = 0;
							empty.add(new Pair<Integer,Integer>(c,j));
							move = true;
							break;
						}
						if(grid[c][i] != grid[c][j]){
							break;
						}
						j++;
					}
				}
				i++;
			}
			
			//move left all available blocks to empty spot 
			for(int r = 1; r < size; r++){
				if(grid[c][r] != 0){
					int x = -1;
					int y = 0;
					while(y < size){
						if(grid[c][y] == 0){
							x = y;
							break;
						}
						y++;
					}
					if(x != -1 && x < r){
						grid[c][x] = grid[c][r];
						grid[c][r] = 0;
						empty.remove(new Pair<Integer,Integer>(c,x));
						empty.add(new Pair<Integer,Integer>(c,r));
						move = true;
					}
				}
			}
		}
		
		if(move){
			addNext();
		}else if(empty.size() == 0){
			System.out.	println("GAME OVER");
		}
	}
	
	private void moveRight(){
		boolean move = false;
		//merge all available block
		for(int c = 0; c < size; c++){
			int i = size-1;
			int j = 0;
			while(i > 0){
				j = i - 1;
				if(grid[c][j] != 0){
					while(j >= 0){
						if(grid[c][j] == 0){
							j--;
							continue;
						}
						if(grid[c][i] == grid[c][j]){
							grid[c][i] = grid[c][i]+grid[c][j];
							grid[c][j] = 0;
							empty.add(new Pair<Integer,Integer>(c,j));
							move = true;
							break;
						}
						if(grid[c][i] != grid[c][j]){
							break;
						}
						j--;
					}
				}
				i--;
			}
		
			//move right all available blocks to empty spot 
			for(int r = size-2; r >= 0; r--){
				if(grid[c][r] != 0){
					int x = -1;
					int y = size-1;
					while(y >= 0){
						if(grid[c][y] == 0){
							x = y;
							break;
						}
						y--;
					}
					if(x != -1 && x > r){
						grid[c][x] = grid[c][r];
						grid[c][r] = 0;
						empty.remove(new Pair<Integer,Integer>(c,x));
						empty.add(new Pair<Integer,Integer>(c,r));
						move = true;
					}
				}
			}
		}
		
		if(move){
			addNext();
		}else if(empty.size() == 0){
			System.out.	println("GAME OVER");
		}
	}
		
	private int getRowCol(){
		Random rand = new Random();
		return rand.nextInt(empty.size());
	}
		
	private void addNext(){
		if(empty.size() == 0){
			return;
		}
		int coor = getRowCol();
		Pair<Integer, Integer> p = empty.get(coor);
		grid[p.getKey()][p.getValue()] = getNext();
		empty.remove(coor);
		updateGrid();
	}
	
	private void updateGrid(){
		int x;
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				x = grid[i][j];
				if(x == 0){
					board[i][j].setText("");
				}else {
					board[i][j].setText(Integer.toString(x));
				}
			}
		}
	}
	
	public static void main(String[] args){
		Puzzle2048 game = new Puzzle2048();
	}
 }