package headfirst.hd.command.db.utils;

import headfirst.hd.command.db.interfaces.Command;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengbc on 2018/11/2.
 */
public class DBUtils {
    public static void store(Command command) {
        ObjectOutputStream oos = SerializableHelper.getOOS();
        try {
            oos.writeObject(command);
        } catch (IOException e) {
            // 这里不能抛异常，子类异常不能超过父类异常，简单处理
            e.printStackTrace();
        }
    }

    public static List<Command> load() {
        List<Command> commands = new ArrayList<Command>();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(SerializableHelper.FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                Object object = ois.readObject();
                //if (object instanceof Command),未考察区别，大多数人用的下面方法
                if (Command.class.isAssignableFrom(object.getClass())) {
                    Command command = (Command) object;
                    commands.add(command);
                }
            }
        } catch (Exception e) {
            //请百度：ObjectInputStream判断文件是否序列化完毕的思路，由于是模拟系统宕机，不可能是文件末标志结束符，这里用异常方式
            //读取到文件末，会抛异常，这里返回null，作为判断文件结束条件
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return commands;
    }
}

