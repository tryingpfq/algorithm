package tryingpfq.heap;

/**
 * 关键字信息
 *
 * @author tryingpfq
 * @date 2020/3/6
 **/
public class KeyBusi {

    /**
     * 关键字 这里可以用hashCode
     */
    private String key;

    /**
     * 该关键字统计出现的次数
     */
    private int countNum;


    public KeyBusi(String key, int countNum) {
        this.key = key;
        this.countNum = countNum;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCountNum() {
        return countNum;
    }

    @Override
    public String toString() {
        return "KeyBusi{" +
                "key='" + key + '\'' +
                ", countNum=" + countNum +
                '}';
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }
}
