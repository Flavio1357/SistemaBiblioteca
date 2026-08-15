package main.java.service;

import java.util.ArrayList;
import main.java.model.Usuario;
import main.java.model.Livro;
import main.java.model.Emprestimo;
import main.java.exception.LivroIndisponivelException;
import main.java.model.StatusEmprestimo;
import java.util.List;
import java.util.Collections;

public class BibliotecaService {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Livro> livros;
    private ArrayList<Emprestimo> emprestimos;

    public BibliotecaService(){
        usuarios = new ArrayList<>();
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return Collections.unmodifiableList(usuarios);
    }

    public List<Livro> getLivros() {
        return Collections.unmodifiableList(livros);
    }

    public List<Emprestimo> getEmprestimos() {
        return Collections.unmodifiableList(emprestimos);
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public void realizarEmprestimo(Emprestimo emprestimo) throws LivroIndisponivelException {
        if(emprestimo.getLivro().getQtd() <= 0){
            throw new LivroIndisponivelException("Livro indisponível.");
        }

        emprestimos.add(emprestimo);
        emprestimo.getLivro().setQtd(emprestimo.getLivro().getQtd() - 1);
    }

    public void devolverLivro(Emprestimo emprestimo) {

        if (emprestimo.getStatus() == StatusEmprestimo.DEVOLVIDO) {
            System.out.println("Este empréstimo já foi devolvido.");
            return;
        }

        emprestimo.devolver();

        emprestimo.getLivro().setQtd(emprestimo.getLivro().getQtd() + 1);
    }
}
