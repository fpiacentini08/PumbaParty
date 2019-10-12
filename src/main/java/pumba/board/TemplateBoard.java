package pumba.board;

import java.util.Random;

public class TemplateBoard
{
	public static final int NOT_PLAYABLE = -1;
	public static final int COMMON = 0;
	public static final int WIN_COINS = 1;
	public static final int LOSE_COINS = 2;
	public static final int QUANTITY_OF_PLAYABLE_CELLS = 3;
	public static final Integer dimension = 12;

	private Random rand = new Random();

	public int mat[][] = new int[Board.getDimension()][Board.getDimension()];

	// public TemplateBoard()
	// {
	// mat[1][1] = mat[1][2] = mat[1][4] = mat[1][5] = mat[1][7] = mat[1][9] =
	// mat[1][11] = mat[1][13] = mat[1][14] = mat[1][16] = mat[1][17] =
	// mat[2][1] = mat[2][7] = mat[2][14] = mat[2][18] = mat[3][7] = mat[3][14]
	// = mat[3][18] = mat[4][1] = mat[4][18] = mat[5][1] = mat[5][7] =
	// mat[5][14] = mat[6][6] = mat[6][8] = mat[6][9] = mat[6][14] = mat[6][18]
	// = mat[7][1] = mat[7][5] = mat[7][10] = mat[7][18] = mat[8][1] = mat[8][5]
	// = mat[8][17] = mat[9][2] = mat[9][4] = mat[9][10] = mat[9][11] =
	// mat[9][13] = mat[9][15] = mat[9][17] = mat[9][18] = mat[10][2] =
	// mat[10][5] = mat[10][10] = mat[10][14] = mat[10][18] = mat[11][14] =
	// mat[12][2] = mat[12][5] = mat[12][10] = mat[12][18] = mat[13][6] =
	// mat[13][8] = mat[13][10] = mat[13][14] = mat[13][18] = mat[14][2] =
	// mat[14][5] = mat[14][14] = mat[14][16] = mat[14][18] = mat[15][2] =
	// mat[15][10] = mat[15][14] = mat[16][3] = mat[16][5] = mat[17][5] =
	// mat[17][10] = mat[14][14] = mat[18][5] = mat[18][7] = mat[18][9] =
	// mat[18][11] = mat[18][13] = 1;
	//
	// mat[1][3] = mat[1][8] = mat[1][12] = mat[1][18] = mat[3][1] = mat[4][14]
	// = mat[6][1] = mat[6][7] = mat[8][10] = mat[9][1] = mat[9][5] = mat[9][12]
	// = mat[9][16] = mat[11][2] = mat[11][10] = mat[13][5] = mat[13][7] =
	// mat[13][9] = mat[14][15] = mat[14][17] = mat[16][4] = mat[16][14] =
	// mat[18][10] = mat[18][12] = mat[18][14] = 2;
	//
	// mat[1][6] = mat[1][10] = mat[1][15] = mat[4][7] = mat[6][5] = mat[6][10]
	// = mat[8][18] = mat[9][3] = mat[9][14] = mat[11][5] = mat[11][18] =
	// mat[12][14] = mat[13][2] = mat[14][10] = mat[15][5] = mat[16][2] =
	// mat[16][10] = mat[18][6] = mat[18][8] = 3;
	// }

	public TemplateBoard()
	{
		for (int i = 0; i < this.dimension; i++)
		{
			for (int j = 0; j < this.dimension; j++)
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
		return rand.nextInt(QUANTITY_OF_PLAYABLE_CELLS);
	}
}
