package org.framework.fftt;

/**
 * Created with IntelliJ IDEA.
 * User: lixp
 * Date: 2019/7/30
 * Time: 10:07
 * Mail: lixp0915@163.com
 */
public class QuickSort {

    /**
     * 切分，是为了实现 a[lo .. j-1] < a[j] < a[j+1 .. hi],以便后续，递归排序
     * @param arr 数组
     * @param lo 开始索引值
     * @param high 结束索引值
     * @return
     */
    public static int  partition(int []arr, int lo, int high) {
       int i = lo, j = high+1;
        while(true){
            while(arr[++i] < arr[lo]) if(i == high) break;
            while(arr[--j] > arr[lo]) if(j == lo) break;
            if(i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, lo, j); //交换旗帜位置
        return j;
    }

    public static void swap(int [] arr, int target, int destination) {
        int tmp = arr[target];
        arr[target] = arr[destination];
        arr[destination] = tmp;
    }

    public static void sort(int arr[], int start, int end) {
        if(end <= start) return ;
        int j = partition(arr, start, end);
        sort(arr, start , j-1);
        sort(arr, j+1, end);
    }

    public static void main(String[] args) {
        int result[] = new int[]{45,63,82,13,23,24,22,30,48,83};
        // int param[] = new int[]{41};
        sort(result, 0, result.length - 1);
        if(result != null) {
            for (int key : result) {
                System.out.print(key + " ");
            }
        }
    }
}
