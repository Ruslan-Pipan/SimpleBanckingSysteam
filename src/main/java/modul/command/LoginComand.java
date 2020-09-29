package modul.command;

import dao.consumer.give.GiveCunsumer;
import entety.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginComand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        GiveCunsumer give = GiveCunsumer.email(email,pass);
        Consumer consumer = give.give();

        if (consumer != null){
            HttpSession session = request.getSession();
            session.setAttribute("consumer",consumer);
            return "cabinet.jsp";
        }else
            return "index.jsp";

    }
}
