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

	
// new Approach with un-coupled Entities
//	

	@Query ("SELECT f.fileName FROM FileDocument f WHERE f.username  = ?1")
	public ArrayList<String> getAllFilesBelongingToCurrentUser(String username);

// Old approach with tightly coupled entities
//	@Query ("SELECT com.example.demo.dto.FileViewResponse(f.fileName , u.user_id) FROM FILEDOCUMENT f JOIN f.user u where u.userName like %?1\"")
//	public List<FileViewResponse> findAllFilesBelongingToUserName (String userName) ;
//	
	
	// Old Approach with tightly coupled entities
//	@Query ("SELECT (f.fileName) FROM FILEDOCUMENT f JOIN f.user u  WHERE u.userName like %?1\"")
//	public ArrayList<String> getAllFilesBelongingToCurrentUser(String userName);


}
