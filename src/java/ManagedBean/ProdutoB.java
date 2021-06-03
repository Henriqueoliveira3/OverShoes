package ManagedBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
    
    private List<Produto> carrinho;
    
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
    
    public ProdutoB(){
        carrinho = new ArrayList<>();
        
        if(utils.Utils.verificaExisteRegistroSessao("carrinho")){
            carrinho = (List<Produto>) utils.Utils.recuperaRegistroSessao("carrinho");
        }
    }
    
    public String adicionarCarrinho(Produto p){
        getCarrinho().add(p);
        utils.Utils.addMessage(FacesMessage.SEVERITY_INFO,"Sucesso", "Produto foi adicionado no carrinho");
        utils.Utils.salvaRegistroSessao("carrinho", getCarrinho());
        return "index?faces-redirect=true";
    }
    
    public String salvar(){
        
        try 
        {
            String caminho = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/");

            File file = new File(caminho + "/resources/produtosdb/" + imagem.getFileName());

            OutputStream out = new FileOutputStream(file);
            out.write(imagem.getContent());
            out.close();

            foto = imagem.getFileName();

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage (FacesMessage.SEVERITY_FATAL, "Erro ao salvar a Imagem", "Erro: " + e.getLocalizedMessage()));
        }

        Produto p = new Produto();

        p.setNome(nome);
        p.setPreco(preco);
        p.setDescricao(descricao);
        p.setFoto(foto);

        p = ProdutoDAO.save(p);

        if (p == null)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage (FacesMessage.SEVERITY_INFO, "Erro ao Cadastrar", "O cadastro não foi realizado, favor olhar o output!"));
            context.getExternalContext().getFlash().setKeepMessages(true);
        }
        else
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage (FacesMessage.SEVERITY_INFO, "Sucesso!", "O Produto " + nome + " foi cadastrado com sucesso!"));
            context.getExternalContext().getFlash().setKeepMessages(true);
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

    /**
     * @return the carrinho
     */
    public List<Produto> getCarrinho() {
        return carrinho;
    }

    /**
     * @param carrinho the carrinho to set
     */
    public void setCarrinho(List<Produto> carrinho) {
        this.carrinho = carrinho;
    }
}