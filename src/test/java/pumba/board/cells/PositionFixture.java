package pumba.board.cells;

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
