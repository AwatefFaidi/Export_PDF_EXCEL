package org.sid.exportpdf.dao;


import org.sid.exportpdf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	     
	
}
