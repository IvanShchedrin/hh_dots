package mainpackage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class AdditionalMethods {
	
	/**
	 * @param size ���������� �����
	 * @return ���������� ������ �����, ���������� �������� �������� ���������� � �����������:<br>
	 * 1 - �������� �� ��� X;<br>
	 * 2 - �������� �� ��� Y.<br>
	 * ���� �������������� ����� �������.
	 */
	public static int [][] getDotsFromConsole(int size) {
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int [][] dots = new int [size][2];
		
		try {
			for (int i = 0; i < size; i++) {
				System.out.print("\nX" + (i + 1) + ": ");
				dots[i][0] = in.nextInt();
				System.out.print("Y" + (i + 1) + ": ");
				dots[i][1] = in.nextInt();
			}
		} catch (Exception ex) {
			System.out.println("\n���������� ������ ����� ����� � ��������� integer");
			System.exit(0);
		}
		
		return dots;
		
	}
	
	/**
	 * @return ���������� ������ �����, ���������� �������� �������� ���������� � �����������:<br>
	 * 1 - �������� �� ��� X;<br>
	 * 2 - �������� �� ��� Y.<br>
	 * ������ ������� �� ����� dots.txt � ����� �������.
	 * @throws IOException � ������, ���� ���� �� ������.
	 */
	public static int [][] getDotsFromFile() throws IOException, NumberFormatException, ArrayIndexOutOfBoundsException {
		
		File file = new File("dots.txt");
        FileInputStream fis = new FileInputStream(file);
		
		byte[] data = new byte[(int) file.length()];
		
        fis.read(data);
        fis.close();
        
        // ���������� �� ����� ���������� ����� ��������� � ������ �����
        String[] numbers = new String(data, "UTF-8").replace("\n", "").replace("\r", " ").split(" ");
		
        // �� ������ ����� �� ��� ����������, �������������, ����� ���������� ��������� �� 2
        int [][] dots = new int [numbers.length / 2][2];
        
        // ����������� ������ ������ ���������� �� ���� X � Y � ������ � ������ � Int`�
        for (int i = 0; i < numbers.length; i += 2) {
        	dots[i / 2][0] = Integer.parseInt(numbers[i]);
        	dots[i / 2][1] = Integer.parseInt(numbers[i + 1]);
        }
		
		return dots;
		
	}
	
	/**
	 * @param dots ������ ��������� �����
	 * ������������ ������ � ���������� ������� ��� ������ ����� � ������� ��������� � �������
	 */
	public static void countNeighbors(int [][] dots) {
		
		// ������ �������� �����. ��������� ��� ������ ����� � ������������, ����� �����, ����� ����� ������ � 
		// ������ ������ �����.
		float [] radius = new float [dots.length];
		// ������ ������ ��� ������� �����
		float cachedRad;
		
		int i = 0;
		int q = 1;
		int dotCount = 0;
		
		// ���� ������ ����� -1, ��� ������, ��� ��� ������ ����� ��-��������� � ����� ����
		radius[0] = -1.0f;

		System.out.println("\n����� � ����������� ������� � ��������:\n");
		
		// �������� �� ������� � ��� ������ ����� ��������� ������. ��������� ����������� ������. �������, �������
		// ����� ������ � ����� ����� � �������� �������� �������
		for ( ; i < dots.length; i++) {
			
			cachedRad = getRadius(dots[i], dots[q]);

			// ��������� ������� �� ���� �����, ������� �� ���������, ��-��������� � �������
			for ( ; q < dots.length; q++) {
				// ���� ������ �� �� �����, ��-��������� � ������� ������� �������, ������ = -1
				if (q == i) {
					radius[q] = -1.0f;
					continue;
				}
				
				// ���� ����������� ������ ������ �������� �����������, �� �������������� ����������
				if (( radius[q] = getRadius(dots[i], dots[q]) ) < cachedRad) {
					cachedRad = radius[q];
				}
			}
			
			// ���� ���������� ������ ����� 0, ��� ������, ��� �������� 2 ��� ����� ����� � ����������� ������������
			// ������� ����� ����� �������� ���� �� ����� �, ��� ���������, ������ ����� ����� ����� 0. �����
			// ����� ����� ������� ��������
			if (cachedRad == 0.0f) {
				for (q = 0; q < dots.length; q++) {
					if (radius[q] == 0.0f) {
						dotCount ++;
					}
				}
				
				// ������� ���� � ����� � ������� ��������, �������� ���������� � ��������� � ��������� �����
				System.out.println( getResultString(dots[i], dotCount, cachedRad) );
				dotCount = 0;
				q = 0;
				continue;
			}
			
			// ������ ����� - ������� ���������� �� ��������� ����� (�� �������)
			cachedRad *= 2.0f;
			
			// � ����� ������� ���������� �� �����. ���� ��� ������ ��� ����� �������, �� �������������� �������
			for (q = 0; q < dots.length; q++) {
				if (q == i) {
					continue;
				}
				
				if (radius[q] <= cachedRad) {
					dotCount ++;
				}
			}
			
			System.out.println( getResultString(dots[i], dotCount, cachedRad) );
			
			q = 0;
			dotCount = 0;
		}
		
	}
	
	/**
	 * @param dot1 ������ �����
	 * @param dot2 ������ �����
	 * @return ���������� ���������� �� ������ �� ������ �����
	 */
	public static float getRadius(int[] dot1, int[] dot2) {
		
		return (float) Math.sqrt( (dot2[0]-dot1[0])*(dot2[0]-dot1[0]) + (dot2[1]-dot1[1])*(dot2[1]-dot1[1]) );
	
	}
	
	/**
	 * @param dot ����� � ������������
	 * @param neighbors ���������� �������
	 * @param radius ������ �����
	 * @return ���������� ������ ��� ������ � �������.<br>
	 * ������: "(dot[0], dot[1]) - �������: neighbors; ������: radius"
	 */
	public static String getResultString(int [] dot, int neighbors, float radius) {
		
		return "(" + dot[0] + ", " + dot[1] + ") - �������: " + neighbors + "; ������: " + radius;
		
	}
	
}