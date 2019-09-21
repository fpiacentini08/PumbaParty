package test.java.pumba.board.cells;

import main.java.pumba.board.cells.Position;

public class PositionFixture
{

	public static final Integer posX = 2;
	public static final Integer posY = 15;
	
    private Position build()
    {
    	Position pos = new Position(posX, posY);
        return pos;
    }

    public static Position withDefaults()
    {
        return new PositionFixture().build();
    }
}
