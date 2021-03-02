package cn.gkq.binary;

/**
 * @author GKQ
 * @Classname BinartArithmetic
 * @Description 二进制运算
 *
 *
 */
public class BinaryArithmetic {

    public static void main(String[] args) {
        rightShift();
    }

    /**
     * @Description 无符号右移（>>>） 和 有符号右移（>>）
     * 功能都是将参与运算数字对于的二进制数 向右移动指定的位数 ,补码参与移位运算
     * 区别：>> 参与数字为正数，则在高位补0 负数高位补1
     * >>> 无论参数数字正、负数，都高位补0
     */
    public static void rightShift() {
        Integer num = -4;
        Integer result = num>>>3;
        System.out.println(result);
        System.out.println(Integer.toBinaryString(result));

    }

}
