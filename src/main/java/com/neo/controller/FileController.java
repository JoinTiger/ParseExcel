package com.neo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neo.bean.User;
import com.neo.service.RecordService;
import com.neo.service.UserService;
import com.neo.util.ExcelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FileController {
    //Save the uploaded file to this folder
    
    @Resource
    private UserService userService;
    
    @Resource
    private RecordService recordService;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws Exception {
        if (file.isEmpty()) {
            return "upload";
        }

        
        
        List<User> users = ExcelUtil.getUsers(file);
        
        userService.saveAll(users);
        
        users.clear();
        users = null;
        
        redirectAttributes.addFlashAttribute("message",
                 "上传文件成功 '" + file.getOriginalFilename() + "'");


        return "redirect:/uploadStatus";
    }

    @GetMapping("/download") // //new annotation since 4.3
    public void download(HttpServletRequest request, 
    		HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		//设置ContentType字段值
		response.setContentType("text/html;charset=utf-8");
		//获取所要下载的文件
		String filename = request.getParameter("filename");
		String folder = "classpath:files/";
		
		File file = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			file = ResourceUtils.getFile(folder + filename);
			//通知浏览器以下载的方式打开
			in = new FileInputStream(file);
			out = response.getOutputStream();
			response.addHeader("Content-type", "appllication/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename="+filename);
			//通知文件流读取文件
			
			byte[] buffer = new byte[1024];
			int len;
			//循环取出流中的数据
			
			while((len = in.read(buffer)) != -1){
				out.write(buffer,0,len);
			}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				 {
					try {
						if(out != null) {
							out.close();
							out = null;
						}
						
						if(in != null) {
							in.close();
							in = null;
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
    	
    }

    @GetMapping("/downloadData") // //new annotation since 4.3
    public void downloadData(HttpServletRequest request, 
    		HttpServletResponse response) throws IOException {
    	List<User> users = userService.getAll();
    	ExcelUtil.dataToExcel(users, response);
    	users.clear();
    	users = null;
    }
    
    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}