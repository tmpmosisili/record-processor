 
package com.surepay.exceptions;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.surepay.config.StorageProperties;
import com.surepay.service.FileSystemStorageService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

 
 class FileSystemStorageServiceTests {

	private StorageProperties properties = new StorageProperties();
	private FileSystemStorageService service;

	@BeforeEach
	public void init() {
		properties.setLocation("target/files/" + Math.abs(new Random().nextLong()));
		service = new FileSystemStorageService(properties);
		service.init();
	}

	@Test
	public void loadNonExistent() {
		assertThat(service.load("Report.json")).doesNotExist();
	}

	@Test
	public void saveAndLoad() {
		service.store(new MockMultipartFile("report", "report.json", MediaType.APPLICATION_JSON_VALUE,
				"Exception Report".getBytes()));
		assertThat(service.load("report.json")).exists();
	}

	@Test
	public void saveRelativePathNotPermitted() {
		assertThrows(StorageException.class, () -> {
			service.store(new MockMultipartFile("test", "../report.csv",
					MediaType.TEXT_PLAIN_VALUE, "Exception, Report".getBytes()));
		});
	}

	@Test
	public void saveAbsolutePathNotPermitted() {
		assertThrows(StorageException.class, () -> {
			service.store(new MockMultipartFile("foo", "/etc/passwd",
					MediaType.TEXT_PLAIN_VALUE, "Hello, World".getBytes()));
		});
	}


	@Test
	public void savePermitted() {
		service.store(new MockMultipartFile("sure", "pay/../test.txt",
				MediaType.TEXT_PLAIN_VALUE, "Exception, Report".getBytes()));
	}

}
