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
			
			System.out.println("'f' - ��������� ������ �� ����� dots.txt;"
					+ "\n'c' - ������ ������ � �������\n");
			System.out.print(">> ");
			
			decision = in.nextLine();
			
			switch (decision) {
			
				case "c":
				case "�":
					System.out.print("\n������� ���������� �����: ");
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
					System.out.println("\n����������� �������. ������������� ���������");
					System.exit(0);
				}
			
		} catch (InputMismatchException ex) {
			System.out.println("\n���������� ������ ����� �� 2 �� 1000000");
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} catch (NumberFormatException ex) {
			System.out.println(ex.getMessage());
			System.out.println("\n� ����� dots.txt ������ ������ ������ ��� ����� � �������� �������");
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("\n� ����� dots.txt ���������� ����� ������ ���� ������");
		} finally {
			System.exit(1);
		}
		
	}
	
}