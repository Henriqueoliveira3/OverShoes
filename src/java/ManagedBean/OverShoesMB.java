/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Henrique
 */
@Named(value = "overShoesMB")
@RequestScoped
public class OverShoesMB {

    /**
     * Creates a new instance of OverShoesMB
     */
    public OverShoesMB() {
    }
    public String getPaginaSobre(){
      return "sobre";
    }
    public String getPaginaCadastro(){
      return "cadastro";
    }
    public String getPaginaLogin(){
        return "login";
    }
    public String getPaginaInicial(){
        return "index";
    }
    public String getPaginaIdeias(){
        return "ideia";
    }
    public String getPaginaProdutos(){
        return "produtos";
    }
    public String getPaginaSalgado(){
        return "salgado";
    }
    public String getPaginaDoce(){
        return "doce";
    }
    public String getPaginaBebida(){
        return "bebida";
    }
    public String getPaginaCadastroProdutos(){
        return "cadastroProdutos";
    }
    public String getPaginaCarrinho(){
        return "carrinho";
    }
    public String getPaginaListaCompras(){
        return "listaCompras";
    }
    
}
