package headfirst.hd.command.db.concretecommand;

import headfirst.hd.command.db.interfaces.Command;
import headfirst.hd.command.db.reveiver.DbOp;
import headfirst.hd.command.db.utils.DBUtils;

public class DbQuery implements Command{

    private static final long serialVersionUID = 1L;
    private DbOp dbOp;

    public DbQuery(DbOp dbOp) {
        super();
        this.dbOp = dbOp;
    }

    @Override
    public void execute() {
        dbOp.query();
        System.out.println("命令执行完成，开始序列化本次命令");
        store();
    }

    @Override
    public void undo() {
        dbOp.queryCancel();
    }

    @Override
    public void store() {
        DBUtils.store(this);
    }

}
