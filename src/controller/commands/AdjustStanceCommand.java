package controller.commands;


import model.Board;
import model.Coordinate;
import model.pieces.PieceInterface;

public class AdjustStanceCommand implements Command {

    private Coordinate currentlySelected;
    private Board board;
    private PieceInterface newPiece;
    private PieceInterface oldPiece;

    public AdjustStanceCommand(Board board, Coordinate currentlySelected, PieceInterface newPiece) {
        this.currentlySelected = currentlySelected;
        this.newPiece = newPiece;
        this.board = board;
        this.oldPiece = null;
    }
    @Override
    public void execute() {
        this.oldPiece =  board.getPiece(currentlySelected);
        board.setPiece(currentlySelected, newPiece);
    }

    @Override
    public void undo() {
        board.setPiece(currentlySelected, oldPiece);
    }

    @Override
    public boolean CanUndo() {
        return oldPiece != null;
    }
}
