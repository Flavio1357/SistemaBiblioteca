package main.java.service;

import java.util.ArrayList;
import main.java.model.Usuario;
import main.java.model.Livro;

public class BibliotecaService {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Livro> livros;

    public BibliotecaService(){
        usuarios = new ArrayList<>();
        livros = new ArrayList<>();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }
}
