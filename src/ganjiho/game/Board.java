package ganjiho.game;

import ganjiho.game.players.BlackPlayer;
import ganjiho.game.players.Player;
import ganjiho.game.players.WhitePlayer;

public class Board 
{
	private int rows;
	private Peg[][] board;
	
	private int turnIndex;
	private Player[] players;
	
	public Board(int rows)
	{
		this.rows = rows;
		board = new Peg[rows][rows];
		
		turnIndex = 0;
		players = new Player[] {new WhitePlayer(), new BlackPlayer()};
	}
	
	public void playMove()
	{
		players[turnIndex].playTurn(this);
		turnIndex = (turnIndex + 1) % players.length;
	}
	
	public void placePeg(int row, int col, Peg pegType)
	{
		board[row - 1][col - 1] = pegType;
	}
	
	public boolean isMoveAvailable()
	{
		return players[turnIndex].checkAvailable(this);
	}
	
	public boolean isCellOccupied(int row, int col)
	{
		return board[row - 1][col - 1] != null;
	}
	
	public String winner()
	{
		int winnerIndex = (turnIndex + 1) % players.length;
		return "The winner is " + players[winnerIndex];
	}
	
	public String toString()
	{
		StringBuffer output = new StringBuffer();
		for(int i = 0; i < rows + 1; i++)
		{
			output.append(i + "\t");
		}
		output.append("\n");
		
		for(int i = 0; i < rows; i++)
		{
			output.append(i+1 + "\t");
			for(int j = 0; j < rows; j++)
			{
				String cell = board[i][j] == null ? "-" : board[i][j].toString();
				output.append(cell + "\t");
			}
			output.append("\n");
		}
		return output.toString();
	}

	public int getRows() 
	{
		return rows;
	}
}
