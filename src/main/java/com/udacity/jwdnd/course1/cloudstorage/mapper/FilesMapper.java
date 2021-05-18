
package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    Files getFileById(Integer fileId);

    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    Files getFileByName(String fileName);

    @Select("SELECT * FROM FILES")
    List<Files> getAllFiles();

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<Files> getFilesByUser( Integer userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES( #{file.fileName}, #{file.contentType},#{file.fileSize}, #{file.userId},  #{file.fileData} )")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(@Param("file") Files file);

    @Update("UPDATE FILES SET filename = #{file.fileName}, contenttype = #{file.contentType} ,filesize = #{file.fileSize}, filedata = #{file.fileData} WHERE fileId= #{file.fileId}")

    int updateFile(@Param("file") Files file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int deleteFile(Integer fileId);

}



