package queen_wars;

/* Written by Zeus Polanco Salgado, Feb 11 19
 * 
 * 	It is important that I say that this algorithm is 80%
 * in-accurate. I spend about a week working on a mathematical
 * equation for this grid but there is more than one possible outcome.
 * I was still very anxious to figure out what kind of solution I could
 * come up with and write it in code so here it is. Maybe when
 * I get to college or a year from now I will come back
 * and attempt to solve this in a more efficient manner but
 * for now I couldn't help myself but start building.
 * 
 * 	The original question was...
 * 
 * Personal note:: Just because it works doesn't mean it's good.
 */


import static java.lang.System.*;
import java.lang.*;
import java.util.*;


public class queen_grids {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Enviroment();
	}
}

class Enviroment implements Runnable{
	Scanner scan = new Scanner(in);
	char[][] grid;
	int n;
	
	public void run() {
		populate_grid();
		queen_fill();
	}
	
	public void print_grid() {
		out.println();
		out.println();
		for(char[] row : grid) 
		  out.println(Arrays.toString(row));
	}
	
	public void populate_grid() {
		out.println("Enter number for NxN grid:: ");
		n = scan.nextInt();
		grid = new char[n][n];
		
		for(int i=0; i<grid.length; i++)
		Arrays.fill(grid[i], '0');
	}
	
	/* 
	 * 'queen_fill()' method hashes through grid and fills
	 * in empty spaces in grid with queens 
	 * also prevents any other queen from being placed
	 * where it is at risk by another queen.
	 * 
	 * read comments on called methods for more information
	 */
	public void queen_fill() {
		for(int i=0; i<n; i++){ //row hasher
			for(int j=0; j<n; j++){ //column hasher
				out.println(grid[i][j]);
				if(grid[i][j] == '0') {
					out.println("Placing queen at ::" + i + " " + j);
					grid[i][j] = '1';
					
					block_straight(i,j);// horizontal/vertical block
					block_diagonal_a(i,j);
					block_diagonal_b(i,j);
					print_grid();
		 }}}
	}

	public void block_straight(int row, int column) {
		for(int j=0; j<n; j++)
			if(grid[row][j] != '1')
				grid[row][j] = 'x';
		
		for(int i=0; i<n; i++)
			if(grid[i][column] != '1')
			 grid[i][column] = 'x';
	}
	
	//blocks spots diagonal from queen top left to bottom right
	public void block_diagonal_a(int row, int column) {
		
		int largestV=0; //used to mark magnitude length in for loop
		int[] pos = {row, column} ; //starting position of block
		if(row > column) {
			pos[0] = row - column;
			pos[1] = 0;
			largestV = pos[0];
			//out.println(pos[0]);
		}
		else{
			pos[1] = column - row;
			pos[0] = 0;
			largestV = pos[1];
			//out.println(pos[1]);
		}
		
		out.println(Arrays.toString(pos));
		
		//vector hasher
		//blocks diagonal from with slope of -1 relative to client
		for(int j = 0; j<n - largestV ; j++)
			if(grid[ pos[0] + j ][ pos[1] + j ] == '0')
				grid[ pos[0] + j ][ pos[1] + j ] = 'x';
	}
		
	/* 
	 * if you start from bottom left to top right then for loop wont function
	 * since variable j is either adding or subtracting 
	 * so for that reason, block begins from top right to
	 * bottom left
	 */
	public void block_diagonal_b(int row, int column) {
		int definer=0; //used to mark magnitude length in for loop
		int[] pos = {row, column} ; //will be starting position of block
		
		if(row ==0 && column ==0){
			definer = 0;
		}
		else if(row < (n/2)) {
			out.println("Row < " + n/2);
			pos[0] = 0; //top most row starting
			pos[1] += row; //current column + steps taken to get top 
			definer = pos[1];
		}
		else if(row > n/2) {
			out.println("Row > " + n/2);
			pos[1] = n -1; //start from most right column
			pos[0] -= (n - column) -1; //leads row to starting point
			definer = (n - pos[0]) -1;
		}
		else{ definer = 0;} //temporary condition
		
		out.println(Arrays.toString(pos));
		
		//vector hasher
		//blocks diagonal from with slope of -1 relative to client
		// n - largestV is the limit of spaces available to hash
		for(int j=0; j<definer +1; j++)
			if(grid[ pos[0] + j][ pos[1] - j ] == '0')
				grid[ pos[0] + j ][ pos[1] - j ] = 'x';
		}
		
	
	public Enviroment() {
		run();
	}
}
