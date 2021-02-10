package controllers;

import dao.services.implementations.ConsumerServiceImp;
import entety.Consumer;
import exceptions.BadVerification;
import service.dispatcher.annotations.Controler;
import service.dispatcher.annotations.Get;
import service.dispatcher.annotations.Post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The Person haven't access to cabinet pages and adminPanel. It can be located on the only index page.
 *
 * If you want to add a new function for the person you must create a method that takes in parameters HttpServletRequest,
 * and will have annotation @Post or @Get, and if the method should forward should have string returns.
 * @author Ruslan Pipan
 * @version 1.2
 * */
@Controler
public class ControlerPerson {
    /**
     * If a person has recorded in the database he can log in to his account cabinet, entering password and email.
     * After that will be pass into the state consumer.
     * */
    @Get("authorization")
    String login(HttpServletRequest request) throws BadVerification {
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String passReq = request.getParameter("password");

        Consumer consumer = ConsumerServiceImp.getInstance().findConsumerByEmail(email);

        if (consumer != null && passReq.equals(consumer.getPassword())){
            session.setAttribute("consumer",consumer);
            return "cabinet.jsp";
        }
        session.setAttribute("err","Bad verification");
        return "error.jsp";
    }

    /**
     * A Person can be registering, after that will be pass into the state consumer and obtain own recorded in the database.
     * */
    @Post("registering")
    String authorization(HttpServletRequest request)  {
        HttpSession session = request.getSession();
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String pass = request.getParameter("firstPass");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        Consumer consumer = null;
        try {
            consumer = new Consumer.ConsumerBuild(firstName, secondName).setPass(pass).setEmail(email).setNumber(phoneNumber).build();
        } catch (BadVerification badVerification) {
            session.setAttribute("err", "Bad verification");
            badVerification.printStackTrace();
            return "error.jsp";
        }
        ConsumerServiceImp.getInstance().addConsumer(consumer);
        session.setAttribute("consumer",consumer);
        return "cabinet.jsp";
    }
}
