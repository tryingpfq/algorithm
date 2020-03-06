package tryingpfq.heap;

import sun.misc.IOUtils;

import java.io.*;
import java.nio.file.Files;

/**
 * 对文件进行分区操作工具
 *
 * @author tryingpfq
 * @date 2020/3/6
 **/
public class PartitionFile {
    public static final PartitionFile INSTANCE = new PartitionFile();

    private static final int MAX_PARTITION_SIZE = 4;

    /**
     * 分区的文件名称
     */
    private static final String PARTITIN_DIR_NAME = "partition";

    /**
     * 中间零时分割文件
     **/
    private static final String SUFFIX_NAME = ".buffer";

    /**
     * 对大文件进行分区
     * @param basePath 文件路径
     * @return 分区后的文件信息
     */
    public PartitionBusi[] getPartition(String basePath) {
        PartitionBusi[] result = new PartitionBusi[MAX_PARTITION_SIZE];

        File baseFile = new File(basePath + File.separator + PARTITIN_DIR_NAME);

        if (baseFile.exists()) {
            File[] rsp = baseFile.listFiles();

            for (File file : rsp) {
                file.delete();
            }
            System.err.println("删除已经存在的文件，结果：" + rsp);
        }

        baseFile.mkdir();

        for (int i = 0; i < MAX_PARTITION_SIZE; i++) {
            PartitionBusi busi = new PartitionBusi();
            busi.setIndex(i);
            String path = baseFile.getPath() + File.separator + i + SUFFIX_NAME;
            busi.setPath(path);

            result[i] = busi;

            //生成需要输出的文件
            try {
                busi.setOutWriter(new FileWriter(path));
                busi.setBufferedWriter(new BufferedWriter(busi.getOutWriter()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return result;

    }

    /**
     * 对问去进行数据的写入操作
     * @param busi 分区
     * @param data 数据
     */
    public void writeData(PartitionBusi busi, String data) {
        try {
            busi.getBufferedWriter().write(data);
            busi.getBufferedWriter().newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将所有的写入文件流关闭
     *
     * @param partitionBusi 分区信息
     */
    public void closeOutput(PartitionBusi[] partitionBusi) {

        for (int i = 0; i < partitionBusi.length; i++) {
            partitionBusi[i].closeOutput();
        }
    }


    /**
     * 进行文件的读取操作
     *
     * @param file 输入文件信息
     */
    public void openReader(PartitionBusi file) {
        try {
            file.setFileReader(new FileReader(file.getPath()));
            file.setBufferedReader(new BufferedReader(file.getFileReader()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 进行文件的关闭操作
     *
     * @param file 文件信息
     */
    public void closeReader(PartitionBusi file) {
        file.closeReader();
    }

}
