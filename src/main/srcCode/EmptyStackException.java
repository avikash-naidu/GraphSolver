package nz.ac.auckland.softeng281.a4;

public class EmptyStackException extends RuntimeException{
    
    public EmptyStackException(){}

    public EmptyStackException(String msg){
    	super(msg);
    }

}