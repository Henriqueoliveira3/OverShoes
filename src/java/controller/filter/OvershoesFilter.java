
package controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.entity.Usuario;


@WebFilter("/*")
public class OvershoesFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException
    {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpRequest.getSession();
        
        if(httpRequest.getRequestURI().contains("cadastroProdutos") || httpRequest.getRequestURI().contains("listaCompras") ){
            
            Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
            
            if(usuario == null || !usuario.getAdministrador()){
                
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsf");
            }
        }
        
        chain.doFilter(request, response);
    }
    
}
