package tryingpfq.sort;

public class MergeSort {

    private static void mergeSort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }
    }

    private static void mergeSortInternally(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }

        // 取p到r之间的中间位置q,防止（p+r）的和超过int类型最大值
        int q = p + (r - p)/2;
        mergeSortInternally(a, p, q);
        mergeSortInternally(a, q, r);

        merge(a, p, q, r);
    }

    private static void merge(int[] a, int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int k = 0;
        int[] temp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if(a[i] <= a[j]){
                temp[k++] = a[i++];
            }else{
                temp[k++] = a[j++];
            }
        }
        int start = i;
        int end = q;
        if (j <= r) {
            start = j;
            end = r;
        }
        while (start <= end) {
            temp[k++] = a[start++];
        }

        // 将tmp中的数组拷贝回a[p...r]
        for (i = 0; i <= r-p; ++i) {
            a[p+i] = temp[i];
        }
    }


    //快速排序
    public static void quickSort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }
        quickSortInternally(a,0,n-1);
    }


    private static void quickSortInternally(int[] a, int p, int r) {
        if (p >= r) return;

        int q = partition(a, p, r); // 获取分区点
        quickSortInternally(a, p, q-1);
        quickSortInternally(a, q+1, r);
    }

    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for(int j = p ; j < r;j ++){
            if(a[j] < pivot){
                if (i == j) {
                    i++;
                }else{
                    int temp = a[i];
                    a[i++] = a[j];
                    a[j] = temp;
                }
            }
        }

        int temp = a[i];
        a[i] = pivot;
        a[r] = temp;

        return i;
    }
}
