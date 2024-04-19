package account.responses;

import java.time.LocalDateTime;

public class BadRequestResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;

    public BadRequestResponse(String path) {
        this.timestamp = LocalDateTime.now();
        this.status = 400;
        this.error =  "Bad Request";
        this.path = path;
    }

    public String getStatus() {
        return "{" +
                "\n     Timestamp: " + timestamp +
                "\n     Status: " + status +
                "\n     Error: " + error +
                "\n     Path: " + path +
                "\n}";
    }
}
