import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[]args) {
		
		ArrayList<Integer> playerPositions = new ArrayList<Integer>();
		ArrayList<Integer> computerPositions = new ArrayList<Integer>();
		
		char[][] gameBoardLayout = {
				{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '}
		};
		
		printGameBoard(gameBoardLayout);
		
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.print("Enter your X placement (1-9): ");
			int playerPosition = scan.nextInt();
			
			while(playerPositions.contains(playerPosition) || computerPositions.contains(playerPositions)) {
			System.out.println("Position taken, enter correct position");
			playerPosition = scan.nextInt();
			}
			placePiece(gameBoardLayout, playerPosition, "player", playerPositions, computerPositions);
			
			String result = checkWinner(playerPositions, computerPositions);
			
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
			
			Random rand = new Random();
			int computerPosition = rand.nextInt(9) + 1;
			while(playerPositions.contains(computerPosition) || computerPositions.contains(computerPosition)) {
				computerPosition = rand.nextInt(9) + 1;
				}
			
			placePiece(gameBoardLayout, computerPosition, "computer", playerPositions, computerPositions);
			printGameBoard(gameBoardLayout);
			
			result = checkWinner(playerPositions, computerPositions);
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
		}
	}
	
	public static void printGameBoard(char[][] gameBoardLayout) {
		
		for(char[] row : gameBoardLayout) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(char[][] gameBoardLayout, int position, String user, ArrayList<Integer>playerPositions, ArrayList<Integer>computerPositions) {
		
		char symbol = ' ';
		
		if(user.equals("player")){
			symbol = 'X';
			playerPositions.add(position);
		}
		else if (user.equals("computer")){
			symbol = 'O';
			computerPositions.add(position);
		}
		
		switch (position) {
			case 1:
				gameBoardLayout[0][0] = symbol;
				break;
			case 2:
				gameBoardLayout[0][2] = symbol;
				break;
			case 3:
				gameBoardLayout[0][4] = symbol;
				break;
			case 4:
				gameBoardLayout[2][0] = symbol;
				break;
			case 5:
				gameBoardLayout[2][2] = symbol;
				break;
			case 6:
				gameBoardLayout[2][4] = symbol;
				break;
			case 7:
				gameBoardLayout[4][0] = symbol;
				break;
			case 8:
				gameBoardLayout[4][2] = symbol;
				break;
			case 9:
				gameBoardLayout[4][4] = symbol;
				break;
			default:
				break;
		}
	}
	
	public static String checkWinner(ArrayList<Integer>playerPositions, ArrayList<Integer>computerPositions) {
		
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List lastRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(3, 5, 7);
		
		List<List> winningConditions = new ArrayList<List>();
		winningConditions.add(topRow);
		winningConditions.add(midRow);
		winningConditions.add(lastRow);
		winningConditions.add(leftCol);
		winningConditions.add(midCol);
		winningConditions.add(rightCol);
		winningConditions.add(cross1);
		winningConditions.add(cross2);
		
		for(List l : winningConditions) {
			if (playerPositions.containsAll(l))
				return("YOU WON!");
			
			else if(computerPositions.containsAll(l))
				return("The computer wins!");
			
			else if(playerPositions.size() + computerPositions.size() == 9)
				return("Draw!");
		}
		return("");
	}
}
