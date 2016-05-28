package model.pieces;


public class DefensiveStanceDecoratedPiece  extends DecoratedPiece {
    public DefensiveStanceDecoratedPiece(PieceInterface piece) {
        super(piece);
    }

    @Override
    public int getDefense() {
        return super.getDefense()*2;
    }

    @Override
    public int getStrength() {
        return super.getStrength()/2;
    }
}
