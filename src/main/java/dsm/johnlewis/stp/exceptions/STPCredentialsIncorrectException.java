package dsm.johnlewis.stp.exceptions;

public class STPCredentialsIncorrectException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public STPCredentialsIncorrectException(){
	}
	
	public STPCredentialsIncorrectException(String message){
		super(message);
	}
}
