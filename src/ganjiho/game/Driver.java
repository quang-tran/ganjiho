package ganjiho.game;

import ganjiho.game.players.BlackPlayer;
import ganjiho.game.players.WhitePlayer;
import ganjiho.heuristic.Heuristic;
import ganjiho.heuristic.HeuristicRemainingMoves;

public class Driver 
{
	public static void main(String[] args)
	{
		WhitePlayer white = new WhitePlayer(null);
		BlackPlayer black = new BlackPlayer(null);
		
		//Heuristic h = new HeuristicRemainingMoves(white);
		//white = new WhitePlayer(new PlayerAI(h));
		
		Board board = new Board(8, white, black);
		
		try
		{
			while(board.isMoveAvailable())
			{
				System.out.println(board);
				board.playMove();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		System.out.println("\n\n**** WINNER ****\n\n" + board);
		System.out.println(board.winner());
	}
}
