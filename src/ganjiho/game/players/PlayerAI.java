package ganjiho.game.players;

import ganjiho.game.Board;
import ganjiho.heuristic.Heuristic;

/**
 * Class that runs heuristics on the game board in order to find the next best move
 */
public abstract class PlayerAI 
{
	protected Heuristic h;
	
	public PlayerAI(Heuristic h)
	{
		this.h = h;
	}
	
	/**
	 * Calculate the move that should be played based on the current state of the board
	 * @param board State of the current board
	 * @return { row, col } of the move to play. 
	 */
	public abstract int[] getMove(Board board);
}
