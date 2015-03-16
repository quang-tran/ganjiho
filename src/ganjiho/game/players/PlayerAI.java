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
	
	public String moveToCell(int[] move)
	{
		String cell = "";
		switch(move[0])
		{
		case 1: cell += 'A'; break;
		case 2: cell += 'B'; break;
		case 3: cell += 'C'; break;
		case 4: cell += 'D'; break;
		case 5: cell += 'E'; break;
		case 6: cell += 'F'; break;
		case 7: cell += 'G'; break;
		case 8: cell += 'H'; break;
		}
		
		cell += move[1];
		return cell;
	}
}
