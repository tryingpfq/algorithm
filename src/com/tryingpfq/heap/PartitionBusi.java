package tryingpfq.heap;

import java.io.*;

/**
 * 文件切分后的一个分区
 *
 * @author tryingpfq
 * @date 2020/3/6
 **/
public class PartitionBusi {

    /**
     * 当前文件索引编号
     */
    private int index;

    /**
     * 文件路劲
     */
    private String path;

    /**
     * 文件输出流对象
     */
    private FileWriter outWriter;

    /**
     * 缓冲输出流对象
     */
    private BufferedWriter bufferedWriter;

    /**
     * 文件读取
     */
    private FileReader fileReader;

    /**
     * 缓冲读取
     */
    private BufferedReader bufferedReader;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileWriter getOutWriter() {
        return outWriter;
    }

    public void setOutWriter(FileWriter outWriter) {
        this.outWriter = outWriter;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public FileReader getFileReader() {
        return fileReader;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public void closeOutput() {

        try {
            bufferedWriter.close();
            outWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void closeReader() {
        try {
            fileReader.close();
            bufferedReader.close();;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
