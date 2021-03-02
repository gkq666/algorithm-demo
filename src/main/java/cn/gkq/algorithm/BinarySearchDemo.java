package cn.gkq.algorithm;

/**
 * @author GKQ
 * @Classname BinartSearchDemo
 * @Description 二分查找算法
 * @Date 2021/3/2
 */
public class BinarySearchDemo {


    public static void main(String[] args) {
        int[] array = {11, 23, 88, 123, 333, 876};
        System.out.println(recursionBinarySearch(876, 0, array.length - 1, array));
        System.out.println(whileBinarySearch(876, array));
    }

    /**
     * 递归查找
     *
     * @param value
     * @param start
     * @param end
     * @param array
     * @return
     */
    public static int recursionBinarySearch(int value, int start, int end, int[] array) {
        int middleIndex = (start + end) / 2;
        int middleValue = array[middleIndex];
        if (value == middleValue) {
            return middleIndex;
        }
        if (start >= end) {
            return -1;
        }
        if (value < middleValue) {
            return recursionBinarySearch(value, 0, middleIndex - 1, array);
        } else if (value > middleValue) {
            return recursionBinarySearch(value, middleIndex + 1, end, array);
        }
        return -1;
    }

    /**
     * 循环二分查找
     *
     * @param value
     * @param array
     * @return
     */
    public static int whileBinarySearch(int value, int[] array) {
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int middleIndex = (start + end) / 2;
            int middleValue = array[middleIndex];
            if (value == middleValue) {
                return middleIndex;
            }
            if (value < middleValue) {
                end = middleIndex - 1;
            } else if (value > middleValue) {
                start = middleIndex + 1;
            }
        }
        return -1;
    }


}
