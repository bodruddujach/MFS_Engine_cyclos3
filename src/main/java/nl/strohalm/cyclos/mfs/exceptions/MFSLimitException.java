package nl.strohalm.cyclos.mfs.exceptions;

import org.springframework.http.HttpStatus;

public class MFSLimitException extends MFSCommonException{

	private static final long serialVersionUID = -3693225805959309043L;

	public MFSLimitException(String errorCode, String message) {
		super(errorCode, message, HttpStatus.BAD_REQUEST);
		// TODO Auto-generated constructor stub
	}

	public MFSLimitException(String errorCode, String message, HttpStatus httpStatus) {
		super(errorCode, message, httpStatus);
		// TODO Auto-generated constructor stub
	}

}
