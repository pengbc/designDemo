import headfirst.hd.command.db.concretecommand.DbAdd;
import headfirst.hd.command.db.concretecommand.DbDelete;
import headfirst.hd.command.db.concretecommand.DbQuery;
import headfirst.hd.command.db.concretecommand.DbUpdate;
import headfirst.hd.command.db.invoker.DbControl;
import headfirst.hd.command.db.reveiver.DbOp;

/**
 * Created by pengbc on 2018/11/2.
 */
public class DriveTest {
    public static void main(String[] args) {
        //invoker
        DbControl dbControl = new DbControl();
        //reveiver
        DbOp dbOp = new DbOp();
        //concretecommand
        DbAdd dbAdd = new DbAdd(dbOp);
        DbQuery dbQuery = new DbQuery(dbOp);
        DbDelete dbDelete = new DbDelete(dbOp);
        DbUpdate dbUpdate = new DbUpdate(dbOp);
        //绑定
        dbControl.setCommand(dbAdd);
        dbControl.setCommand(dbQuery);
        dbControl.setCommand(dbDelete);  //报错
        dbControl.setCommand(dbUpdate);

        dbControl.startExecutes();
    }
}
