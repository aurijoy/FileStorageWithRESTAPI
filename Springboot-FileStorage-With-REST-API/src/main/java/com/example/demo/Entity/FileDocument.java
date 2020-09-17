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

// Try to decouple user from here
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
		
		
//We are using Joins in order to get the files pertaining to a specific user the 
//Join operation in the FileDocument Repository Interface
		
/*This was the first approach of coupling User and FileDocument but this method is not feasible 
 since Sping does not allow the SecurityContextHolder to be passed*/
		
//		@Column (nullable=true)
//		@ManyToOne( optional = false)		//Fetch type not working 
//		@JoinColumn(name = "userName")       
////		@JoinColumn(name = "User_id", nullable = false)
// 		private User user; // Storing Reference to User Object
		
		
// This is a loose coupling technique now user and FileDocument have no reference to connect to each other
// in the data model their coupling is now completely handled by application logic this might not be 
	// best practice but for the sake of this demo we did it 
		
		private String username;
		
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

			public FileDocument(long id, String fileName, byte[] docFile, String username) {
				super();
				this.id = id;
				this.fileName = fileName;
				this.docFile = docFile;
				this.username = username;
			}

			public FileDocument() {
				super();
			}

			public String getUsername() {
				return username;
			}

			public void setUsername(String username) {
				this.username = username;
			}
			
			
// This was our original approach to get User information and tight coupling it to FileDocument
// but we abondoned this approach in favor of somplicity and let application logic handle coupling
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
