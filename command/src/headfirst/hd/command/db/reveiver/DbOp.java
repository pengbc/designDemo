package headfirst.hd.command.db.reveiver;

/**
 * Created by pengbc on 2018/11/2.
 */

import java.io.Serializable;

public class DbOp implements Serializable {

    private static final long serialVersionUID = 1L;

    public void add() {
        System.out.println("对数据【添加】操作");
    }
    public void delete() {
        System.out.println("对数据【删除】操作");
        //模拟宕机
        throw new RuntimeException("当前操作【删除】,系统宕机。。。");

    }
    public void update() {
        System.out.println("对数据【修改】操作");
    }
    public void query() {
        System.out.println("对数据【查询】操作");
    }
    public void addCancel() {
        System.out.println("【回退之前】对数据【添加】操作");
    }
    public void deleteCancel() {
        System.out.println("【回退之前】对数据【删除】操作");
    }
    public void updateCancel() {
        System.out.println("【回退之前】对数据【修改】操作");
    }
    //查询不需要回退操作，空方法，这里只是显示提示
    public void queryCancel() {
        System.out.println("【回退之前】对数据【查询】操作");
    }
}

