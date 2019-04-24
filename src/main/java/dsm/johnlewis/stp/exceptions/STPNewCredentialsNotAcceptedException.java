package dsm.johnlewis.stp.exceptions;

public class STPNewCredentialsNotAcceptedException extends ADUsersException {
	
	private static final long serialVersionUID = 1L;
	
	public STPNewCredentialsNotAcceptedException(){
	}
	
	public STPNewCredentialsNotAcceptedException(String message){
		super(message);
	}	

}
