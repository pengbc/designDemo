package headfirst.hd.command.db.concretecommand;

import headfirst.hd.command.db.interfaces.Command;
import headfirst.hd.command.db.reveiver.DbOp;
import headfirst.hd.command.db.utils.DBUtils;

/**
 * Created by pengbc on 2018/11/2.
 */
public class DbUpdate implements Command {

    private static final long serialVersionUID = 1L;
    private DbOp dbOp;

    public DbUpdate(DbOp dbOp) {
        super();
        this.dbOp = dbOp;
    }

    @Override
    public void execute() {
        dbOp.update();
        System.out.println("命令执行完成，开始序列化本次命令");
        store();
    }

    @Override
    public void undo() {
        dbOp.updateCancel();
    }

    @Override
    public void store() {
        DBUtils.store(this);
    }

}
