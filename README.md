# Точки
### Задание
###### Описание
Даны N точек на плоскости (для простоты можно считать, что у всех точек целочисленные координаты). Назовём расстояние от точки A до ближайшей к ней точки B "радиусом" точки A. "Соседями" точки A будем называть все точки, лежащие в пределах двойного радиуса от неё включительно (кроме самой точки A).
Для каждой точки из заданного набора определите её радиус и количество соседей.
###### Пример входных данных
```
5 2
1 3
-2 0
1 -5
10 2
```
###### Пример выходных данных
```
(5, 2) - соседей: 4; радиус: 8.246211
(1, 3) - соседей: 3; радиус: 8.246211
(-2, 0) - соседей: 3; радиус: 8.485281
(1, -5) - соседей: 4; радиус: 11.661903
(10, 2) - соседей: 2; радиус: 10.0
```
### Решение
Решаем задачу перебором. Высчитыванием расстояния от текущей точки до остальных по формуле ```r = sqrt( (x2−x1)^2+(y2−y1)^2 )```, где x1, y1, x2, y2 - координаты точек. Вместе с этим для текущей точки находим минимальное расстояние (радиус). После того, как все расстояния получены и найден радиус, находим соседние точки (лежащие в пределах двойного радиуса). Количество соседей и двойной радиус выводим в консоль и переходим к следующей точке, обнулив найденные расстояния и радиус.
В случае, если координаты точек совпадают, будем считать, что точки лежат "друг на друге". Их радиус будет равен 0 и эти точки будут являться соседями по-отношению друг к другу.
###### Ввод данных
В программе предусмотрено чтение данных из файла dots.txt и ввод через консоль. Формат данных в файле: 
```
<x1><пробел><y1><пробел>
<x2><пробел><y2><пробел>
...
```
Возможные значения координат: ```+/- 2^(14) - 1```
### Запуск проекта
###### В Eclipse
Склонировать или скачать репозиторий в ZIP, разархивировать, импортировать папку ```Dots_sources``` в Eclipse (File > Import > Existing Projects into Workspace). Запускать из класса MainClass.
###### Jar файл
Склонировать или скачать репозиторий в ZIP, разархивировать, в папке ```Dots_runnable``` запустить файл run.bat.
