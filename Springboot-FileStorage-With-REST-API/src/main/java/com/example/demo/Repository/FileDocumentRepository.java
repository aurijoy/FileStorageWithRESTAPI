package com.example.demo.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.demo.Entity.FileDocument;
import com.example.demo.dto.FileViewResponse;

@Repository
public interface FileDocumentRepository extends JpaRepository<FileDocument, Long> {
	
	
	public FileDocument findByFileName(String fileName);

//	@Query ("SELECT f.fileName FROM FILEDOCUMENT f WHERE f.userName like %?1\"")
//	public List<String> getAllFilesBelongingToCurrentUser(String username);

//	@Query("SELECT com.example.demo.dto.FileViewResponse(f.fileName , u.user_id) FROM FILEDOCUMENT f JOIN f.user u ")
//	public List<FileViewResponse> getAllFiles();
//	
//			
//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    if (principal instanceof UserDetails) {
//      String username = ((UserDetails)principal).getUsername();
//    } else {
//      String username = principal.toString();
//			
//	@Query ("SELECT com.example.demo.dto.FileViewResponse(f.fileName , u.user_id) FROM FILEDOCUMENT f JOIN f.user u where u.userName like %?1\"")
//	public List<FileViewResponse> findAllFilesBelongingToUserName (String userName) ;
//	
	
	
//	@Query ("SELECT (f.fileName) FROM FILEDOCUMENT f JOIN f.user u  WHERE u.userName like %?1\"")
//	public ArrayList<String> getAllFilesBelongingToCurrentUser(String userName);

	;
}
