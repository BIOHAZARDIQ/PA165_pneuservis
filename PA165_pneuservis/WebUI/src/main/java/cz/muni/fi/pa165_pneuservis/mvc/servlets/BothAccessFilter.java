/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165_pneuservis.mvc.servlets;

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
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Komoi
 */
@WebFilter()
public class BothAccessFilter implements Filter {

    final static Logger log = LoggerFactory.getLogger(BothAccessFilter.class);

    public static final String USERNAME1 = "user";
    public static final String PASSWORD1 = "user";
    
    public static final String USERNAME2 = "admin";
    public static final String PASSWORD2 = "admin";

    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;
        String auth = request.getHeader("Authorization");
        if (auth == null) {
            response401(response);
            return;
        }
        String[] creds = parseAuthHeader(auth);
        if((!USERNAME1.equals(creds[0]) || !PASSWORD1.equals(creds[1])) && (!USERNAME2.equals(creds[0]) || !PASSWORD2.equals(creds[1]))) {
            log.warn("wrong credentials: user={} password={}",creds[0],creds[1]);
            response401(response);
            return;
        } else if ((USERNAME1.equals(creds[0]) && PASSWORD1.equals(creds[1]))){
            //request.getSession().setAttribute("loggedInUser", "user");
            UserLogged.usernameLogged = "user";
        } else {
            //request.getSession().setAttribute("loggedInUser", "admin");
            UserLogged.usernameLogged = "admin";
        }
        chain.doFilter(request, response);
    }

    private String[] parseAuthHeader(String auth) {
        return new String(DatatypeConverter.parseBase64Binary(auth.split(" ")[1])).split(":", 2);
    }

    private void response401(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "Basic realm=\"type password\"");
        response.getWriter().println("<html><body><h1>401 Unauthorized</h1></body></html>");
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }
}
