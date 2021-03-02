package cn.gkq.algorithm;

import java.util.Arrays;

/**
 * @author GKQ
 * @Classname BubblingSortDemo
 * @Description 冒泡排序
 * @Date 2021/3/2
 */
public class BubblingSortDemo {

    public static void main(String[] args) {
        int[] array = {2, 33, 1, 12, 123, 32, 66, 7};
        bubblingSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void bubblingSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if(array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

}
