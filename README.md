# Test
Проект состоит из 4 основных из 4 основных классов. Для выполнения задачи непосредственно использовать необходимо два класса: SubMulty - описывает множество в виде массива пары чисел (максимального и минимального значения); UtilForSubmulties - статический класс утилита для нахождения подмножества, которое содержит интервалы, являющиеся пересечением списка подмножеств.
Для работы приложения необходимо сначала создать новый класс SubMulti. Для этого в классе реализовано 2 конструктора. Один принимает минимальное и максимальное значение первого интервала и создаёт подмножество, содержащее лишь один интервал с переданными ранее в конструктор минимальным и максимальным значениями. Второй конструктор без входных параметров лишь создает объект, инициализируя параметры, но не добавляя параметры.
Пример создания подмножества:
```java
SubMulti subMulti = new SubMulti(0.34,10);
SubMulti emptySubMulti = new SubMulti();
```
Для реализации подмножеств содержащих несколько интервалов необходимо последовательно добавить эти интервалы с помощью метода .add, определенного в классе SubMulti:
```java
SubMulti subMulti = new SubMulti();
subMulti.add(0.1, 4.34);
subMulti.add(5.75, 9.65);
subMulti.add(-12.3, -5);
```
При этом метод обеспечивает добавление интервалов таким образом, чтобы сформированное множество содержало только независимые интервалы. Если в процессе добавления нового интервала, окажется, что он является частью уже одного из имеющегося, то границы существующего расширятся в зависимости от разницы между вводимым и меющимся интервалов подмножества. Если вводимый интервал объединяет два или несколько имеющихся интервалов подмножества, то метод объединит их в один.
```java
SubMulti subMulti = new SubMulti();
    		subMulti.add(1.1, 2.3);
    		subMulti.add(4.1,5.67);
    		subMulti.add(2.20,2.5);
    		subMulti.showSubMulti();
```
Для отображения помножества в классе SubMulti реализован метод showSubMulti(), который позволяет вывести в консоль подмножество в виде списка интервалов. Интервалы в свою очередь представлены парой чисел - максимальное значени и минимальное значение. Результат выполение вышеизложенного кода будет следующим:
Отображение подмножества: 

Интервал 0
    minimum of interval: 1.1
    maximum of interval: 2.5
Интервал 1
    minimum of interval: 4.1
    maximum of interval: 5.67

Из вывода видно, что интервал (1.1, 2.3) при добавлении в подмножесво нового интервала (2.20, 2.25) расширился до (1.1, 2.25).
Чтобы избежать случая при котором пользователь вводит интервал некорректно - минимальное значение больше максимального (например (3.4, 0)). В проекте реализован класс, расширающий класс Exception, выдающий ошибку при выполнении неверного ввода параметров интервала. Данный класс носит название IntervalValueExeption.

Для того, чтобы найти пересечение нескольких подмножеств в проекте предусмотрен класс UtilForSubmulties, в котором определён открытый статический метод getCross(SubMulti[] subMultiesList). Он принимает в качестве аргумента массив из подмножеств и возвращает подмножество, которое содержит все интервалы, которые являются пересечением всех переданных в аргумент подмножетсв. Если же интервала, который принадлежал бы всем подмножеством не существует для данного списка подмножеств, то метод возвращает null.
Пример нахождения пересечения списка подмножеств:
```java
try{
	    SubMulti[] subMultiLst = new SubMulti[5];

	    for( int i = 0; i < subMultiLst.length; i++ ){
	    	subMultiLst[i] = new SubMulti();
		}

		subMultiLst[0].add(SubMulti.NEGATIVE_INFINITY,5.2);
		subMultiLst[0].add(5.25, 7.1);
		subMultiLst[0].add(8.2,8.25);
		subMultiLst[0].add(10.5,SubMulti.POSITIVE_INFINITY);

		subMultiLst[1].add(-3,-1);
		subMultiLst[1].add(0,5.28);
		subMultiLst[1].add(7.15,7.3);
		subMultiLst[1].add(8.4,15.4);

		subMultiLst[2].add(-3,5.21);
		subMultiLst[2].add(5.23, 5.30);
		subMultiLst[2].add(6.5,8.21);
		subMultiLst[2].add(10.6, 18.1);

		subMultiLst[3].add(-3.05,5.31);
		subMultiLst[3].add(7.16,15.5);

		subMultiLst[4].add(SubMulti.NEGATIVE_INFINITY,6.9);
		subMultiLst[4].add(7.19,SubMulti.POSITIVE_INFINITY);

		SubMulti crossArea = UtilForSubmulties.getCross(subMultiLst);
		crossArea.showSubMulti();
		
    }catch(IntervalValueExeption ive){
	    System.out.println(ive);
    }
```

