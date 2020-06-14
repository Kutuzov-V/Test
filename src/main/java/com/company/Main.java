package com.company;

public class Main {

    public static void main(String[] args) {
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
    }
}
