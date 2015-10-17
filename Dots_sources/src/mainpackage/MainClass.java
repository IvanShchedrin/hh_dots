package mainpackage;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {

	public static void main(String [] arg) {
        
		try {
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			String decision = "";
			
			System.out.println("'f' - прочитать данные из файла dots.txt;"
					+ "\n'c' - ввести данные в консоль\n");
			System.out.print(">> ");
			
			decision = in.nextLine();
			
			switch (decision) {
			
				case "c":
				case "с":
					System.out.print("\nВведите количество точек: ");
					int dotsNum = in.nextInt();
					
					if (dotsNum < 2 || dotsNum > 1000000) {
						throw new InputMismatchException();
					}
					
					AdditionalMethods.countNeighbors( AdditionalMethods.getDotsFromConsole(dotsNum) );
					break;
				
				case "f":
					AdditionalMethods.countNeighbors( AdditionalMethods.getDotsFromFile() );
					break;
					
				default:
					System.out.println("\nНеизвестная команда. Перезапустите программу");
					System.exit(0);
				}
			
		} catch (InputMismatchException ex) {
			System.out.println("\nНеобходимо ввести число от 2 до 1000000");
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} catch (NumberFormatException ex) {
			System.out.println(ex.getMessage());
			System.out.println("\nВ файле dots.txt найден лишний пробел или число в неверном формате");
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("\nВ файле dots.txt количество чисел должно быть четным");
		} finally {
			System.exit(1);
		}
		
	}
	
}