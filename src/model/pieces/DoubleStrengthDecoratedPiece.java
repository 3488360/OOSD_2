package model.pieces;


public class DoubleStrengthDecoratedPiece extends DecoratedPiece {
    public DoubleStrengthDecoratedPiece(PieceInterface piece) {
        super(piece);
    }

    @Override
    public int getStrength() {
        return super.getStrength()*2;
    }
}