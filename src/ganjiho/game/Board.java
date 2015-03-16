package ganjiho.game;

import ganjiho.game.players.Player;

public class Board 
{
	private int rows;
	private Peg[][] board;
	
	private Player[] players;
	
	public Board(int rows, Player white, Player black)
	{
		this.rows = rows;
		board = new Peg[rows][rows];
		
		players = new Player[] {white, black};
	}
	
	public void playMove(boolean whiteTurn) throws Exception
	{
		try
		{
			if(whiteTurn)
			{
				players[0].playTurn(this);
			}
			else
			{
				players[1].playTurn(this);
			}
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void placePeg(int row, int col, Peg pegType)
	{
		board[row - 1][col - 1] = pegType;
	}
	
	public boolean isMoveAvailable(boolean whiteTurn)
	{
		int rows = this.rows + 1;
		if(whiteTurn)
		{
			for(int row = 2; row < rows; row++)
			{
				for(int col = 1; col < rows; col++)
				{
					if(!isCellOccupied(row, col) && !isCellOccupied(row - 1, col))
					{
						return true;
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
					if(!isCellOccupied(row, col) && !isCellOccupied(row, col - 1))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean isCellOccupied(int row, int col)
	{
		return board[row - 1][col - 1] != null;
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
	 * Create a deep copy of the current board state, with no players
	 * @return Board b that has the same state as this
	 */
	public Board copyState()
	{
		Board b = new Board(this.rows, null, null);
		
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
