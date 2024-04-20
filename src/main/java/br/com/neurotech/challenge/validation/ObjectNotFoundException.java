package br.com.neurotech.challenge.validation;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String id){
        super("Objeto não encontrado -> Id " + id);
    }
}
