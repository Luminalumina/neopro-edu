package one.neopro.edu.neoproedu.DTO;

public class ErrorDTO {
    private String errorCode;
    private String errorMessage;

    public String getSystemErrorMessage() {
        return systemErrorMessage;
    }

    public void setSystemErrorMessage(String systemErrorMessage) {
        this.systemErrorMessage = systemErrorMessage;
    }

    private String systemErrorMessage;

    public ErrorDTO() {
    }


    public ErrorDTO(String errorCode, String errorMessage, String systemErrorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.systemErrorMessage = systemErrorMessage;
    }

    public ErrorDTO(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
