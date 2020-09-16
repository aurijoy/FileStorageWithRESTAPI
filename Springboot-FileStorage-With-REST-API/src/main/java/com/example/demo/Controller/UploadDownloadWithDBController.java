package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.util.StringUtils;

import com.example.demo.Entity.FileDocument;
import com.example.demo.Entity.User;
import com.example.demo.Repository.FileDocumentRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.FileUploadResponse;

@RestController
public class UploadDownloadWithDBController {

	@Autowired
	private FileDocumentRepository fileRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public UploadDownloadWithDBController(FileDocumentRepository fileRepository){
		this.fileRepository = fileRepository;
			
	}
	
	@PostMapping("/upload") 
	FileUploadResponse singleFileUplaod(@RequestParam("file") MultipartFile file) throws IOException {

        String name = StringUtils.cleanPath(file.getOriginalFilename());
        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName(name);
        fileDocument.setDocFile(file.getBytes());
//        String userName;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//           userName = ((UserDetails)principal).getUsername();
//        } else {
//           userName = principal.toString();
//        }
//        User user = userRepository.findByUserName(userName);
        
        fileRepository.save(fileDocument);
        
        
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")						// return it from the Database itself
                .path(name)
                .toUriString();
        
        String contentType = file.getContentType();
        
        FileUploadResponse response = new FileUploadResponse (name, contentType, url);
        
        return response;
        
	}
	
	
	 @GetMapping("/download/{fileName}")
	    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

	        FileDocument doc = fileRepository.findByFileName(fileName);

	        String mimeType = request.getServletContext().getMimeType(doc.getFileName());

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(mimeType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName="+ doc.getFileName())
	//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + doc.getFileName())
	//                .body(null);
	                .body(doc.getDocFile());
	                
	    }
//	    
//	    @GetMapping("/getUserFiles")
//	    public List<String> getAllFilesBelongingToLoggedInUser() {
//	    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	    	String userName;
//	    	if (principal instanceof UserDetails) {
//	    	   userName = ((UserDetails)principal).getUsername();
//	    	} else {
//	    	   userName = principal.toString();
//	    	}
//	    	
//			return fileRepository.getAllFilesBelongingToCurrentUser(userName);
//	    }
	    
	    
	    
}
