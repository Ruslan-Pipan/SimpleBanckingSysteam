package controllers;

import dao.services.interfaces.ConsumerService;
import dao.services.interfaces.TransactionService;
import entety.Consumer;
import entety.accounts.Transaction;
import exceptions.BadVerification;
import dao.ServiceConstants;
import service.dispatcher.annotations.Controler;
import service.dispatcher.annotations.Post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controler("")
public class ControlerAdmin {

    @Post("/last_transaction")
    String getLastTransactions(HttpServletRequest request){
        HttpSession session =request.getSession();
        TransactionService TS = ServiceConstants.TRANSACTION_SERVICE;
        String accString = request.getParameter("acc");
        long acc = Long.parseLong(accString);
        List<Transaction> transactions = TS.getTransactionByAcc(acc);
        session.setAttribute("listTransaction", transactions);
        return "adminPanel.jsp";
    }

    @Post("/consumerByID")
     String getConsumer(HttpServletRequest request) throws BadVerification {
        HttpSession session = request.getSession();
        ConsumerService CS = ServiceConstants.CONSUMER_SERVICE;
        Consumer consumer = CS.findConsumerByEmail(request.getParameter("email"));
        session.setAttribute("consumerAdmin",consumer);
        return "adminPanel.jsp";
    }
    @Post("/consumers")
    String getConsumers(HttpServletRequest request){
        HttpSession session = request.getSession();
        ConsumerService CS = ServiceConstants.CONSUMER_SERVICE;
        List<Consumer> consumers = CS.getConsumersList();
        session.setAttribute("consumers", consumers);
        return "adminPanel.jsp";
    }
}
