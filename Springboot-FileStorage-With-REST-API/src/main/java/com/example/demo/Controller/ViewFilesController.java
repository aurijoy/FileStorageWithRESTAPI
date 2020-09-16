package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.util.StringUtils;

import com.example.demo.Entity.FileDocument;
import com.example.demo.Entity.User;
import com.example.demo.Repository.FileDocumentRepository;
import com.example.demo.dto.FileUploadResponse;

@RestController
public class ViewFilesController {
	@Autowired
	private FileDocumentRepository fileRepository;
	
//	@Autowired
//	private User user;
	
	public ViewFilesController(FileDocumentRepository fileRepository){
		this.fileRepository = fileRepository;
			
	}
	
	

}
