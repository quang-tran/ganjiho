package ganjiho.game;

import ganjiho.game.players.BlackPlayer;
import ganjiho.game.players.WhitePlayer;

public class Driver 
{
	public static void main(String[] args)
	{
		WhitePlayer white = new WhitePlayer(null);
		BlackPlayer black = new BlackPlayer(null);
		
//		Heuristic whiteh = new HeuristicGuaranteed(white, black);
//		white = new WhitePlayer(new WhitePlayerAI(whiteh));
//		
//		Heuristic blackh = new HeuristicBlockAndConserve(black, white);
//		black = new BlackPlayer(new BlackPlayerAI(blackh));
		
		Board board = new Board(11, white, black);
		
		boolean whiteTurn = true;
		try
		{
			while(board.isMoveAvailable(whiteTurn))
			{
				System.out.println(board);
				board.playMove(whiteTurn);
				whiteTurn = !whiteTurn;
			}
		}
		catch(Exception e)
		{
			System.out.println("Invalid: " + e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("\n\n**** WINNER ****\n\n" + board);
		System.out.println("The winner is " + (!whiteTurn ? "white!" : "black!"));
	}
}
