package com.royal.payload;

public class FileResponse {
    private String message;
    private String filename;

    public FileResponse(String message, String filename) {
        super();
        this.message = message;
        this.filename = filename;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }



}
