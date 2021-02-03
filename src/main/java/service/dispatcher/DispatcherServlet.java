package service.dispatcher;


import service.dispatcher.annotations.handlers.DispatcherServletHandler;
import loger.GoodLog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        DispatcherServletHandler handler = new DispatcherServletHandler(req);

        String pah = handler.invokeMethod();
        GoodLog.getInstance().log(pah);

        if (pah != null && !url.equals(pah)){
            resp.sendRedirect( req.getContextPath() + pah);
        }

    }

}
