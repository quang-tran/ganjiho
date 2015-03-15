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
		players = new Player[] {new WhitePlayer(null), new BlackPlayer(null)};
	}
	
	public void playMove() throws Exception
	{
		try
		{
			players[turnIndex].playTurn(this);
			turnIndex = (turnIndex + 1) % players.length;
		}
		catch(Exception e)
		{
			turnIndex = (turnIndex + 1) % players.length;
			throw e;
		}
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
		StringBuffer output = new StringBuffer("\\\t");
		for(int i = 1; i < rows + 1; i++)
		{
			output.append(i + "\t");
		}
		output.append("\n");
		
		char rowLabel = 'A';
		for(int i = 0; i < rows; i++)
		{
			output.append(rowLabel++ + "\t");
			for(int j = 0; j < rows; j++)
			{
				String cell = board[i][j] == null ? "-" : board[i][j].toString();
				output.append(cell + "\t");
			}
			output.append("\n");
		}
		return output.toString();
	}

	/**
	 * Create a deep copy of the current board
	 * @return Board b that has the same state as this
	 */
	public Board copy()
	{
		Board b = new Board(this.rows);
		
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < rows; j++)
			{
				Peg p = this.getCell(i+1, j+1);
				if(p != null)
				{
					b.placePeg(i+1, j+1, p);
				}
			}
		}
		
		return b;
	}
	
	public int getRows() 
	{
		return rows;
	}
	
	public Peg getCell(int row, int col)
	{
		return board[row - 1][col - 1];
	}
}
