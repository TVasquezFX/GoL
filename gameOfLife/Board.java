import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {
    // assume square board

    private int n;
    private boolean[][] board; // true is live, false is dead

    public Board(int n) {
	this.n = n;
	this.board = new boolean[n][n];
    }

    public Board() {
	this(20);
    }

    public Board(File f){
    	this();
    	try {
			Scanner s = new Scanner(f);
			int counter =0;
			while (s.hasNextLine()) {
				String line = s.nextLine();
//				System.out.println(line);
				for (int i = 0; i < n; i++)
				{
					board[counter][i] = line.charAt(i) == 'X';
				}
				counter++;
			}
			s.close();
		}catch (FileNotFoundException e) {
		}

	}

    public String toString() {
	String output = "";
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		output += board[i][j] ? "X" : "-";
	    }
	    output += "\n";
	}
	return output;
    }

    public boolean get(int i, int j) {
	return 0 <= i && i < n && 0 <= j && j < n && board[i][j];
    }

    public void set(int i, int j, boolean input) {
	if (0 <= i && i < n && 0 <= j && j < n) {
	    board[i][j] = input;
	}
    }
    
    // returns the no. of alive neighbors at i, j.
    public int neighbors(int i, int j) {
	    /*
	      (i-1, j-1) (i-1, j) (i-1, j+1)
	      (i, j-1)            (i, j+1)
	      (i+1, j-1) (i+1, j) (i+1, j+1)
	    */
	    int[] ivals = {i-1, i-1, i-1, i, i, i+1, i+1, i+1};
	    int[] jvals = {j-1, j, j+1, j-1, j+1, j-1, j, j+1};
	    // thus, (ivals[0], jvals[0]) gets top left, (ivals[4], jvals[4]) gets middle right, etc.
	    
	    int neighbors = 0;
	    for (int a = 0; a < ivals.length; a++) {
		if (get(ivals[a], jvals[a])) {
		    neighbors++;
		}
	    } // now, neighbors has the num. of alive neighbors.

	    return neighbors;
    }
    
    public static void main(String[] args) {
	Board b = new Board();
	b.set(0, 0, true);
	b.set(10, 10, true);
	b.set(0, 0, true);
	b.set(3, 3, true);
	b.set(3, 3, false);
	b.set(1, 1, false);
	System.out.println(b);
    }
}
