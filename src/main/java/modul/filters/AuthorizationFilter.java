package modul.filters;

import entety.Verification;

import javax.servlet.*;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private boolean status;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        status = Boolean.parseBoolean(filterConfig.getInitParameter("status"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String email = servletRequest.getParameter("email");
        String firstPass = servletRequest.getParameter("firstPass");
        String secondPass = servletRequest.getParameter("secondPass");
        if (Verification.verifyEmail(email) && Verification.verifyPassword(firstPass) && firstPass.equals(secondPass)){
            
        }
    }

    @Override
    public void destroy() {

    }
}
