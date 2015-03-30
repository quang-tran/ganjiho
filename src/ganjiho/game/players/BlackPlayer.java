package ganjiho.game.players;

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
		int[] move = getMove(board);
		
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
	
	@Override
	public int checkGuaranteed(Board board)
	{
		return 0;
	}
	
	public String toString()
	{
		return "Black";
	}
}
