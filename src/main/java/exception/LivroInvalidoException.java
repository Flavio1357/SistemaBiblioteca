package main.java.exception;

public class LivroInvalidoException extends Exception {
    public LivroInvalidoException(String mensagem){
        super(mensagem);
    }
}
