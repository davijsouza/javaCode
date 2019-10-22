 package com.souche.salt_common_01.controller;

 import io.swagger.annotations.Api;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;
 import org.springframework.web.multipart.MultipartFile;

 import javax.servlet.ServletException;
 import javax.servlet.http.Cookie;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import java.io.File;
 import java.io.IOException;
 import java.text.SimpleDateFormat;
 import java.time.format.DateTimeFormatter;
 import java.util.Date;

 @RestController
 @Api(tags ={"暂不可用"})
public class MultiController {

   @GetMapping("/lantu/file/upload")
   public String  fileUpload2(MultipartFile uploadFile) throws IOException {
	   /*创建当天日期作为存放路径*/
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
	   String datepath= sdf.format(new Date());
	   String path="/Users/dasouche/D/upFile/"+datepath;
	   File newFile=new File(path);
	   /*判断文件路径是否存在  不存在则创建*/
	   if(!newFile.exists()){
		   newFile.mkdirs();
	   }
	   /*上传后文件的路径和名称*/
	   File targetFile=new File(path,uploadFile.getOriginalFilename());
	   /*上传*/
       try {
           uploadFile.transferTo(targetFile);
           return "success";
       }catch (Exception e){
           System.err.println("文件上传失败");
           e.printStackTrace();
           return  "error";
       }
   }



}
