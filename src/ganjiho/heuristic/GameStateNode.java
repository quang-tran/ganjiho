package ganjiho.heuristic;

import ganjiho.game.Board;
import ganjiho.game.Peg;
import ganjiho.game.PegBlack;
import ganjiho.game.PegWhite;

import java.util.ArrayList;

/**
 * Class to keep game state in order to run heuristic on it and keep move
 */
public class GameStateNode 
{
	private GameStateNode parent;
	private ArrayList<GameStateNode> children;
	
	private Board board;
	private int heuristicValue;
	
	public GameStateNode(GameStateNode parent, Board board)
	{
		this.parent = parent;
		this.children = new ArrayList<GameStateNode>();
		this.board = board;
	}
	
	public GameStateNode getParent()
	{
		return parent;
	}
	
	public ArrayList<GameStateNode> getChildren()
	{
		return children;
	}
	
	public Board getBoard()
	{
		return board;
	}

	public int getHeuristicValue() 
	{
		return heuristicValue;
	}

	public void setHeuristicValue(int heuristicValue) 
	{
		this.heuristicValue = heuristicValue;
	}
	
	public void generateMoveList(boolean whiteTurn)
	{
		int rows = board.getRows() + 1;
		if(whiteTurn)
		{
			for(int row = 2; row < rows; row++)
			{
				for(int col = 1; col < rows; col++)
				{
					if(!board.isCellOccupied(row, col) && !board.isCellOccupied(row - 1, col))
					{
						Board newBoard = board.copyState();
						Peg whitePeg = new PegWhite();
						newBoard.placePeg(row, col, whitePeg);
						newBoard.placePeg(row-1, col, whitePeg);
						
						GameStateNode n = new GameStateNode(this, newBoard);
						children.add(n);
					}
				}
			}
		}
		else
		{
			for(int row = 1; row < rows; row++)
			{
				for(int col = 2; col < rows; col++)
				{
					if(!board.isCellOccupied(row, col) && !board.isCellOccupied(row, col - 1))
					{
						Board newBoard = board.copyState();
						Peg blackPeg = new PegBlack();
						newBoard.placePeg(row, col, blackPeg);
						newBoard.placePeg(row, col-1, blackPeg);
						
						GameStateNode n = new GameStateNode(this, newBoard);
						children.add(n);
					}
				}
			}
		}
	}
}
