package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/home")
public class HomeController {


    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    private NotesService notesService;

    private UserService userService;

    private CredentialsService credentialsService;

    @Autowired
    private FilesService fileService;

    public HomeController(NotesService notesService,UserService userService,CredentialsService credentialsService,FilesService fileService) {
        this.notesService = notesService;
        this.userService=userService;
        this.credentialsService=credentialsService;
        this.fileService=fileService;

    }

    @GetMapping
    public String getHomePage( Authentication authentication,Model model) throws Exception {
        Integer userId= this.userService.getUser(authentication.getName()).getUserId();

        model.addAttribute("notes", notesService.getNotes());
        model.addAttribute("credentials", credentialsService.getAllCredentialsForUser(userId));
        List<Files> files= null;

        try {
            files = this.fileService.getAllFilesForUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("fileList", files);
        return "home";
    }

    @PostMapping("/addUpdate")
    public String postHomePage(Authentication authentication,Notes notes, Model model) {
        String username=authentication.getName();
        User user=userService.getUser(username);
        Notes note =null;
        note=notesService.findNoteById(notes.getNoteId());

    try {
        if (notes.getNoteId() == null) {
            Notes newNote = notes;
            newNote.setNoteDescription(notes.getNoteDescription());
            newNote.setNoteTitle(notes.getNoteTitle());
            newNote.setUserId(user.getUserId());


            notesService.addNotes(newNote);
            model.addAttribute("notes", notesService.getNotes());

        } else {
            Notes updateNote = notes;
            updateNote.setNoteId(notes.getNoteId());
            updateNote.setNoteDescription(notes.getNoteDescription());
            updateNote.setNoteTitle(notes.getNoteTitle());
            updateNote.setUserId(note.getUserId());
            notesService.updateNote(updateNote);
        }
        return "redirect:/result?success";
    }catch(Exception e){
        return "redirect:/result?error";
    }

    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable("id") Integer id, Model model) {
        Notes note =null;
        note=notesService.findNoteById(id);
        if(note ==null )
        {
            return "redirect:/result?errordelete";
        }else{
            notesService.deleteNoteById(id);
            return "redirect:/result?success";
        }

    }



    @GetMapping("/deleteCredential/{id}")
    public String deleteCredential(@PathVariable("id") Integer id, Model model) {
        Credentials cred =null;
        cred=credentialsService.findCredentialById(id);
        if(cred ==null )
        {
            return  "redirect:/result?errordelete";
        }else{
            credentialsService.deleteCredential(id);
            return  "redirect:/result?success";
        }

    }
    @RequestMapping(value = "/decrypt-password/{id}")
    @ResponseBody
    public List<String> decryptPassword(@PathVariable("id") Integer id) throws Exception {

        Credentials credential=this.credentialsService.findCredentialById(id);
        if(credential==null){

            throw new Exception();

        }
        return Arrays.asList(this.credentialsService.getDecryptedCredential(id));
    }
    @PostMapping("/addUpdateCredential")
    public String postCredentialsPage(Authentication authentication, Credentials credentialItem, Model model) {
        String username=authentication.getName();
        User user=userService.getUser(username);


        logger.info("URL"+credentialItem.getUrlLink());
        try {
            if (credentialItem.getCredentialId() == null) {
                credentialItem.setUserName((userService.getUser(authentication.getName())).getUsername());
                credentialsService.addCredential(credentialItem,authentication);
            } else {
                credentialsService.updateCredential(credentialItem,authentication);
            }
            model.addAttribute("credentials", credentialsService.getAllCredentialsForUser(user.getUserId()));
            return "redirect:/result?success";
        }catch(Exception exp ){
            return  "redirect:/result?error";
        }
    }






}
