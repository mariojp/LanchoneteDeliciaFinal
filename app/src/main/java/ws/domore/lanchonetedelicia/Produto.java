package ws.domore.lanchonetedelicia;

/**
 * Created by mariojp on 23/11/2017.
 */

import java.io.Serializable;

public class Produto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer _id;
    private String nome;
    private String preco;
    private String descricao;
    private String imagem;

    public Produto() {
        // TODO Auto-generated constructor stub
    }

    public Produto(Integer _id, String nome, String preco, String descricao) {
        super();
        this._id = _id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }

    public Integer getId() {
        return _id;
    }
    public void setId(Integer id) {
        this._id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getPreco() {
        return preco;
    }
    public void setPreco(String preco) {
        this.preco = preco;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}