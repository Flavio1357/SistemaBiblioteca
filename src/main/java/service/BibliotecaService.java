package main.java.service;

import java.util.ArrayList;
import main.java.model.Usuario;
import main.java.model.Livro;
import main.java.model.Emprestimo;
import main.java.exception.LivroIndisponivelException;
import main.java.exception.UsuarioInvalidoException;
import main.java.exception.LivroInvalidoException;
import main.java.exception.IdDuplicadoException;
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

    public void cadastrarUsuario(Usuario usuario) throws UsuarioInvalidoException, IdDuplicadoException {
        if(usuario.getNome() == null || usuario.getNome().isBlank()){
            throw new UsuarioInvalidoException("Nome do usuário é obrigatório.");
        }

        if(usuario.getEmail() == null || usuario.getEmail().isBlank()){
            throw new UsuarioInvalidoException("Email do usuário é obrigatório.");
        }

        for(Usuario usuarioExistente : usuarios){
            if(usuarioExistente.getId() == usuario.getId()){
                throw new IdDuplicadoException("Já existe um usuário com o ID " + usuario.getId());
            }
        }

        usuarios.add(usuario);
        
    }

    public void cadastrarLivro(Livro livro) throws LivroInvalidoException, IdDuplicadoException {
        if(livro.getTitulo() == null || livro.getTitulo().isBlank()){
            throw new LivroInvalidoException("Título do livro é obrigatório.");
        }

        if(livro.getAutor() == null || livro.getAutor().isBlank()){
            throw new LivroInvalidoException("Autor do Livro é obrigatório.");
        }

        if(livro.getQtd() < 0) {
            throw new LivroInvalidoException("A quantidade do livro não pode ser negativa.");
        }

        if (livroComIdExiste(livro.getId())) {
            throw new IdDuplicadoException(
                "Já existe um livro com o ID " + livro.getId()
            );
        }


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

    private boolean livroComIdExiste(int id) {
        for(Livro livro : livros) {
            if(livro.getId() == id){
                return true;
            }
        }
        return false;
    }

    public Usuario buscarUsuarioPorId(int id){
        for(Usuario usuario : usuarios){
            if(usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public Livro buscarLivroPorId(int id){
        for(Livro livro : livros){
            if(livro.getId() == id) {
                return livro;
            }
        }

        return null;
    }
}
