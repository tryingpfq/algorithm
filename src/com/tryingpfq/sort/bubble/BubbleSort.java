package tryingpfq.sort.bubble;

public class BubbleSort {

    public void bubble(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j+1]; a[j+1] = tmp;
                    flag = true; // 表示有数据交换
                }
            }
            if (!flag) {
                break;
            }
        }
    }


    public static void bubbleSort2(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }
        int lastExchange = 0;
        int sortBoder = n-1;
        boolean flag;
        for (int i = 0; i < n; i++) {
            flag = false;
            for (int j = 0; j < sortBoder; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j+1] = temp;
                    flag = true;
                    lastExchange = j;
                }
            }
            sortBoder = lastExchange;
            if (!flag) {
                break;
            }
        }
    }


    public static void insert(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }
        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i-1;
            for (; j >= 0; --j) {
                if (a[j] > a[j + 1]) {
                    a[j +1] = a[j];
                }else{
                    break;
                }
            }
            a[j + 1] = value;
        }
    }
}
