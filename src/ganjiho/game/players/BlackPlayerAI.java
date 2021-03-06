package ganjiho.game.players;

import ganjiho.heuristic.GameStateNode;
import ganjiho.heuristic.Heuristic;
import ganjiho.game.Board;

import java.util.ArrayList;

public class BlackPlayerAI extends PlayerAI
{
	public BlackPlayerAI(Heuristic h)
	{
		super(h);
	}
	
	public int[] getMove(Board board)
	{
		GameStateNode root = new GameStateNode(null, board);
		int value = minimax(root, 3, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
		
		GameStateNode nextState = null;
		for(GameStateNode n : root.getChildren())
		{
			if(n.getHeuristicValue() == value)
			{
				nextState = n;
				break;
			}
		}
		
		//root.print();
		
		Board nextBoard = nextState.getBoard();
		return getNextMove(board, nextBoard);
	}
	
	private int minimax(GameStateNode node, int depth, int alpha, int beta, boolean maximizing)
	{
		Board current = node.getBoard();
		node.setVisited(true);
		if(depth == 0 || !current.isMoveAvailable(!maximizing))
		{
			int heuristicValue = h.calculate(current);
			node.setHeuristicValue(heuristicValue);
			return heuristicValue;
		}
		
		if(maximizing)
		{
			node.generateMoveList(false);
			int max = Integer.MIN_VALUE;
			ArrayList<GameStateNode> children = node.getChildren();
			
			for(GameStateNode child : children)
			{
				max = Math.max(max, minimax(child, depth - 1, alpha, beta, false));
				alpha = Math.max(alpha, max);
				node.setHeuristicValue(max);
				
				if(alpha > beta)
				{
					break;
				}
			}
			return max;
		}
		else
		{
			node.generateMoveList(true);
			int min = Integer.MAX_VALUE;
			ArrayList<GameStateNode> children = node.getChildren();
			
			for(GameStateNode child : children)
			{
				min = Math.min(min, minimax(child, depth - 1, alpha, beta, false));
				beta = Math.min(beta, min);
				node.setHeuristicValue(min);
				
				if(alpha > beta)
				{
					break;
				}
			}
			return min;
		}
	}
	
	private int[] getNextMove(Board current, Board next)
	{
		int rows = current.getRows() + 1;
		for(int i = 1; i < rows; i++)
		{
			for(int j = 1; j < rows; j++)
			{
				if(!current.isCellOccupied(i, j) && next.isCellOccupied(i, j))
				{
					return new int[] {i, j};
				}
			}
		}
		
		return null;
	}
}
