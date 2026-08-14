package main.java.service;

import java.util.ArrayList;
import main.java.model.Usuario;
import main.java.model.Livro;
import main.java.model.Emprestimo;

public class BibliotecaService {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Livro> livros;
    private ArrayList<Emprestimo> emprestimos;

    public BibliotecaService(){
        usuarios = new ArrayList<>();
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();
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

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public void realizarEmprestimo(Emprestimo emprestimo) {
        if(emprestimo.getLivro().getQtd() <= 0){
            System.out.println("Livro Indisponivel");
            return;
        }

        emprestimos.add(emprestimo);
        emprestimo.getLivro().setQtd(emprestimo.getLivro().getQtd() - 1);
    }
}
