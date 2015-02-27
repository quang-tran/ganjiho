package ganjiho.game;

public class Driver 
{
	public static void main(String[] args)
	{
		Board board = new Board(8);
		while(board.isMoveAvailable())
		{
			System.out.println(board);
			board.playMove();
		}
		
		System.out.println("\n\n**** WINNER ****\n\n" + board);
		System.out.println(board.winner());
	}
}
