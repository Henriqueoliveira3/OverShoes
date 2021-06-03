package ManagedBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import model.dao.ProdutoDAO;
import model.entity.Produto;
import org.primefaces.model.file.UploadedFile;

/**@author Henrique de Oliveira*/

@Named(value = "produtoB")
@RequestScoped
public class ProdutoB {
    
    @Inject
    private ProdutoDAO ProdutoDAO;
    
    private Integer id;
    private String nome;
    private String foto;
    private Double preco;
    private String descricao;
    
    private UploadedFile imagem;
    
    public List<Produto> getTodosDados()
    {
        return ProdutoDAO.getAllResults("produto.findAll");
    }
    
    public void salvarProduto()
    {
        

        Produto p = new Produto();
        
        p.setNome(nome);
        p.setPreco(preco);
        p.setDescricao(descricao);
        p.setFoto(foto);
        
        p = ProdutoDAO.save(p);
        
        if (p == null)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage (FacesMessage.SEVERITY_INFO, "O cadastro não foi realizado, favor olhar o output!", ""));
        }
        else
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage (FacesMessage.SEVERITY_INFO, "O Produto " + nome + " foi cadastrado com sucesso!", ""));
        }
    }

  
    
    public String salvar(){
        
        try{
            String caminho = utils.Utils.getRealPath();
            File file = new File(caminho + "/resources/produtos/" + imagem.getFileName());
            OutputStream out = new FileOutputStream(file);
            out.write(imagem.getContent());
            out.close();
            
            foto = imagem.getFileName();
        }
        catch(Exception ex){
            utils.Utils.addMessage(FacesMessage.SEVERITY_FATAL, "Erro ao salvar a imagem", "Erro" + ex.getLocalizedMessage());
            return "index?faces-redirect=true";
        }
        
        Produto p = new Produto();
        p.setNome(nome);
        p.setDescricao(descricao);
        p.setPreco(preco);
        p.setFoto(foto);
        
        p = ProdutoDAO.save(p);
        
        if (p == null)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage (FacesMessage.SEVERITY_INFO, "O cadastro não foi realizado, favor olhar o output!", ""));
        }else
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage (FacesMessage.SEVERITY_INFO, "O produto " + nome + " foi cadastrado com sucesso!", ""));
        }
        
        return "produtos?faces-redirect=true";
    }
    
  
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @return the imagem
     */
    public UploadedFile getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(UploadedFile imagem) {
        this.imagem = imagem;
    }
}