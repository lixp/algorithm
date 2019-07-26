package org.framework.fftt;

/**
 * Created with IntelliJ IDEA.
 * User: lixp
 * Date: 2019/7/26
 * Time: 10:17
 * Mail: lixp0915@163.com
 */
public class FindSth {
    public static int arr[] = {1, 3, 5, 7, 0};

    /**
     * 二分查找，前提是要排序
     * @param arr
     * @param key
     * @return
     */
    public static int findIter(int [] arr, int key) {
        if(arr == null || arr.length == 0) {
            return -2;
        }
        int len = arr.length;
        boolean flag = true;
        while(flag){
            int mid = len/2;
            //if(arr[mid])
        }
        //-1 没有找到, -2 输入不能为空，空里边分为两种情况，null||size=0
        return -1;
    }

    /**
     * 选择排序,这里交换过于频繁，优化下
     * @param arr
     * @return
     */
    public static int [] sortArr(int [] arr) {
        if(arr == null || arr.length == 0) {
            return null;
        }
        for(int i = 0; i < arr.length; i++) {
            for(int index = i+1; index < arr.length; index++) {
                //此处需要优化，每次需要交换，浪费
                if(arr[i] > arr[index]){
                    int tmp = arr[i];
                    arr[i] = arr[index];
                    arr[index] = tmp;
                }
            }
        }
        return arr;
    }

    public static int[] sortArrV(int [] arr) {
        if(arr == null || arr.length == 0) {
            return null;
        }
        for(int i = 0; i < arr.length; i++) {
            //树立标杆
            int k = i;
            for(int index = i+1; index < arr.length; index++) {
                //和标杆比较
                if(arr[k] > arr[index]) {
                    k = index;
                }
            }
            if(k != i) {
                int tmp = arr[i];
                arr[i] = arr[k];
                arr[k] = tmp;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int param[] = new int[]{45,63,82,13,23,23,24,22,30,48,49};
        int result[] = sortArrV(param);
        if(result != null) {
            for (int key : result) {
                System.out.print(key + " ");
            }
        }
    }
}
