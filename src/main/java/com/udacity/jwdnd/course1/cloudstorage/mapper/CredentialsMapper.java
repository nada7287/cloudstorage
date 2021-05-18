package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {

    @Select("SELECT * FROM CREDENTIALS")
    List<Credentials> getAllCredentials();

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credentials> getCredentialByUser( Integer userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid=#{id}")
    Credentials getCredentialById(Integer id);

    @Insert("INSERT INTO CREDENTIALS (url,username,key,password,userid) VALUES(#{credentials.urlLink}, #{credentials.userName}, #{credentials.keyItem},#{credentials.passWord},#{credentials.userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredential(@Param("credentials") Credentials credentials);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid=#{credentialId}")
    void deleteCredential( Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url= #{credential.urlLink} ,username= #{credential.userName}, key= #{credential.keyItem},password=#{credential.passWord},userid=#{credential.userId} WHERE credentialid=#{credential.credentialId}")
    void updateCredential(@Param("credential") Credentials credential);


}
