package ganjiho.game;

public class Driver 
{
	public static void main(String[] args)
	{
		Board board = new Board(8);
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
