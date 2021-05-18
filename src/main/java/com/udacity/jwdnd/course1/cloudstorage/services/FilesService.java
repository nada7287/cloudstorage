package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class FilesService {

    private final FilesMapper fileMapper;
    private final  UserService userService;


    public FilesService(FilesMapper fileMapper, UserService userService ){
        this.fileMapper=fileMapper;
        this.userService=userService;

    }



    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating FileService bean");
    }



    public List<Files> getAllFiles(){

        List<Files>  allFiles= this.fileMapper.getAllFiles();

        return allFiles;


    }


    public Files getFileByName(String name){

        Files file= this.fileMapper.getFileByName(name);

        return file;


    }

    public List<Files> getAllFilesForUser(Integer userid) throws Exception{

        List<Files> files= this.fileMapper.getFilesByUser(userid);

        if (files == null){

            throw new Exception();
        }

        return files;
    }

    public void createFile(Files file ){

        int fileid= this.fileMapper.insertFile(file);



    }

    public Files findFileById(Integer fileid ){

        Files targetFile= this.fileMapper.getFileById(fileid);



        return targetFile;

    }



    public int updateFile(Files file){

        int targetFile= this.fileMapper.updateFile(file);


        return targetFile;
    }


    public void deleteFile(Integer fileid){

        this.fileMapper.deleteFile(fileid);

    }




}
