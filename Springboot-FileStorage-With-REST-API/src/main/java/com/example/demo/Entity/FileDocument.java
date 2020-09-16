package com.example.demo.Entity;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor            
/*SHould not include a no args constructor since instance can
 * 								   only be created if User_Id is present as an argument in Constructor 
 * 								   call.
*/
@ToString
@Getter
@Setter
public class FileDocument {

	
		
		

		@Id
		@GeneratedValue(strategy= GenerationType.AUTO)
		private long id;
		@Column
		private String fileName;
		@Column
		@Lob
		private byte[] docFile;
		
//We are using Joins in order to get the files pertaining to a specific user the Join is in the FileDocument Repository
		
		
		@ManyToOne( optional = false)		//Fetch type not working 
		@JoinColumn(name = "userName")       
//		@JoinColumn(name = "User_id", nullable = false)
 		private User user; // Storing Reference to User Object
		
		
		
		 public long getId() {
		        return id;
		    }

		    public void setId(long id) {
		        this.id = id;
		    }

		    public String getFileName() {
		        return fileName;
		    }

		    public void setFileName(String fileName) {
		        this.fileName = fileName;
		    }

		    public byte[] getDocFile() {
		        return docFile;
		    }

		    public void setDocFile(byte[] docFile) {
		        this.docFile = docFile;
		    }

			public FileDocument(long id, String fileName, byte[] docFile, User user) {
				super();
				this.id = id;
				this.fileName = fileName;
				this.docFile = docFile;
				this.user = user;
			}

			public FileDocument() {
				super();
			}
			
			
		    
//		    public FileDocument(String fileName, byte[] docFile) {
//				super();
//				this.fileName = fileName;
//				this.docFile = docFile;
//		        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		        if (principal instanceof UserDetails) {
//		          String username = ((UserDetails)principal).getUsername();
//		        } else {
//		          String username = principal.toString();
//		        }
//		        fileDocument.User
//			}
		
}
