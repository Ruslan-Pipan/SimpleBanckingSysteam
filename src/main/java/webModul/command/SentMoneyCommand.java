package webModul.command;


import entety.Consumer;

import javax.servlet.http.HttpServletRequest;


public class SentMoneyCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String sumaS = request.getParameter("suma");
        double suma = Double.parseDouble(sumaS);

        String balanceS = request.getParameter("balance");
        double balance = Double.parseDouble(balanceS);

        if (balance >= suma){
            String toBankAccS = request.getParameter("to_bank_acc");

            long toBankAcc = Long.parseLong(toBankAccS);

//            Consumer toSent = GiveCunsumer.bank_acc(toBankAcc).give();

//            request.setAttribute("firstName", toSent.getFirstName());
//            request.setAttribute("lastName", toSent.getLastName());
//            request.setAttribute("to_bank_acc", toBankAccS);
//            request.setAttribute("suma", suma);

            String this_bank_acc = request.getParameter("this_bank_acc");
            request.setAttribute("this_bank_acc",this_bank_acc);

            return "sent_money.jsp";
        }else
            return "cabinet.jsp";


    }
}
