package tryingpfq.heap;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 堆的应用: 求文件topN 问题
 * 这里是针对超大文件进行关键字TOPN 的统计问题
 * @author tryingpfq
 * @date 2020/3/6
 **/
public class BigFileTopN {

    public static void main(String[] args) {
        BigFileTopN bigFileTopN = new BigFileTopN();
        KeyBusi[] keyBusis = bigFileTopN.topN("datafile/data.txt", 2);
        for (int i = 0  ;i < keyBusis.length; i++) {
            System.out.println(keyBusis[i].toString());
        }

    }
    /**
     * 默认分割符
     */
    private static final String DEFAULT_SPLIT = " ";


    /**
     *
     * @param path 文件路勁
     * @param n 数量
     * @return 前n个关键字
     */
    public KeyBusi[] topN(String path, int n) {
        File curFile = new File(path);
        PartitionBusi[] busis = PartitionFile.INSTANCE.getPartition(curFile.getParent());

        this.fileToPartition(path, busis);

        // 关闭所有分片文件,以写入磁盘
        PartitionFile.INSTANCE.closeOutput(busis);

        // 2,针对每个文件进行求TokN
        List<KeyBusi[]> topkList = this.countTopN(busis, n);

        // 3, 针对求得的TokN再求中总的TopN即为，最后结果的TokN
        KeyBusi[] topBusis = CountTopN.INSTANCE.getTopN(topkList, n);

        return topBusis;
    }

    /**
     * 进行单文件和topn求解
     *
     * @param partis
     * @param n
     * @return
     */
    private List<KeyBusi[]> countTopN(PartitionBusi[] partis, int n) {
        List<KeyBusi[]> list = new ArrayList<>();

        for (int i = 0; i < partis.length; i++) {
            PartitionFile.INSTANCE.openReader(partis[i]);
            // 进行数据读取
            try {
                String line = null;
                while ((line = partis[i].getBufferedReader().readLine()) != null) {
                    CountTopN.INSTANCE.putData(line);
                }
                // 完成一个文件进行一次topN的求解
                list.add(CountTopN.INSTANCE.getTopN(n));

                CountTopN.INSTANCE.dataClear();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 进行文件的关闭操作
                PartitionFile.INSTANCE.closeReader(partis[i]);
            }
        }

        return list;
    }

    /**
     * 读取源文件将数据写入分区文件中
     *
     * @param file 源文件信息
     * @param partitions 分区文件信息
     */
    private void fileToPartition(String file, PartitionBusi[] partitions) {

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        String line = null;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                // 将行数据输出到分片文件信息中
                this.lineToPartition(line, partitions);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 将文件内容输出到分区文件中
     *
     * @param line
     * @param partitions
     */
    private void lineToPartition(String line, PartitionBusi[] partitions) {
        // 分隔字符串
        int index = 0;
        int findIndex;
        line = line.trim();

        while (index < line.length()) {
            // 切分字符串
            if ((findIndex = line.indexOf(DEFAULT_SPLIT, index)) != -1) {
                String key = line.substring(index, findIndex);

                // 按关键证书进行分片操作
                this.keyToPartition(key, partitions);

                index = findIndex + 1;
            } else {
                String key = line.substring(index);

                // 按关键证书进行分片操作
                this.keyToPartition(key, partitions);

                index += line.length();
            }
        }
    }

    /**
     * 将内容输出到分片文件信息中
     *
     * @param key 关键字key
     * @param partitions 分片文件信息
     */
    private void keyToPartition(String key, PartitionBusi[] partitions) {
        int partLength = partitions.length;
        // 计算hash值
        int hashCode = HashCode.getHash(key);
        // 计算得到分片
        int modeVal = hashCode % partLength;
        // 将数据输出到分处文件中
        PartitionFile.INSTANCE.writeData(partitions[modeVal], key);
    }

}
