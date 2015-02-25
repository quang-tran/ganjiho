package ganjiho.game.players;

import ganjiho.game.Board;
import ganjiho.game.PegWhite;

public class WhitePlayer extends Player 
{
	public WhitePlayer()
	{
		super(new PegWhite());
	}
	
	@Override
	public void playTurn(Board board) 
	{
		System.out.println("White turn");
		int[] move = getMove(board);
		
		while(move[0] + 1 > board.getRows() || board.isCellOccupied(move[0] + 1, move[1]))
		{
			System.out.println("No vertical space for that column.");
			move = getMove(board);
		}
		
		board.placePeg(move[0], move[1], pegType);
		board.placePeg(move[0] + 1, move[1], pegType);
	}
}
