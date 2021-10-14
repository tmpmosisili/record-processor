package com.surepay.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.surepay.exceptions.StorageFileNotFoundException;
import com.surepay.model.ExceptionReport;
import com.surepay.service.FileStrategy;
import com.surepay.service.StorageService;


@Controller
public class FileUploadController {
	@Autowired
	private  StorageService storageService;
	
	@Autowired
	@Qualifier("csvImplmentation")
	private FileStrategy csvFileStrategy;
	
	private ArrayList <ExceptionReport> exceptionReport;
	
	@Autowired
	@Qualifier("jsonImplmentation")
	private FileStrategy jsonFileStrategy;
	
	public FileUploadController() {
		super();
	}
	
	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {
		return "upload";
	}

 
	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes, @RequestParam("filetype") String fileType) {		
		if (fileType.equalsIgnoreCase("CSV")){
			this.exceptionReport = (ArrayList<ExceptionReport>) csvFileStrategy.processRecords(storageService.store(file));
		}else if (fileType.equalsIgnoreCase("JSON")){
			this.exceptionReport = (ArrayList<ExceptionReport>) jsonFileStrategy.processRecords(storageService.store(file));
		}		
		redirectAttributes.addFlashAttribute("message", 
				"We have process the following bank statement " + file.getOriginalFilename());

		return "redirect:/report";
	}
	
	@GetMapping("/report")
	public String showReport(@ModelAttribute ArrayList <ExceptionReport> exceptionReport, Model model){
		model.addAttribute("exceptionReport", this.exceptionReport);
		return "report";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<Object> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}


}
