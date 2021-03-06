package ganjiho.game.players;

import java.util.Calendar;
import java.util.Scanner;

import ganjiho.game.Board;
import ganjiho.game.Peg;

public abstract class Player 
{
	private static Scanner scanner;
	
	protected Peg pegType;
	protected PlayerAI ai;
	
	public Player(Peg pegType, PlayerAI ai)
	{
		this.pegType = pegType;
		this.ai = ai;
	}
	
	/**
	 * Place a pair of pegs onto the board
	 * @throws Exception if AI plays an invalid move
	 */
	public abstract void playTurn(Board board) throws Exception;

	protected int[] getMove(Board board) 
	{
		// Get the row and column that the player wants to place a peg
		int[] move = null;
		if(ai != null)
		{
			long start = Calendar.getInstance().getTimeInMillis();
			move = ai.getMove(board);
			System.out.println("AI played move: " + ai.moveToCell(move));
			long end = Calendar.getInstance().getTimeInMillis();
			
			System.out.println("Time to calculate move: " + (end - start)/1000.0 + " seconds.");
		}
		else
		{
			if(scanner == null)
			{
				scanner = new Scanner(System.in);
			}
			
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
					move = new int[] {parseRowChar(input.charAt(0)), Integer.parseUnsignedInt(input.substring(1))};
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
		}
		return move;
	}
	
	private int parseRowChar(char row) throws Exception
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
		case 'I': case 'i': return 9;
		case 'J': case 'j': return 10;
		case 'K': case 'k': return 11;
		default: throw new Exception("Malformed input.");
		}
	}
	
	/**
	 * Check how many moves are available to a player on a specific board configuration
	 * @param board Current state of the board
	 * @return The number of available moves left
	 */
	public abstract int checkAvailable(Board board);
	
	public abstract int checkGuaranteed(Board board);
}
