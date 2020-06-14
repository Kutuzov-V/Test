package com.company;

import java.util.Arrays;

public class SubMulti {
    final static double POSITIVE_INFINITY = Double.POSITIVE_INFINITY;
    final static double NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY;
    //final static double POSITIVE_INFINITY = Double.MAX_VALUE;
    //final static double NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY;
    private int countInterval;
    double[] areaList;

    //получить количество интервалов подмножества
    public int getCountInterval() {
        return countInterval;
    }

    //удалить интервал из множества
    private void remove(int intervalIndex){
        double[] newArealist = new double[areaList.length-2];
        int areaListLength = this.areaList.length;
        int i = 0;
        int j = 0;
        int removeIndexMinElem = (intervalIndex)*2;
        int removeIndexMaxElem = (intervalIndex)*2+1;
        while(i < areaListLength){
            if(i == removeIndexMinElem || i == removeIndexMaxElem){
                i++;
                continue;
            }else {
                newArealist[j] = this.areaList[i];
                j++;
                i++;
            }
        }
        this.areaList = newArealist;
    }

    //добавить готовый интервал в подмножество (данный интервал не пересекается с другими интервалами)
    private void addInterval(double minVal,double maxVal){
        double[] newArealist = new double[areaList.length+2];
        int areaListLength = this.areaList.length;
        int newAreaListLength = newArealist.length;
        int i = 0;
        while(i < areaListLength){
            newArealist[i] = this.areaList[i];
            i++;
        }
        newArealist[i] = minVal;
        newArealist[i+1] = maxVal;
        this.areaList = newArealist;
    }

    //получить минимальное число интервала
    public double getMinOfInterval(int intervalIndex){
        return areaList[intervalIndex*2];
    }

    //получить максимальное число интервала
    public double getMaxOfInterval(int intervalIndex){
        return areaList[intervalIndex * 2 + 1];
    }

    //добавить интревал в подмножество
    public void add(double minVal, double maxVal) throws IntervalValueExeption {
        if(minVal > maxVal){
            throw new IntervalValueExeption();
        }
        double newIntervalMaxVal = maxVal;
        double newIntervalMinVal = minVal;

        if(areaList.length == 0){
            addInterval(minVal,maxVal);
            this.countInterval = this.areaList.length/2;
            return;
        }
        for (int i = 0; i < areaList.length/2; i++){
            double minValInterval = getMinOfInterval(i);
            double maxValInterval = getMaxOfInterval(i);

            //если добавляемый интервал полность входит данный интервал подмножества
            if (newIntervalMinVal >= minValInterval & maxVal <= maxValInterval) {
                return;
            }

            //если добавляемый интервал полностью включает в себя имеющийся
            if(newIntervalMinVal < minValInterval & newIntervalMaxVal > maxValInterval){
                remove(i);
                i--;
                continue;
            }

            //если добавляемый интервал выходит за границы имеющегося слева и является его частью.
            if((newIntervalMaxVal <= maxValInterval & newIntervalMaxVal >= minValInterval) & newIntervalMinVal < minValInterval){
                remove(i);
                i--;
                newIntervalMaxVal = maxValInterval;
                continue;
            }
            //если добавляемый интервал выходит за пределы имеющегося справа и явяется его частью
            if((newIntervalMinVal >= minValInterval & newIntervalMinVal <= maxValInterval) & newIntervalMaxVal > maxValInterval){
                remove(i);
                i--;
                newIntervalMinVal = minValInterval;
                continue;
            }

            //если добавляемый интревал никак не пересекается с имеющимся
            if((newIntervalMinVal < minValInterval & newIntervalMaxVal < minValInterval) || (newIntervalMinVal > maxValInterval & newIntervalMaxVal > maxValInterval)){
                continue;
            }

        }
        addInterval(newIntervalMinVal, newIntervalMaxVal);
        Arrays.sort(areaList);
        countInterval = areaList.length/2;
    }

    //отображение подмножества
    public void showSubMulti(){
        System.out.println("Отображение подмножества: ");
        for(int i = 0; i < this.areaList.length/2;i++ ){
            System.out.println("Интервал " + i);
            System.out.println("    minimum of interval: " + this.areaList[i*2] + "\n"
                    + "    maximum of interval: " + this.areaList[i*2+1]);
        }
    }

    SubMulti(double minVal, double maxVal){
        this.areaList = new double[2];
        this.areaList[0] = minVal;
        this.areaList[1] = maxVal;
        countInterval = this.areaList.length/2;
    }
    SubMulti(){
        this.areaList = new double[0];
        this.countInterval = 0;
    }

}
