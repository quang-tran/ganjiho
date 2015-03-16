package ganjiho.heuristic;

import ganjiho.game.Board;
import ganjiho.game.players.Player;

public class HeuristicRemainingMoves implements Heuristic 
{
	private Player player;
	
	public HeuristicRemainingMoves(Player player)
	{
		this.player = player;
	}
	
	@Override
	public int calculate(Board b) 
	{
		return player.checkAvailable(b);
	}
}
