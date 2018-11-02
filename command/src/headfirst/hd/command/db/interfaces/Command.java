package headfirst.hd.command.db.interfaces;

import java.io.Serializable;

/**
 * Created by pengbc on 2018/11/2.
 */

public interface Command extends Serializable {
    //执行命令的正常操作
    void execute();
    //回退操作
    void undo();
    //序列化操作，对应execute方法
    void store();
}
