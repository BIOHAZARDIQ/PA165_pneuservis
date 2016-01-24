/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.mvc.filters;

import cz.muni.fi.pa165_pneuservis.dto.CustomerDTO;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Ondrej Komarek <448288@mail.muni.cz>
 */
@WebFilter(urlPatterns={"/tire/*", "/service/*","/customer/*"})
public class AccessFilter implements Filter {

@Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(true);
        
        CustomerDTO admin = (CustomerDTO)session.getAttribute("auth");
        
        if(admin == null) 
            response.sendRedirect(getLoginContext(request));
        
        chain.doFilter(request, response);   
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    @Override
    public void destroy() {
    }
    
    private String getLoginContext(HttpServletRequest request)
    {
        return request.getContextPath() + "/login";
    }
}
