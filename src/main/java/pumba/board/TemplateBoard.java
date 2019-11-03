package pumba.board;

import java.util.Random;

public class TemplateBoard
{
	public static final int NOT_PLAYABLE = -1;
	public static final int COMMON = 0;
	public static final int WIN_COINS = 1;
	public static final int LOSE_COINS = 2;
	public static final Integer dimension = 12;

	private Random rand = new Random();

	public int mat[][] = new int[Board.getDimension()][Board.getDimension()];

	public TemplateBoard()
	{
		for (int i = 0; i < TemplateBoard.dimension; i++)
		{
			for (int j = 0; j < TemplateBoard.dimension; j++)
			{
				mat[i][j] = NOT_PLAYABLE;
				if ((i == 1 || i == 10) && (j != 0 && j != 11))
				{
					mat[i][j] = randomPlayableCell();
				}
				else if ((j == 1 || j == 4 || j == 7 || j == 10) && (i > 0 && i < 11))
				{
					mat[i][j] = randomPlayableCell();
				}
			}
		}

	}

	private int randomPlayableCell()
	{
		switch (rand.nextInt(10))
		{
			case 0:
				return COMMON;
			case 1:
			case 2:
			case 3:
				return LOSE_COINS;
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			default:
				return WIN_COINS;
		}
	}
}
