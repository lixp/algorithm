package org.framework.fftt;

/**
 * Created with IntelliJ IDEA.
 * User: lixp
 * Date: 2019/7/31
 * Time: 9:06
 * Mail: lixp0915@163.com
 */
public class HeapSort {
    /**
     * 堆排序的下沉排序
     * @param arr
     * @param k
     * @param N
     */
    private static void sink(int []arr, int k, int N){
        while(2*k <= N) {
            int j = 2*k;
            if(j < N && arr[j] < arr[j+1]) j++;
            if(arr[k] > arr[j]) break;
            swap(arr, k, j);
            k = j;
        }
    }

    private static void swap(int []arr, int source, int target) {
        int tmp = arr[source];
        arr[source] = arr[target];
        arr[target] = tmp;
    }

    private static void sort(int arr[]) {
        //构建堆
        int N = arr.length-1;
        for(int k = N/2; k >= 1; k--) {
            sink(arr, k ,N);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i+":" + arr[i] + " ");
        }
        while(N > 1) {
            swap(arr, 1, N--);
            sink(arr, 1, N);
        }
    }

    public static void main(String[] args) {
        int result[] = new int[]{0,45,63,82,13,23,24,22,30,48,83};
        // int param[] = new int[]{41};
        //sort(result, 0, result.length - 1);
        sort(result);
        if(result != null) {
            for (int key : result) {
                System.out.print(key + " ");
            }
        }
    }
}
