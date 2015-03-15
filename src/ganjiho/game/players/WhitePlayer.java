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
	public void playTurn(Board board) 
	{
		System.out.println("White turn");
		int[] move = getMove(board);
		
		while(move[0] + 1 > board.getRows() || board.isCellOccupied(move[0] + 1, move[1]))
		{
			System.out.println("No vertical space for that space.");
			move = getMove(board);
		}
		
		board.placePeg(move[0], move[1], pegType);
		board.placePeg(move[0] + 1, move[1], pegType);
	}

	@Override
	public int checkAvailable(Board board) 
	{
		int available = 0;
		int rows = board.getRows();
		for(int col = 1; col <= rows; col++)
		{
			for(int row = 2; row <= rows; row++)
			{
				if(!board.isCellOccupied(row, col) && !board.isCellOccupied(row - 1, col))
				{
					available++;
				}
			}
		}
		
		return available;
	}
	
	public String toString()
	{
		return "White";
	}
}
