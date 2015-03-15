package ganjiho.heuristic;

import ganjiho.game.Board;

import java.util.ArrayList;

/**
 * Class to keep game state in order to run heuristic on it and keep move
 */
public class GameStateNode 
{
	private GameStateNode parent;
	private ArrayList<GameStateNode> children;
	
	private Board board;
	
	public GameStateNode(GameStateNode parent, Board board)
	{
		this.parent = parent;
		this.children = new ArrayList<GameStateNode>();
		
		this.board = board;
	}
	
	public void addChild(GameStateNode child)
	{
		children.add(child);
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
}
