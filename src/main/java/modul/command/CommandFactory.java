package modul.command;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static  CommandFactory factory = new CommandFactory();
    private Map<String, Command> commands = new HashMap<>();

    private CommandFactory(){}

    public static CommandFactory commandFactory(){
        return factory;
    }

    {
        commands.put("login", new LoginComand());
    }

    public Command getCommand(HttpServletRequest request){
        String action = request.getParameter("action");
        Command command = commands.get(action);
        return command;
    }
}
