package ganjiho.heuristic;

import ganjiho.game.Board;
import ganjiho.game.players.Player;

public class HeuristicBlockAndConserve implements Heuristic 
{
	private Player block;
	private Player conserve;
	
	public HeuristicBlockAndConserve(Player block, Player conserve)
	{
		this.block = block;
		this.conserve = conserve;
	}
	@Override
	public int calculate(Board b) 
	{
		return conserve.checkAvailable(b) - block.checkAvailable(b) + 1;
	}
}
