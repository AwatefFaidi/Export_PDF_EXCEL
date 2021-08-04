package org.sid.exportpdf.service;
import java.util.List;

import javax.transaction.Transactional;

import org.sid.exportpdf.dao.UserRepository;
import org.sid.exportpdf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
 
@Service
@Transactional
public class UserServices {
	 @Autowired
	    private UserRepository repo;
	     
	    public List<User> listAll() {
	        return repo.findAll(Sort.by("email").ascending());
	    }
}
