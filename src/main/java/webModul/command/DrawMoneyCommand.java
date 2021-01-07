package webModul.command;

import javax.servlet.http.HttpServletRequest;


public class DrawMoneyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String this_bank_acc = request.getParameter("this_bank_acc");
        String awt = request.getParameter("awt");
        String name_acc = request.getParameter("name_acc");

        System.out.println(this_bank_acc + awt);


        return "cabinet.jsp";
    }
}
