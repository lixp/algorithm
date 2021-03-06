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
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int findRecursion(int [] arr, int left, int right, int key){
        int mid = left + (right - left)/2;
        if(arr[mid] == key) return mid;
        if(left >= right) return -2;
        if(arr[mid] > key) {
            return findRecursion(arr, left, mid-1, key);
        } else{
            return findRecursion(arr, mid+1, right, key);
        }
    }

    /**
     * 迭代二分查找，前提是要排序
     * @param arr
     * @param key
     * @return
     */
    public static int findIter(int [] arr, int key) {
        if(arr == null || arr.length == 0) {
            return -2;
        }

        int left = 0, right = arr.length-1, mid;
        while(left <= right){

             mid = left + (right - left)/2;

            if(arr[mid] == key) {
                return mid;
            }

            if(arr[mid] < key) {
                left = mid + 1;
            }

            if(arr[mid] > key) {
                right = mid - 1;
            }

           // System.out.println("left is " + left + ",right is " + right + ",mid is " + mid);
        }

        //-1 没有找到, -2 输入不能为空，空里边分为两种情况，null||size=0
        return -2;
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
        int param[] = new int[]{45,63,82,13,23,24,22,30,48,83};
       // int param[] = new int[]{41};
        int result[] = sortArrV(param);
        if(result != null) {
            for (int key : result) {
                System.out.print(key + " ");
            }
        }
        int cursor = findRecursion(result, 0, result.length-1,41);
        if(cursor == -2) {
            System.out.print("can not find");
        }else {
            System.out.print("find 23 index is " + cursor + ",value is " + result[cursor]);
        }
    }
}
