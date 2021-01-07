package webModul.filters;

import javax.servlet.*;
import java.io.IOException;

public class SentMoneyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        int i = 0;
        if (i == 1){
            filterChain.doFilter(servletRequest,servletResponse);
        }else
            servletRequest.getRequestDispatcher("error.jsp").forward(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