Результат выполнения данного фрагмента кода в консоли:
Отображение подмножества: 
Интервал 0
    minimum of interval: -3.0
    maximum of interval: -1.0
Интервал 1
    minimum of interval: 0.0
    maximum of interval: 5.2
Интервал 2
    minimum of interval: 5.25
    maximum of interval: 5.28
Интервал 3
    minimum of interval: 10.6
    maximum of interval: 15.4

В консоле отображено подмножество, являющееся пересечением массива подмножеств.

Метод  getCross(SubMulti[] subMultiesList)  позволяет для любого заданного числа x (double point) найти число, принадлежащее некоторому подмножеству максимально близкое к x (или само x, если оно принадлежит пересечению подмножеств).Пример использования:
```java
try{
	    SubMulti[] subMultiLst = new SubMulti[5];

	    for( int i = 0; i < subMultiLst.length; i++ ){
	    	subMultiLst[i] = new SubMulti();
		}

		subMultiLst[0].add(SubMulti.NEGATIVE_INFINITY,5.2);
		subMultiLst[0].add(5.25, 7.1);
		subMultiLst[0].add(8.2,8.25);
		subMultiLst[0].add(10.5,SubMulti.POSITIVE_INFINITY);

		subMultiLst[1].add(-3,-1);
		subMultiLst[1].add(0,5.28);
		subMultiLst[1].add(7.15,7.3);
		subMultiLst[1].add(8.4,15.4);

		subMultiLst[2].add(-3,5.21);
		subMultiLst[2].add(5.23, 5.30);
		subMultiLst[2].add(6.5,8.21);
		subMultiLst[2].add(10.6, 18.1);

		subMultiLst[3].add(-3.05,5.31);
		subMultiLst[3].add(7.16,15.5);

		subMultiLst[4].add(SubMulti.NEGATIVE_INFINITY,6.9);
		subMultiLst[4].add(7.19,SubMulti.POSITIVE_INFINITY);

		SubMulti crossArea = UtilForSubmulties.getCross(subMultiLst);

		crossArea.showSubMulti();

		double x = 5.24;
		double result = UtilForSubmulties.getTestPoint(crossArea,x);
		System.out.println("\n" + "Наиболее близкое к заданному значению x= " + x + " из области пересечения является точка со значением: " + result);

		x = 5.26;
		result = UtilForSubmulties.getTestPoint(crossArea,x);
		System.out.println("\n" + "Наиболее близкое к заданному значению x =" + x +" из области пересечения является точка со значением: " + result);

		x = -8;
		result = UtilForSubmulties.getTestPoint(crossArea,x);
		System.out.println("\n" + "Наиболее близкое к заданному значению x= " + x + " из области пересечения является точка со значением: " + result);

		x = 7;
		result = UtilForSubmulties.getTestPoint(crossArea,x);
		System.out.println("\n" + "Наиболее близкое к заданному значению x= " + x + " из области пересечения является точка со значением: " + result);

		x = 9;
		result = UtilForSubmulties.getTestPoint(crossArea,x);
		System.out.println("\n" + "Наиболее близкое к заданному значению x= " + x + " из области пересечения является точка со значением: " + result);

    }catch(IntervalValueExeption ive){
	    System.out.println(ive);
    }
```

В данном примере создается массив подмножеств, находится их пересечение. Из полученного пересечения находится число максимально близкое к интервалам подмножеств. Рассматривается несколько различных случаев. Результат выводится на консоль.

    Отображение подмножества: 
        Интервал 0
            minimum of interval: -3.0
            maximum of interval: -1.0
        Интервал 1
            minimum of interval: 0.0
            maximum of interval: 5.2
        Интервал 2
            minimum of interval: 5.25
            maximum of interval: 5.28
        Интервал 3
            minimum of interval: 10.6
            maximum of interval: 15.4

    Наиболее близкое к заданному значению x= 5.24 из области пересечения является точка со значением: 5.25

    Наиболее близкое к заданному значению x =5.26 из области пересечения является точка со значением: 5.26  
    Наиболее близкое к заданному значению x= -8.0 из области пересечения является точка со значением: -3.0

    Наиболее близкое к заданному значению x= 7.0 из области пересечения является точка со значением: 5.28

    Наиболее близкое к заданному значению x= 9.0 из области пересечения является точка со значением: 10.6
    
Данный пример можно запустить и просмотреть в классе Main, методе main проекта, который является основной точкой входа в приложение.



