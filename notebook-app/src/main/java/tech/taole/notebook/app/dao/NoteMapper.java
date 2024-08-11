/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * @Author: pf_xu
 * @Date: 2024/8/2 23:25
 * @email：pfxuchn@gmail.com
 */
package tech.taole.notebook.app.dao;

import org.apache.ibatis.annotations.*;
import tech.taole.notebook.app.entity.NoteEntity;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("select count(*) from notes")
    int getCount();

    @Insert("insert into notes (N_TITLE, N_CONTENT, AUTHOR_ID, EDITOR_ID, CREATE_TIME, MODIFY_TIME) " +
            "values (#{title}, #{content}, #{authorId}, #{editorId}, #{createTime}, #{modifyTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "N_ID")
    int insertNote(NoteEntity noteEntity);


    @Update("update notes set N_TITLE=#{title}, N_CONTENT=#{content}, AUTHOR_ID=#{authorId}, EDITOR_ID=#{editorId}," +
            " CREATE_TIME=#{createTime}, MODIFY_TIME=#{modifyTime} where N_ID=#{id}")
    int updateNoteById(NoteEntity noteEntity);


    @Results(id = "noteResultMap", value = {
            @Result(id = true, column = "N_ID", property = "id"),
            @Result(column = "N_TITLE", property = "title"),
            @Result(column = "N_CONTENT", property = "content"),
            @Result(column = "AUTHOR_ID", property = "authorId"),
            @Result(column = "EDITOR_ID", property = "editorId"),
            @Result(column = "CREATE_TIME", property = "createTime"),
            @Result(column = "MODIFY_TIME", property = "modifyTime")
    })
    @Select("select * from notes where N_ID=#{noteId}")
    NoteEntity getNoteById(Long noteId);


    @Results(id = "catalogResultMap", value = {
            @Result(column = "N_ID", property = "id"),
            @Result(column = "N_TITLE", property = "title")

    })
    @Select("select NOTES.N_ID, NOTES.N_TITLE from NOTES inner join NOTES_INFO as INFO on NOTES.N_ID=INFO.N_ID " +
            "where INFO.PARENT_NOTE_ID=#{noteId}")
    List<NoteEntity> getChildrenNote(Long noteId);

    @Delete("delete from NOTES where NOTES.N_ID=#{noteId}")
    int deleteNodeById(Long noteId);

}
