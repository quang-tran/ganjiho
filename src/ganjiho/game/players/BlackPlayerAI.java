package ganjiho.game.players;

import ganjiho.heuristic.GameStateNode;
import ganjiho.heuristic.Heuristic;
import ganjiho.game.Board;

import java.util.ArrayList;

public class BlackPlayerAI extends PlayerAI implements Heuristic
{
	public BlackPlayerAI(Heuristic h)
	{
		super(h);
	}
	
	public int[] getMove(Board board)
	{
		int[] move = null;
		
		return move;
	}
	
	/*private int[] Minimax(GameStateNode node, int level, BlackPlayerAI player)
	{
		int value = 0;
		int bestPossibleScore = Integer.MAX_VALUE;
		int currentScore;
		int bestRow = -1;
		int bestCol = -1;
		
		ArrayList<int[]> nextPossibleMoves = GeneratePossibleMoves(node);
		
		if(level == 0 || node.getChildren() == null)
		{
			bestPossibleScore = calculate(node.getBoard());
		}
		else
		{
			
		}
	}*/
	
	public int calculate(Board b)
	{
		return 0;
	}
	
	private ArrayList<int[]> GeneratePossibleMoves(GameStateNode node)
	{
		ArrayList<int[]> nextPossibleMoves = new ArrayList<int[]>();
		int rows = node.getBoard().getRows();
		
		if(!node.getBoard().isMoveAvailable())
		{
			return nextPossibleMoves;
		}
		
		for(int row = 0; row < rows; row++)
		{
			for (int col = 0; col < rows; col++)
			{
				if(!node.getBoard().isCellOccupied(row+1, col+1) && !node.getBoard().isCellOccupied(row+1, col+2))
				{
					nextPossibleMoves.add(new int[] {row+1, col+1, col+2});
					//add the next board states to child?
				}
			}
		}
		
		return nextPossibleMoves;
	}
}
