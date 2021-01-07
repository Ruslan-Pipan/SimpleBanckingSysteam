package webModul.command;

import bankException.BadVerification;

import entety.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
//        Insert insert = new InsertCunsumer();

        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String pass = request.getParameter("firstPass");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");


        try {
            Consumer consumer = new Consumer.CunsumerBild(firstName, secondName).setPass(pass).setEmail(email).setNumber(phoneNumber).build();
//            insert.insert(consumer);
            HttpSession session = request.getSession();
            session.setAttribute("consumer",consumer);
            return "cabinet.jsp";
        } catch (BadVerification badVerification) {
            badVerification.printStackTrace();
            return "error.jsp";
        }

    }
}
