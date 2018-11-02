package headfirst.hd.command.db.invoker;


import java.util.ArrayList;
import java.util.List;

import headfirst.hd.command.db.interfaces.Command;
import headfirst.hd.command.db.utils.DBUtils;

public class DbControl {
    private List<Command> commands = new ArrayList<Command>();

    public void setCommand(Command command) {
        commands.add(command);
    }

    public void startExecutes() {
        for (Command command : commands) {
            command.execute();
        }
    }

    //额外方法，这个方法应该不放在这个类中，这里简化操作，放在这里
    public void loadComands() {
        for (Command command : DBUtils.load()) {
            command.undo();
        }
    }
}
