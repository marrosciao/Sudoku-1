package br.ufc.deti.ti0052;

import java.util.Arrays;

import javax.swing.JPanel;
//import javax.swing.JTextField;

import br.ufc.deti.ti0052.NumericTextField.BoxLength;

public class SudokuBoard extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private NumericTextField[][] grid;
	private SudokuRandom rand;
	
	public SudokuBoard() {
		setSize(225, 225);
		setLayout(null);
		
		grid = new NumericTextField[9][9];
		rand = new SudokuRandom();
		subgrid();
		arrange(rand.StandardNumbers());
	}

	public void subgrid() {
		for(int y = 0; y < 9; y++)
			for(int x = 0; x < 9; x++) {
				grid[y][x] = new NumericTextField();
				BoxLength leng = new BoxLength();
				leng.setMax(1);
				grid[y][x].setDocument(leng);
				grid[y][x].setHorizontalAlignment(NumericTextField.CENTER);
				grid[y][x].setBounds(y*getWidth()/9, x*getHeight()/9,
						getWidth()/9, getHeight()/9);
				add(grid[y][x]);
			}
	}
	
	public void arrange(int[] numbers) {
		int i = 0, x, y;
		System.out.println(Arrays.toString(numbers));
		while(i < numbers.length) {
			x = rand.Randomize(); y = rand.Randomize();
			System.out.println("(" + y + "," + x + ") " + numbers[i] + " " + i);
			if(grid[y][x].isEditable() &&
					!existsInColumn(y, numbers[i]) &&
					!existsInRow(x, numbers[i]) &&
					!existsInSubgrid(x, y, numbers[i])) {
				grid[y][x].setText(String.valueOf(numbers[i]));
				grid[y][x].setEditable(false);
				i++;
			}
		}
	}
	
	public boolean existsInRow(int x, int i) {
		for(int y = 0; y < 9; y++) {
			try {
				if(existance(x, y, i))
					return true;
			} catch (NumberFormatException e) {
				continue;
			} catch (NullPointerException e) {
				continue;
			}
		}
		return false;
	}

	public boolean existsInColumn(int y, int i) {
		for(int x = 0; x < 9; x++) {
			try {
				if(existance(x, y, i))
					return true;
			} catch (NumberFormatException e) {
				continue;
			} catch (NullPointerException e) {
				continue;
			}
		}
		return false;
	}

	public boolean existsInSubgrid(int x, int y, int i) {
		int l, c;
		if(y >= 0 && y <= 2) {
			if(x >= 0 && x <= 2) {
				for(c = 0; c <= 2; c++)
					for(l = 0; l <= 2; l++)
						try {
							if(existance(l, c, i))
								return true;
						} catch (NumberFormatException e) {
							continue;
						} catch (NullPointerException e) {
							continue;
						}
				return false;
			}
			if(x >= 3 && x <= 5) {
				for(c = 0; c <= 2; c++)
					for(l = 3; l <= 5; l++)
						try {
							if(existance(l, c, i))
								return true;
						} catch (NumberFormatException e) {
							continue;
						} catch (NullPointerException e) {
							continue;
						}
				return false;
			}
			if(x >= 6 && x <= 8) {
				for(c = 0; c <= 2; c++)
					for(l = 6; l <= 8; l++)
						try {
							if(existance(l, c, i))
								return true;
						} catch (NumberFormatException e) {
							continue;
						} catch (NullPointerException e) {
							continue;
						}
				return false;
			}
		}
		if(y >= 3 && y <= 5) {
			if(x >= 0 && x <= 2) {
				for(c = 3; c <= 5; c++)
					for(l = 0; l <= 2; l++)
						try {
							if(existance(l, c, i))
								return true;
						} catch (NumberFormatException e) {
							continue;
						} catch (NullPointerException e) {
							continue;
						}
				return false;
			}
			if(x >= 3 && x <= 5) {
				for(c = 3; c <= 5; c++)
					for(l = 3; l <= 5; l++)
						try {
							if(existance(l, c, i))
								return true;
						} catch (NumberFormatException e) {
							continue;
						} catch (NullPointerException e) {
							continue;
						}
				return false;
			}
			if(x >= 6 && x <= 8) {
				for(c = 3; c <= 5; c++)
					for(l = 6; l <= 8; l++)
						try {
							if(existance(l, c, i))
								return true;
						} catch (NumberFormatException e) {
							continue;
						} catch (NullPointerException e) {
							continue;
						}
				return false;
			}
		}
		if(y >= 6 && y <= 8) {
			if(x >= 0 && x <= 2) {
				for(c = 6; c <= 8; c++)
					for(l = 0; l <= 2; l++)
						try {
							if(existance(l, c, i))
								return true;
						} catch (NumberFormatException e) {
							continue;
						} catch (NullPointerException e) {
							continue;
						}
				return false;
			}
			if(x >= 3 && x <= 5) {
				for(c = 6; c <= 8; c++)
					for(l = 3; l <= 5; l++)
						try {
							if(existance(l, c, i))
								return true;
						} catch (NumberFormatException e) {
							continue;
						} catch (NullPointerException e) {
							continue;
						}
				return false;
			}
			if(x >= 6 && x <= 8) {
				for(c = 6; c <= 8; c++)
					for(l = 6; l <= 8; l++)
						try {
							if(existance(l, c, i))
								return true;
						} catch (NumberFormatException e) {
							continue;
						} catch (NullPointerException e) {
							continue;
						}
				return false;
			}
		}
		return false;
	}
	
	private boolean existance(int x, int y, int i) throws NumberFormatException, NullPointerException {
		int aux;
		aux = Integer.parseInt(grid[y][x].getText());
		if(aux == i)
			return true;
		return false;
	}
	
	public int[] getFocusedBox() {
		int[] coordinates = null;
		for(int y = 0; y < 9; y++)
			for(int x = 0; x < 9; x++)
				if(grid[y][x].isFocusOwner()) {
					coordinates = new int[2];
					coordinates[0] = y;
					coordinates[1] = x;
				}
		return coordinates;
	}

}
