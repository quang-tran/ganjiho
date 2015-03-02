package ganjiho.game.players;

import java.util.Scanner;

import ganjiho.game.Board;
import ganjiho.game.Peg;

public abstract class Player 
{
	private static Scanner scanner;
	
	protected Peg pegType;
	
	public Player(Peg pegType)
	{
		this.pegType = pegType;
	}
	
	/**
	 * Place a pair of pegs onto the board
	 */
	public abstract void playTurn(Board board);

	protected int[] getMove(Board board) 
	{
		// Get the row and column that the player wants to place a peg
		if(scanner == null)
		{
			scanner = new Scanner(System.in);
		}
		
		int[] move = null;
		
		while(move == null)
		{
			System.out.print("RowCol: ");
			String input = scanner.nextLine();
			
			if(input.length() != 2)
			{
				System.out.println("Malformed input.");
				move = null;
			}
			
			try
			{
				move = new int[] {parseRowChar(input.charAt(0)), Integer.parseUnsignedInt(input.substring(1,2))};
				if(move[0] > board.getRows() || move[1] > board.getRows())
				{
					System.out.println("Input out of range.");
					move = null;
				}
				
				if(board.isCellOccupied(move[0], move[1]))
				{
					System.out.println("Cell is already occupied.");
					move = null;
				}
			}
			catch(Exception e)
			{
				System.out.println("Malformed input.");
				move = null;
			}
		}
		return move;
	}
	
	public int parseRowChar(char row) throws Exception
	{
		switch(row)
		{
		case 'A': case 'a':	return 1;
		case 'B': case 'b':	return 2;
		case 'C': case 'c':	return 3;
		case 'D': case 'd':	return 4;
		case 'E': case 'e':	return 5;
		case 'F': case 'f':	return 6;
		case 'G': case 'g':	return 7;
		case 'H': case 'h':	return 8;
		default: throw new Exception("Malformed input.");
		}
	}
	
	public abstract boolean checkAvailable(Board board);
}
