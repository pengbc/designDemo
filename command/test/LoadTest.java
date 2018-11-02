import headfirst.hd.command.db.invoker.DbControl;

/**
 * Created by pengbc on 2018/11/2.
 */
public class LoadTest {

    public static void main(String[] args) {
        //invoker
        DbControl dbControl = new DbControl();
        //加载异常时候的命令，回退
        dbControl.loadComands();
    }


}
