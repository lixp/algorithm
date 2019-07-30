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
     * 这里的含义是第k+1小
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

    /**
     * 从大到小排序
     * @param arr
     * @param lo
     * @param high
     * @return
     */
    public static int  partitionReverse(int []arr, int lo, int high) {
        int i = lo, j = high+1;
        while(true){
            while(arr[++i] > arr[lo]) if(i == high) break;
            while(arr[--j] < arr[lo]) if(j == lo) break;
            if(i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, lo, j); //交换旗帜位置
        return j;
    }

    /**
     * 这里的j含义还指第k+1大，需要简单改下代码，同样用递归的方法
     * @param arr
     * @param lo
     * @param high
     * @return
     */
    public static int  findKthBig(int []arr, int lo, int high, int k) {
        int i = lo, j = high+1;
        while(true){
            while(arr[++i] > arr[lo]) if(i == high) break;
            while(arr[--j] < arr[lo]) if(j == lo) break;
            if(i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, lo, j); //交换旗帜位置
        if(j - lo +1 == k) return arr[j];
        //第N大小于k了，还需要在子数组中找几个
        if(j - lo + 1 < k) {
            return findKthBig(arr, j+1, high, k - (j-lo+1));
        } else {
            //第N大大于k了，就在前面的子数组中找即可.
            return findKthBig(arr, lo, j - 1, k);
        }
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

    public static void sortReverse(int arr[], int start, int end) {
        if(end <= start) return ;
        int j = partitionReverse(arr, start, end);
        sortReverse(arr, start , j-1);
        sortReverse(arr, j+1, end);
    }

    public static void main(String[] args) {
        int result[] = new int[]{45,63,82,13,23,24,22,30,48,83};
        // int param[] = new int[]{41};
        //sort(result, 0, result.length - 1);
        sortReverse(result, 0, result.length - 1);
        if(result != null) {
            for (int key : result) {
                System.out.print(key + " ");
            }
        }
        System.out.println("the 5th No is " + findKthBig(result, 0, result.length-1, 5));
    }
}
