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
		int[] move;
		if(ai == null)
		{
			move = getMove(board);
		}
		else
		{
			move = ai.getMove(board);
			System.out.println("AI played move: " + rowToChar(move[0]) + "" + move[1]);
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
	
	public char rowToChar(int row)
	{
		switch(row)
		{
		case 0: return 'A';
		case 1: return 'B';
		case 2: return 'C';
		case 3: return 'D';
		case 4: return 'E';
		case 5: return 'F';
		case 6: return 'G';
		case 7: return 'H';
		}
		
		return 'x';
	}
	
	public String toString()
	{
		return "Black";
	}
}
