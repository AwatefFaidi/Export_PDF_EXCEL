package org.sid.exportpdf.web;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;

import org.sid.exportpdf.dao.UserRepository;
import org.sid.exportpdf.entity.User;
import org.sid.exportpdf.service.UserExcelExporter;
import org.sid.exportpdf.service.UserPDFExporter;
import org.sid.exportpdf.service.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
import com.lowagie.text.DocumentException;
 @Controller
public class UserController {
	@Autowired
    private UserServices service;
	 @Autowired
	    private UserRepository userRepository;
	
  @GetMapping("/users")
   public String showusersList(Model model) {
				List<User> userlist= userRepository.findAll();
			   model.addAttribute("userlist",userlist );
			    return "users";
		}

    @GetMapping("/users/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<User> listUsers = service.listAll();
         
        UserPDFExporter exporter = new UserPDFExporter(listUsers);
        exporter.export(response);
         
    }
    
    @GetMapping("/users/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<User> listUsers = service.listAll();
         
        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);
         
        excelExporter.export(response);    
    }  

}
