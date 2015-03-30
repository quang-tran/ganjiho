package ganjiho.game.players;

import ganjiho.game.Board;
import ganjiho.game.PegWhite;

public class WhitePlayer extends Player 
{
	public WhitePlayer(PlayerAI ai)
	{
		super(new PegWhite(), ai);
	}
	
	@Override
	public void playTurn(Board board) throws Exception 
	{
		System.out.println("White turn");
		int[] move = getMove(board);
		
		while(move[0] + 1 > board.getRows() || board.isCellOccupied(move[0] + 1, move[1]))
		{
			if(ai == null)
			{
				System.out.println("No vertical space for that move.");
				move = getMove(board);
			}
			else
			{
				throw new Exception("AI has played an invalid move. Human player wins.");
			}
		}
		
		board.placePeg(move[0], move[1], pegType);
		board.placePeg(move[0] + 1, move[1], pegType);
	}

	@Override
	public int checkAvailable(Board board) 
	{
		int available = 0;
		int cols = board.getRows() + 1;
		int rows = cols - 1;
		for(int col = 1; col < cols; col++)
		{
			for(int row = 1; row < rows; row++)
			{
				if(!board.isCellOccupied(row, col) && !board.isCellOccupied(row + 1, col))
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
		int guaranteed = 0;
		int cols = board.getRows() + 1;
		int rows = cols - 1;
		for(int col = 1; col < cols; col++)
		{
			for(int row = 1; row < rows; row++)
			{
				if(!board.isCellOccupied(row, col) && !board.isCellOccupied(row + 1, col))
				{
					if(col > 1 && 
						(!board.isCellOccupied(row, col - 1) || !board.isCellOccupied(row + 1, col - 1)))
					{
						continue;
					}
					if(col < rows &&
						(!board.isCellOccupied(row, col + 1) || !board.isCellOccupied(row + 1, col + 1)))
					{
						continue;
					}
					guaranteed++;
					row++;
				}
			}
		}
		return guaranteed;
	}
	
	public String toString()
	{
		return "White";
	}
}
