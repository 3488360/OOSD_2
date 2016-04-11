package pieces;


import model.Coordinate;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class WizardPiece extends AbstractPiece{
    /* The Wizard is ranged, has low hp, high attack and a medium move range */
    public WizardPiece(Player player) {
        super(player);
        maxHealth = 100;
        strength = 240;
        cost = 10;
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public List<Coordinate> getMoves(Coordinate currentCoordinate) {
       ArrayList<Coordinate> movesList = new ArrayList<Coordinate>();
       movesList.add(new Coordinate(currentCoordinate.x+1,currentCoordinate.y+1));
//       movesList.add()
    	return null;
    }

    @Override
    public List<Coordinate> getAttackRange() {
        return null;
    }

}
