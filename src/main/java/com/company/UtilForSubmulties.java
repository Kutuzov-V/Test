package com.company;

public class UtilForSubmulties {

    /**
     * Данный статический метод принимает два подмножества и возвращает подмножество, содержащее их пересечение.
     * Если переданные в метод подмножества не пересекаются, то метод возвращают "null"
     *
     * @param subMultiOne - подмножество вещественых чисел, представленное в виде минимумов и максимумов непрерывных интервалов подмножества
     * @param subMultiTwo - подмножество вещественых чисел, представленное в виде минимумов и максимумов непрерывных интервалов подмножества
     * @return - возвращает объект "result" класса SubMulti, содержащий подмножество, интервалы которого являются пересечениями двух исходных
     * подмножеств
     * @throws IntervalValueExeption исключение возникающее в результате попытки, создать интервал у которого максимальное значение меньше минимального
     */
    private static SubMulti findCross(SubMulti subMultiOne, SubMulti subMultiTwo) throws IntervalValueExeption {
        SubMulti result = null;

        double resultMinVal = 0;
        double resultMaxVal = 0;

        for (int i = 0; i < subMultiOne.getCountInterval(); i++) {
            for (int j = 0; j < subMultiTwo.getCountInterval(); j++) {
                double minValOne = subMultiOne.getMinOfInterval(i);
                double maxValOne = subMultiOne.getMaxOfInterval(i);

                double minValTwo = subMultiTwo.getMinOfInterval(j);
                double maxValTwo = subMultiTwo.getMaxOfInterval(j);

                if (minValOne > maxValTwo) {
                    continue;
                }
                if (maxValOne < minValTwo) {
                    break;
                }
                if (minValOne <= maxValTwo & maxValOne >= minValTwo) {

                    if (result == null) {
                        result = new SubMulti();
                    }

                    if (minValOne <= minValTwo) {
                        resultMinVal = minValTwo;

                    } else {
                        resultMinVal = minValOne;
                    }

                    if (maxValOne <= maxValTwo) {
                        resultMaxVal = maxValOne;
                    } else {
                        resultMaxVal = maxValTwo;
                    }
                    result.add(resultMinVal, resultMaxVal);

                }
            }
        }
        return result;
    }

    /**
     * Данный статический метод принимает список подмножеств и находит их пересечение, если таковое имеется. Пересечение
     * представленно в видео подмножества (объект клааса SubMulti). Данное подмножество содержит интервалы, входящие во
     * все подмножества, определённые в массиве подмножеств. Данный метод в своей основе использует метод "findCross",
     * попарно сравнивая подмножества.Сначала сравнивается первые два подмножества, а затем их результат сравнивается со
     * следующим подмножеством и так до последнего подмножества.
     *
     * @param subMultiesList - массив, содержащий список подмножеств (массив объектов типа SubMulti, пересечения которых
     *                       нужно найти.
     * @return - возвращает объект "result" класса SubMulti, содержащий подмножество, интервалы которого являются
     * пересечениями двух исходных подмножеств.
     * @throws IntervalValueExeption исключение возникающее в результате попытки, создать интервал у которого
     *                               максимальное значение меньше минимального.
     */
    public static SubMulti getCross(SubMulti[] subMultiesList) throws IntervalValueExeption {
        SubMulti result = null;
        if (subMultiesList.length >= 2) {
            result = findCross(subMultiesList[0], subMultiesList[1]);
        } else {
            System.out.println("Нет подмножеств для определения отрезков пересечений...");
        }
        if (result == null) {
            return null;
        }

        for (int i = 2; i < subMultiesList.length; i++) {
            if (result == null) {
                break;
            }
            result = findCross(result, subMultiesList[i]);
        }
        return result;
    }

    /**
     * Для любого заданного числа x (double point) возвращает число, принадлежащее переданному в аргумент подмножеству,
     * максимально близкое к x (или само x, если оно принадлежит пересечению подмножеств).
     *
     * @param subMulti - подмножество
     * @param point     - число x, относительно которого находится максимально близкое число из ближайшего интервала
     *                  заданного в аргументе подмножества
     * @return - возвращает число типа double, принадлежащее переданному в аргумент подмножеству,максимально близкое к x
     * (или само x, если оно принадлежит пересечению подмножеств).
     */

    public static double getTestPoint(SubMulti subMulti, double point) {

        for (int i = 0; i < subMulti.getCountInterval(); i++) {

            double minCurrInterval = subMulti.getMinOfInterval(i);
            double maxCurInterval = subMulti.getMaxOfInterval(i);

            if (i == 0) {
                if (point < minCurrInterval) {
                    return minCurrInterval;
                } else if (point > maxCurInterval) {
                    if (i == subMulti.getCountInterval() - 1) {
                        return maxCurInterval;
                    } else {
                        continue;
                    }
                } else {
                    return point;
                }
            } else {

                double maxPrevInterval = subMulti.getMaxOfInterval(i - 1);

                if (point < minCurrInterval) {
                    if ((minCurrInterval - point) > (point - maxPrevInterval)) {
                        return maxPrevInterval;
                    } else {
                        return minCurrInterval;
                    }
                } else if (point >= minCurrInterval && point <= maxCurInterval) {
                    return point;
                } else {
                    if (i == subMulti.getCountInterval() - 1) {
                        return maxCurInterval;
                    } else {
                        continue;
                    }
                }

            }

        }
        return Double.NEGATIVE_INFINITY;
    }
}
