package mainpackage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class AdditionalMethods {
	
	/**
	 * @param size Количество точек
	 * @return Возвращает массив точек, элементами которого являются подмассивы с параметрами:<br>
	 * 1 - смещение по оси X;<br>
	 * 2 - смещение по оси Y.<br>
	 * Ввод осуществляется через консоль.
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
			System.out.println("\nНеобходимо ввести целое числа в диапазоне integer");
			System.exit(0);
		}
		
		return dots;
		
	}
	
	/**
	 * @return Возвращает массив точек, элементами которого являются подмассивы с параметрами:<br>
	 * 1 - смещение по оси X;<br>
	 * 2 - смещение по оси Y.<br>
	 * Данные берутся из файла dots.txt в корне проекта.
	 * @throws IOException в случае, если файл не найден.
	 */
	public static int [][] getDotsFromFile() throws IOException, NumberFormatException, ArrayIndexOutOfBoundsException {
		
		File file = new File("dots.txt");
        FileInputStream fis = new FileInputStream(file);
		
		byte[] data = new byte[(int) file.length()];
		
        fis.read(data);
        fis.close();
        
        // Полученные из файла координаты точек сохраняем в массив строк
        String[] numbers = new String(data, "UTF-8").replace("\n", "").replace("\r", " ").split(" ");
		
        // На каждую точку по две координаты, следовательно, делим количество координат на 2
        int [][] dots = new int [numbers.length / 2][2];
        
        // Проставляем каждую вторую координату по осям X и Y в массив и парсим к Int`у
        for (int i = 0; i < numbers.length; i += 2) {
        	dots[i / 2][0] = Integer.parseInt(numbers[i]);
        	dots[i / 2][1] = Integer.parseInt(numbers[i + 1]);
        }
		
		return dots;
		
	}
	
	/**
	 * @param dots Массив координат точек
	 * Подсчитывает радиус и количество соседей для каждой точки и выводит результат в консоль
	 */
	public static void countNeighbors(int [][] dots) {
		
		// Массив радиусов точек. Создается для каждой точки и используется, чтобы найти, какие точки входят в 
		// радиус данной точки.
		float [] radius = new float [dots.length];
		// Хранит радиус для текущей точки
		float cachedRad;
		
		int i = 0;
		int q = 1;
		int dotCount = 0;
		
		// Если радиус равен -1, это значит, что это радиус точки по-отношению к самой себе
		radius[0] = -1.0f;

		System.out.println("\nТочки с количеством соседей и радиусом:\n");
		
		// Проходим по массиву и для каждой точки сохраняем радиус. Сохраняем минимальный радиус. Смотрим, сколько
		// точек входят в точек лежит в пределах двойного радиуса
		for ( ; i < dots.length; i++) {
			
			cachedRad = getRadius(dots[i], dots[q]);

			// Вычисляем радиусы до всех точек, лежащих на плоскости, по-отношению к текущей
			for ( ; q < dots.length; q++) {
				// Если попали на ту точку, по-отношению к которой считаем радиусы, радиус = -1
				if (q == i) {
					radius[q] = -1.0f;
					continue;
				}
				
				// Если вычисленный радиус меньше текущего наименьшего, то нереопределяем наименьший
				if (( radius[q] = getRadius(dots[i], dots[q]) ) < cachedRad) {
					cachedRad = radius[q];
				}
			}
			
			// Если наименьший радиус равен 0, это значит, что попались 2 или более точек с одинаковыми координатами
			// Считаем такие точки лежащими друг на друге и, как следствие, радиус таких точек равен 0. Такие
			// точки будем считать соседями
			if (cachedRad == 0.0f) {
				for (q = 0; q < dots.length; q++) {
					if (radius[q] == 0.0f) {
						dotCount ++;
					}
				}
				
				// Выводим инфу о точке с нулевым радиусом, обнуляем переменные и переходим к следующей точке
				System.out.println( getResultString(dots[i], dotCount, cachedRad) );
				dotCount = 0;
				q = 0;
				continue;
			}
			
			// Радиус точки - двойное расстояние до ближайшей точки (из условия)
			cachedRad *= 2.0f;
			
			// В цикле смотрим расстояния до точек. Есди оно меньше или равно радиусу, то инкрементируем счетчик
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
	 * @param dot1 Первая точка
	 * @param dot2 Вторая точка
	 * @return Возвращает расстояние от первой до второй точки
	 */
	public static float getRadius(int[] dot1, int[] dot2) {
		
		return (float) Math.sqrt( (dot2[0]-dot1[0])*(dot2[0]-dot1[0]) + (dot2[1]-dot1[1])*(dot2[1]-dot1[1]) );
	
	}
	
	/**
	 * @param dot Точка с координатами
	 * @param neighbors Количество соседей
	 * @param radius Радиус точки
	 * @return Возвращает строку для вывода в консоль.<br>
	 * Формат: "(dot[0], dot[1]) - соседей: neighbors; радиус: radius"
	 */
	public static String getResultString(int [] dot, int neighbors, float radius) {
		
		return "(" + dot[0] + ", " + dot[1] + ") - соседей: " + neighbors + "; радиус: " + radius;
		
	}
	
}