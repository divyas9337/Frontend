package com.niit.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {
	@SuppressWarnings("unused")
	@Autowired
	private  HttpSession httpsession;
	
	@SuppressWarnings("unused")
	private static final Logger logger=LoggerFactory.getLogger(FileUtil.class);
	//private static final String imageDirectory="ShoppingCartImages";
	//private static String rootPath=System.getProperty("catalina.home");
	private static String rootPath = System.getProperty("user.dir");
	public static  boolean fileCopyNIO(MultipartFile file,String fileName) {
		System.out.println(rootPath);
		File dest = new File(rootPath+File.separator  + fileName);
		System.out.println("where it is uploading ??"+ dest.getAbsolutePath());
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
