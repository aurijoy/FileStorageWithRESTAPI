package com.example.demo.dto;





public class FileUploadResponse {

	private String fileName;

    private String contentType;

    private String url;
    
    private int User_id;

  
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	public int getUser_id() {
		return User_id;
	}

	public void setUser_id(int user_id) {
		User_id = user_id;
	}

	public FileUploadResponse() {
		super();
	}

	public FileUploadResponse(String fileName, String contentType, String url) {
		super();
		this.fileName = fileName;
		this.contentType = contentType;
		this.url = url;
	}

//	public FileUploadResponse(String fileName, String url, int user_id) {
//		super();
//		this.fileName = fileName;
//		this.url = url;
//		User_id = user_id;
//	}

	

	

}
