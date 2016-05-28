package model.pieces;


public class AggressiveStanceDecoratedPiece extends DecoratedPiece {
    public AggressiveStanceDecoratedPiece(PieceInterface piece) {
        super(piece);
    }

    @Override
    public int getStrength() {
        return super.getStrength()*2;
    }

    @Override
    public int getDefense() {
        return super.getDefense()/2;
    }
}