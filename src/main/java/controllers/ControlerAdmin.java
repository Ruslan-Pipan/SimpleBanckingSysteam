package controllers;

import dao.services.implementations.ConsumerServiceImp;
import dao.services.implementations.TransactionServiceImpl;
import entety.Consumer;
import entety.accounts.Transaction;
import exceptions.BadVerification;
import service.dispatcher.annotations.Controler;
import service.dispatcher.annotations.Post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The administrator the only one who can obtain all consumers, consumers by id and last transactions.
 * This Controller handles requests from the administrator.
 * If you want to add a new function for the administrator you must create a method that takes in parameters HttpServletRequest,
 * and will have annotation @Post or @Get, and if the method should forward should have string returns.
 * @author Ruslan Pipan
 * @version 1.2
 *
 */

@Controler
public class ControlerAdmin {

    /** Receives the latest transactions from the consumer's bank acc. */
    @Post("last_transaction")
    String getLastTransactions(HttpServletRequest request){
        HttpSession session = request.getSession();
        String accString = request.getParameter("acc");
        long acc = Long.parseLong(accString);
        List<Transaction> transactions = TransactionServiceImpl.getInstance().getTransactionByAcc(acc);
        session.setAttribute("listTransaction", transactions);
        return "adminPanel.jsp";
    }
    /** Receive consumer by his id. */
    @Post("consumerByID")
     String getConsumer(HttpServletRequest request) throws BadVerification {
        HttpSession session = request.getSession();
        Consumer consumer = ConsumerServiceImp.getInstance().findConsumerByEmail(request.getParameter("email"));
        session.setAttribute("consumerAdmin",consumer);
        return "adminPanel.jsp";
    }
    /** Receives all consumers. */
    @Post("consumers")
    String getConsumers(HttpServletRequest request){
        HttpSession session = request.getSession();
        List<Consumer> consumers = ConsumerServiceImp.getInstance().getConsumersList();
        session.setAttribute("consumers", consumers);
        return "adminPanel.jsp";
    }
}
