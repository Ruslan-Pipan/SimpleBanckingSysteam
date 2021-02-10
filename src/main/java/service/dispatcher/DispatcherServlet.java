package service.dispatcher;


import service.dispatcher.annotations.handlers.DispatcherServletHandler;
import loger.GoodLog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This main Servlet, his handler all request from users.
 *
 * @author Ruslan Pipan
 * @version 1.0
 * */
public class DispatcherServlet extends HttpServlet {

    public DispatcherServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    /**
     * The method that handles the request and dispatches a response.
     * @param req HttpServletRequest from users.
     * @param resp HttpServletResponse witch is dispatches in response.
     * */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = req.getRequestURI();
        DispatcherServletHandler handler = new DispatcherServletHandler(req);

        String pah = handler.invokeMethod();
        GoodLog.getInstance().log(pah);

        if (pah != null && !url.equals(pah)){
            resp.sendRedirect( req.getContextPath() + pah);
        }
    }

}
