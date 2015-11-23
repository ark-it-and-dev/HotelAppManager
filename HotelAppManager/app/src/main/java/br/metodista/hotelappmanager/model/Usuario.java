package br.metodista.hotelappmanager.model;

import java.util.List;

/**
 * Created by Gustavo Assalin on 26/10/2015.
 */
public class Usuario {
    private String _id;
    private String login;
    private String senha;
    private List<Produto> listaProduto;

    public List<Produto> getListaProduto() {
        return listaProduto;
    }

    public void setListaProduto(List<Produto> listaProduto) {
        this.listaProduto = listaProduto;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return login + " " + senha;
    }
}
