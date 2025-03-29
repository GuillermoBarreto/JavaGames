import java.util.Scanner;

// Enum for colors
enum Color { WHITE, BLACK }

// Abstract class for all chess pieces
abstract class Piece {
    Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board);
    public abstract String toString();
}

// Pawn class
class Pawn extends Piece {
    public Pawn(Color color) { super(color); }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        int direction = (color == Color.WHITE) ? -1 : 1;
        return startY == endY && endX == startX + direction && board[endX][endY] == null;
    }

    @Override
    public String toString() { return (color == Color.WHITE) ? "P" : "p"; }
}

// Rook class
class Rook extends Piece {
    public Rook(Color color) { super(color); }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        if (startX != endX && startY != endY) return false;  // Move must be straight
        return true;
    }

    @Override
    public String toString() { return (color == Color.WHITE) ? "R" : "r"; }
}

// Chess board class
class ChessBoard {
    Piece[][] board = new Piece[8][8];

    public ChessBoard() { setupBoard(); }

    private void setupBoard() {
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Color.BLACK);
            board[6][i] = new Pawn(Color.WHITE);
        }
        board[0][0] = board[0][7] = new Rook(Color.BLACK);
        board[7][0] = board[7][7] = new Rook(Color.WHITE);
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print((board[i][j] == null ? "." : board[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public boolean movePiece(int startX, int startY, int endX, int endY, Color player) {
        if (board[startX][startY] == null || board[startX][startY].color != player) {
            System.out.println("Invalid move: No piece or wrong color.");
            return false;
        }

        Piece piece = board[startX][startY];
        if (piece.isValidMove(startX, startY, endX, endY, board)) {
            board[endX][endY] = piece;
            board[startX][startY] = null;
            return true;
        } else {
            System.out.println("Invalid move for this piece.");
            return false;
        }
    }
}

// Main Game Class
public class ChessGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessBoard board = new ChessBoard();
        Color currentPlayer = Color.WHITE;

        while (true) {
            board.printBoard();
            System.out.println(currentPlayer + "'s turn. Enter move (format: x1 y1 x2 y2): ");
            int startX = scanner.nextInt(), startY = scanner.nextInt();
            int endX = scanner.nextInt(), endY = scanner.nextInt();

            if (board.movePiece(startX, startY, endX, endY, currentPlayer)) {
                currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
            }
        }
    }
}