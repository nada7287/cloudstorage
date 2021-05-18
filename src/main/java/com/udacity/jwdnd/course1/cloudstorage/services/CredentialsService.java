package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialsService {


    private  CredentialsMapper credentialMapper;

    private   EncryptionService encryptionService;

    private  UserService userService;

    public CredentialsService(CredentialsMapper credentialMapper, EncryptionService encryptionService, UserService userService){
        this.credentialMapper=credentialMapper;
        this.encryptionService=encryptionService;
        this.userService=userService;
    }


    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating CredentialService bean");
    }



    public List<Credentials> getAllCredentials(){

        List<Credentials>  allCredentials= this.credentialMapper.getAllCredentials();

        return allCredentials;


    }

    public List<Credentials>  getAllCredentialsForUser(Integer userid) throws Exception{

        List<Credentials> credentials= this.credentialMapper.getCredentialByUser(userid);
        if (credentials == null){
            throw new Exception();
        }
        return credentials;
    }

    public void addCredential(Credentials credential, Authentication authentication){

        Integer userId= this.userService.getUser(authentication.getName()).getUserId();
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credential.setUserId(userId);
        credential.setKeyItem(encodedKey);
        String encryptedPass= this.encryptionService.encryptValue(credential.getPassWord(),encodedKey);
        credential.setPassWord(encryptedPass);
        this.credentialMapper.addCredential(credential);

    }

    public Credentials findCredentialById(Integer credentialid ){
        Credentials targeCredential= this.credentialMapper.getCredentialById(credentialid);
        return targeCredential;
    }



    public void updateCredential(Credentials credential, Authentication authentication){

        Integer userId= this.userService.getUser(authentication.getName()).getUserId();
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credential.setUserId(userId);
        credential.setKeyItem(encodedKey);
        String encryptedPass= this.encryptionService.encryptValue(credential.getPassWord(),encodedKey);
        credential.setPassWord(encryptedPass);
        this.credentialMapper.updateCredential(credential);
    }


    public void deleteCredential(Integer credentialid){
        this.credentialMapper.deleteCredential(credentialid);
    }
    public String deleteCredential(@PathVariable("id") Integer id, Authentication authentication, Credentials credentialItem, Model model) throws Exception {

        Integer userId= this.userService.getUser(authentication.getName()).getUserId();
        String returnMessage;
        Credentials credential=findCredentialById(id);
        if(credential==null){
            returnMessage= "redirect:/result?error";
            throw new Exception();

        }

        deleteCredential(id);

        returnMessage= "redirect:/result?success";

        return returnMessage;
    }
    public String getDecryptedCredential(Integer id) {
        Credentials credential=this.credentialMapper.getCredentialById(id);
        String decryptedPass= this.encryptionService.decryptValue(credential.getPassWord(),credential.getKeyItem());
        return decryptedPass;
    }
}




