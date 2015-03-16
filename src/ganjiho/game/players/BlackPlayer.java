package ganjiho.game.players;

import java.util.Calendar;

import ganjiho.game.Board;
import ganjiho.game.PegBlack;

public class BlackPlayer extends Player 
{
	public BlackPlayer(PlayerAI ai) 
	{
		super(new PegBlack(), ai);
	}

	@Override
	public void playTurn(Board board) throws Exception 
	{
		System.out.println("Black turn");
		int[] move;
		if(ai == null)
		{
			move = getMove(board);
		}
		else
		{
			long start = Calendar.getInstance().getTimeInMillis();
			move = ai.getMove(board);
			System.out.println("AI played move: " + ai.moveToCell(move));
			long end = Calendar.getInstance().getTimeInMillis();
			
			System.out.println("Time to calculate move: " + (end - start)/1000.0 + " seconds.");
		}
		
		while(move[1] + 1 > board.getRows() || board.isCellOccupied(move[0], move[1] + 1))
		{
			if(ai == null)
			{
				System.out.println("No horizontal space for that move.");
				move = getMove(board);
			}
			else
			{
				throw new Exception("AI has played an invalid move. Human player wins.");
			}
		}
		
		board.placePeg(move[0], move[1], pegType);
		board.placePeg(move[0], move[1] + 1, pegType);
	}
	
	@Override
	public int checkAvailable(Board board) 
	{
		int available = 0;
		int rows = board.getRows();
		for(int row = 1; row <= rows; row++)
		{
			for(int col = 2; col <= rows; col++)
			{
				if(!board.isCellOccupied(row, col) && !board.isCellOccupied(row, col - 1))
				{
					available++;
				}
			}
		}
		
		return available;
	}
	
	
	
	public String toString()
	{
		return "Black";
	}
}
