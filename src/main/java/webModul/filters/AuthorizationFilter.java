package webModul.filters;

import entety.Verification;

import javax.servlet.*;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String phoneNumber = servletRequest.getParameter("phoneNumber");
        String email = servletRequest.getParameter("email");
        String firstPass = servletRequest.getParameter("firstPass");
        String secondPass = servletRequest.getParameter("secondPass");

        if (Verification.verifyPhoneNumber(phoneNumber) && Verification.verifyEmail(email) && Verification.verifyPassword(firstPass) && firstPass.equals(secondPass)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else
            servletRequest.getRequestDispatcher("error.jsp").forward(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
