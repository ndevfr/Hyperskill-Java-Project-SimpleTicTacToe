package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String grid = "         ";
        char[] marks = {'X', 'O'};
        boolean finished = false;
        int nbTurns = 0;
        while (!finished) {
            printGrid(grid);
            if(nbTurns % 2 == 0){
                grid = promptUser(scanner, grid, 'X');
            } else {
                grid = promptUser(scanner, grid, 'O');
            }
            printGrid(grid);
            finished = checkGame(grid);
            nbTurns++;
        }
    }

    public static void printGrid(String grid){
        System.out.println("---------");
        for(int i=0; i<=2; i++){
            printLine(i, grid);
        }
        System.out.println("---------");
    }

    public static void printLine(int line, String grid){
        System.out.println("| " + grid.charAt(3*line) + " " + grid.charAt(3*line+1) + " " + grid.charAt(3*line+2) + " |");
    }

    public static String promptUser(Scanner scanner, String grid, char mark){
        boolean goodMove = false;
        char[] arrayGrid = grid.toCharArray();
        do {
            String line = scanner.nextLine();
            String pattern = "\\d+ \\d+";
            boolean isGood = line.matches(pattern);
            if(!isGood){
                System.out.println("You should enter numbers!");
            } else {
                String[] coords = line.split(" ");
                int x = Integer.parseInt(coords[1]);
                int y = Integer.parseInt(coords[0]);

                if (x < 1 || x > 3 || y < 1 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (arrayGrid[(y - 1) * 3 + (x - 1)] == ' ') {
                    goodMove = true;
                    arrayGrid[(y - 1) * 3 + (x - 1)] = mark;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }
        } while (!goodMove);
        return String.valueOf(arrayGrid);
    }

    public static boolean checkGame(String grid){
        boolean finish = true;
        int countO = 0;
        int countX = 0;
        for(int i = 0; i < 9; i++){
            if(grid.charAt(i) == 'O'){
                countO++;
            } else if(grid.charAt(i) == 'X'){
                countX++;
            } else {
                finish = false;
            }
        }
        boolean impossible = countO > countX+1 || countX > countO + 1;
        boolean playerX = false;
        boolean playerO = false;
        int[][] aligns = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
        for(int[] align : aligns){
            if(grid.charAt(align[0]) == grid.charAt(align[1]) && grid.charAt(align[0]) == grid.charAt(align[2])){
                if(grid.charAt(align[0]) == 'X'){
                    playerX = true;
                } else if(grid.charAt(align[0]) == 'O'){
                    playerO = true;
                }
            }
        }
        if(playerX && !playerO && !impossible){
            System.out.println("X wins");
            return true;
        } else if(playerO && !playerX && !impossible){
            System.out.println("O wins");
            return true;
        } else if(!playerO && !playerX && !impossible && finish){
            System.out.println("Draw");
            return true;
        } else if(!playerO && !playerX && !impossible){
            //System.out.println("Game not finished");
            return false;
        } else {
            System.out.println("Impossible");
            return true;
        }
    }
}
