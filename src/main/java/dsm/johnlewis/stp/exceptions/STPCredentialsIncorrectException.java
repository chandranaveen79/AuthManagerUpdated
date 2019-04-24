package dsm.johnlewis.stp.exceptions;

public class STPCredentialsIncorrectException extends ADUsersException{

	private static final long serialVersionUID = 1L;
	
	public STPCredentialsIncorrectException(){
	}
	
	public STPCredentialsIncorrectException(String message){
		super(message);
	}
	

}
