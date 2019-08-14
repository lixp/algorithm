package org.framework.fftt;

import java.util.Random;

/**
 * 蓄水池算法
 * 大数据集合s，有可能是未知边界
 * 从中取出随机k个元素，结论使每个的概率都相等
 * 方法是用当k>m时，用m/k+i的概率去替换数组的值即可
 * User: lixp
 * Date: 2019/8/14
 * Time: 17:45
 * Mail: lixp0915@163.com
 */
public class Reservoir {

    public int [] source = {2, 4, 6, 8, 10, 15, 18, 20, 23, 25, 27};

    public int M = 3;

    public int [] returnReservior(int M) {
        Random rand = new Random();
        int []m = new int[M];

        for(int i = 0; i < M; i++){
            m[i] = source[i];
        }

        for(int i = M; i < source.length; i++) {
            //随机获取[0 ... i+1]的整数
            int r = rand.nextInt(i+1);
            //命中M区间，替换
            if(r < M) {
                m[r] = source[i];
            }
        }
        return m;
    }

    public static void main(String[] args) {
        Reservoir reservoir = new Reservoir();
        int [] m = reservoir.returnReservior(3);
        for(int i : m) {
            System.out.println(i);
        }
    }
}
