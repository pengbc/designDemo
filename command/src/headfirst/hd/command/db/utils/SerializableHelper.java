package headfirst.hd.command.db.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializableHelper {

    public static String FILE_NAME = "a.txt";

    private static class SingletonHolder {
        private static SerializableHelper instance = new SerializableHelper();

        private static ObjectOutputStream oos;

        static {
            try {
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                oos = new ObjectOutputStream(fos);
            } catch (IOException e) {
                //这里简单处理
                e.printStackTrace();
            }
        }

    }

    private SerializableHelper() {
    }

    public static SerializableHelper getInstance() {
        return SingletonHolder.instance;
    }

    public static ObjectOutputStream getOOS() {
        return SingletonHolder.oos;
    }

    //暂不处理
    public static void release() {

    }

    //必须获取最新的，不然一开始加载的就是空文件
/*  public static ObjectInputStream getOIS() {
        return SingletonHolder.ois;
    }*/
}
