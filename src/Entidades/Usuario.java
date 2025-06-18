package Entidades;

public abstract class Usuario {
    protected int id;
    protected String login;
    protected String senha;

    public Usuario(String login, String senha) {
        setLogin(login);
        setSenha(senha);
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }
    public int getId() {
        return id;
    }
}
