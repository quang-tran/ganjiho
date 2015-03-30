package ganjiho.heuristic;

import ganjiho.game.Board;
import ganjiho.game.players.Player;

public class HeuristicGuaranteed implements Heuristic 
{
	private Player player;
	private Player opponent;
	
	public HeuristicGuaranteed(Player player, Player opponent)
	{
		this.player = player;
		this.opponent = opponent;
	}
	
	@Override
	public int calculate(Board b) 
	{
		return player.checkGuaranteed(b) - opponent.checkGuaranteed(b)*2;
	}

}
