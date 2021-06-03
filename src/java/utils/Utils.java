package utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class Utils
{

    public static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public static boolean verificaExisteSessao()
    {
        return !FacesContext.getCurrentInstance().getExternalContext().getSessionMap().isEmpty();
    }
    
    public static boolean verificaExisteRegistroSessao(String registro)
    {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey(registro);
    }
    
    public static Object recuperaRegistroSessao(String registro)
    {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(registro);
    }
    
    public static Object salvaRegistroSessao(String registro, Object objeto)
    {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(registro, objeto);
    }
    
    public static String getRealPath()
    {
        return ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/");
    }
    
}
