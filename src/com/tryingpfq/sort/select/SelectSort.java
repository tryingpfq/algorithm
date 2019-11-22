package tryingpfq.sort.select;

/**
 * 这个可以说是交换排序吗
 */
public class SelectSort {

    public static void insert(int[] a) {
        int n = a.length;
        if(n <= 1)
            return;

        for (int i = 0; i < n; i++) {
            int k = i;
            for(int j = i+1 ; j < n ; j ++){
                if(a[k] > a[j]){
                    k = j;
                }
            }
            if(k != i){
                //交换
                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
    }


    public static void main(String[] args) {
        int[] data = new int[]{3,2,15,32,5,9,54};
        insert(data);
        System.out.println(data);
    }
}
