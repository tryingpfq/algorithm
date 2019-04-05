package com.tryingpfq.array.base;

/**
 * @Author Tryingpfq
 * @Time 2019/4/5 8:55
 */
public class ArrayBaseOperate {

    public int findMax(int[] array){
        if(array == null || array.length < 1){
            return -1;
        }
        int max = array[0];
        for(int i = 1 ;i< array.length;i++){
            if(array[i] > max){
                max = array[i];
            }
        }
        return max;
    }

    int calcAverate(int[] array){
        if(array == null || array.length < 1){
            return -1;
        }
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum/ array.length;
    }

    int[] copyArray(int[] source){
        int[] dest = new int[source.length];
        for(int i = 0;i < source.length; i ++){
            dest[i] = source[i];
        }
        return dest;
    }

    void reversal(int[] array){
        int n = array.length;
        for (int i = 0; i< n /2;i ++) {
            int temp = array [i];
            array[i] = array[n - i - 1];
            array[n - i - 1] = temp;
        }
    }

    /**
     * N * N 矩阵(方阵)
     * @param a
     * @param b
     */
    public void multMatrix(int[][] a,int[][] b){
       int n = a.length;
       int[][] result = new int[n][n];
       for(int i =0; i< n; i++){
            for(int j =0; j < n;j ++){
                for (int k = 0; k<n ;k ++) {
                     result[i][j] += a[i][k] *a[k][j];
                }
            }
       }
    }


}
