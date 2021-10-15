package com.surepay.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();
	Path store(MultipartFile file);
	Path load(String filename);
	void deleteAll();

}
