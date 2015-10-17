# Точки
### Задание
##### Описание
Даны N точек на плоскости (для простоты можно считать, что у всех точек целочисленные координаты). Назовём расстояние от точки A до ближайшей к ней точки B "радиусом" точки A. "Соседями" точки A будем называть все точки, лежащие в пределах двойного радиуса от неё включительно (кроме самой точки A).
Для каждой точки из заданного набора определите её радиус и количество соседей.
##### Пример входных данных
```
5 2
1 3
-2 0
1 -5
10 2
```
##### Пример выходных данных
```
(5, 2) - соседей: 4; радиус: 8.246211
(1, 3) - соседей: 3; радиус: 8.246211
(-2, 0) - соседей: 3; радиус: 8.485281
(1, -5) - соседей: 4; радиус: 11.661903
(10, 2) - соседей: 2; радиус: 10.0
```
