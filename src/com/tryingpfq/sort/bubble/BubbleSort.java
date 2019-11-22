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
}
