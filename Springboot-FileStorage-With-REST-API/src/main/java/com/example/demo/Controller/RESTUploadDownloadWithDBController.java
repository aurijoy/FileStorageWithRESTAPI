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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import com.example.demo.Service.CustomUserDetails;
import com.example.demo.dto.FileUploadResponse;

@RestController
public class RESTUploadDownloadWithDBController {

	@Autowired
	private FileDocumentRepository fileRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public RESTUploadDownloadWithDBController(FileDocumentRepository fileRepository){
		this.fileRepository = fileRepository;
			
	}
	
	
// Post mapping for uploading file using REST itself
	@PostMapping("/upload") 
	FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam ("username") String usersuppliedusername) throws IOException {

		// Building the fileDocument to be Stored
        String name = StringUtils.cleanPath(file.getOriginalFilename());
        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName(name);
        fileDocument.setDocFile(file.getBytes());
        
// This is the first attempt at constraining the method of obtaining the username from the 
// the securityContextHolder we have abandoned this method in favor of a simpler one.
//        String userName;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//           userName = ((UserDetails)principal).getUsername();
//        } else {
//           userName = principal.toString();
//        }
//        User user = userRepository.findByUserName(userName);
//        fileDocument.setUser(user);
        
        
        
  //      String username="defaultusername";   // hardcoding the username for training purpose only
      
        String username= usersuppliedusername;  // getting username from Post request itself
        fileDocument.setUsername(username);		// finally preparing fileDocument
        
        
        
        fileRepository.save(fileDocument);		// Pushing to Database
        
     // Getting the file url from database itself  
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")						
                .path(name)
                .toUriString();
        
        String contentType = file.getContentType();
        
        
        // FileUploadResponse is a DTO designed for this purpose
        FileUploadResponse response = new FileUploadResponse (name, contentType, url);
        
        return response;
        
	}
///////////////////////////////////////////////////////
	// Handling Downloading of File
///////////////////////////////////////////////////////
	
	// Get mapping for downloading file using REST itself
	 @GetMapping("/download/{fileName}")
	    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

	        FileDocument doc = fileRepository.findByFileName(fileName);

	        String mimeType = request.getServletContext().getMimeType(doc.getFileName());

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(mimeType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName="+ doc.getFileName())		// file as attachment
	//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + doc.getFileName())  // we are not rendering file at browser
	//                .body(null);               // test with no file body
	                .body(doc.getDocFile());
	                
	    }
/////////////////////////////////////////////////////
	// Handling Show All Files Belonging to Current User     
/////////////////////////////////////////////////////	    
//Method 1:
	    //Data model is tightly coupled with references
//This was using our previous approach but we were obtaining User from SecurityContextHolder and
//join was failing so we try another approach
	    
	   
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
	    
	    
// Method 2:
	    //Handle purely on the application side	no coupling with User Repository    
//  Error with @ Query method trace error and debug temporarily disabled
	    
//	    @GetMapping("/getUserFiles")
//	    public List<String> getAllFilesBelongingToLoggedInUser(@RequestParam("username") String username) {
//  
//	    	User user = userRepository.findByUserName(username);
//	    	// Whether user exists in repository or not check logic
//	    	if (user!=null) {
//				return fileRepository.getAllFilesBelongingToCurrentUser(username);
//			}else {
//				throw new UsernameNotFoundException("User with given username does not exist:"+username);
//			}
//		
//	    }
	    
	    
}
