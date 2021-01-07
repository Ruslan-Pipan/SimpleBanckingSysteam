package webModul.command;

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
        commands.put("login", new LoginCommand());
        commands.put("authorization", new AuthorizationCommand());
        commands.put("sent_money", new SentMoneyCommand());
        commands.put("draw_money", new DrawMoneyCommand());
    }

    public Command getCommand(HttpServletRequest request){
        String action = request.getParameter("action");
        Command command = commands.get(action);
        return command;
    }
}
