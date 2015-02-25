package ganjiho.game.players;

import java.util.Scanner;

import ganjiho.game.Board;
import ganjiho.game.Peg;

public abstract class Player 
{
	private Scanner scanner;
	
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
		
		System.out.print("row,col: ");
		String input = scanner.nextLine();
		String[] coords = input.split(",");
		
		if(coords.length != 2)
		{
			System.out.println("Malformed input.");
			return this.getMove(board);
		}
		
		int[] move = new int[2];
		try
		{
			move[0] = Integer.parseUnsignedInt(coords[0]);
			move[1] = Integer.parseUnsignedInt(coords[1]);
			
			if(move[0] > board.getRows() || move[1] > board.getRows())
			{
				System.out.println("Input out of range.");
				return this.getMove(board);
			}
			
			if(board.isCellOccupied(move[0], move[1]))
			{
				System.out.println("Cell is already occupied.");
				return this.getMove(board);
			}
		}
		catch(Exception e)
		{
			System.out.println("Malformed input.");
			return this.getMove(board);
		}
		
		return move;
	}
}
