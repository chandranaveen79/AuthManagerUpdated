package dsm.johnlewis.stp.exceptions;

public class STPCredentialsExpiredException extends ADUsersException {
	
	private static final long serialVersionUID = 1L;
	
	public STPCredentialsExpiredException(){
	}

	public STPCredentialsExpiredException(String message){
		super(message);
	}

}
