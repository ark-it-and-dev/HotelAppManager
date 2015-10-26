package br.metodista.hotelappmanager.model;

/**
 * Created by Gustavo Assalin on 26/10/2015.
 */
public class Usuario {
    private static Usuario usuario;

    private String login;
    private String senha;

    private Usuario() {}

    public static Usuario getInstance() {
        if(usuario == null) {
            return new Usuario();
        } else {
            return usuario;
        }
    }

    public static void encerrarSessao() {
        usuario = null;
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
