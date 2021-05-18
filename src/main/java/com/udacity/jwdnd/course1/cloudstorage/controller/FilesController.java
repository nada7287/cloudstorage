package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.management.MalformedObjectNameException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private FilesService fileService;

    @Autowired
    private UserService userService;

    @PostMapping
    public String getFileView(@RequestParam("fileUpload") MultipartFile  fileUpload, Files newfile,Authentication authentication, Model model) throws IOException, SQLException, MalformedObjectNameException {
        Integer userId= this.userService.getUser(authentication.getName()).getUserId();
        String returnMessage = "redirect:/result?error";

            if (!fileUpload.isEmpty()) {
                if (this.fileService.getFileByName(fileUpload.getResource().getFilename()) != null) {
                    returnMessage = "redirect:/result?errname";
                } else if(fileUpload.getSize()>524288000L){

                    returnMessage = "redirect:/result?errsize";
                }
                else {

                    try {
                        newfile.setUserId(userId);
                        newfile.setContentType(fileUpload.getContentType());
                        newfile.setFileSize(String.valueOf(fileUpload.getSize()));
                        newfile.setFileName(fileUpload.getResource().getFilename());
                        newfile.setFileData(new SerialBlob(fileUpload.getBytes()));
                        this.fileService.createFile(newfile);
                        returnMessage = "redirect:/result?success";

                    } catch (Exception exp) {
                        returnMessage = "redirect:/result?errname";

                    }
                }
            }else{
                returnMessage = "redirect:/result?erremptyFile";
            }




        return returnMessage;
    }

    @GetMapping("/delete/{id}")
    public String deleteFile(@PathVariable("id") Integer id, Authentication authentication, Files fileItem, Model model) throws Exception {
        String returnMessage;
        Integer userId= this.userService.getUser(authentication.getName()).getUserId();
        Files file=this.fileService.findFileById(id);
        if(file==null){
            returnMessage= "redirect:/result?errordelete";
            throw new Exception();
        }

        this.fileService.deleteFile(id);
        returnMessage= "redirect:/result?success";
        return returnMessage;
    }


    @GetMapping("/view/{id}")
    public void downloadPDFResource( HttpServletRequest request,
                                     HttpServletResponse response,
                                     @PathVariable("id") Integer id ,
                                     @RequestHeader String referer) throws FileNotFoundException {
        Files file=this.fileService.findFileById(id);
        if(file==null){
            throw new FileNotFoundException();
        }


        if(referer != null && !referer.isEmpty()) {
        }
        response.setContentType(file.getContentType());
        response.addHeader("Content-Disposition", "attachment; filename="+file.getFileName());
        try
        {
            IOUtils.copy(file.getFileData().getBinaryStream(), response.getOutputStream());
            response.getOutputStream().flush();
        }
        catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }

    }
















}



