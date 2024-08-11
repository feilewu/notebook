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
 * @Date: 2024/8/3 20:54
 * @email：pfxuchn@gmail.com
 */
package tech.taole.notebook.app.dao;

import org.apache.ibatis.annotations.*;
import tech.taole.notebook.app.entity.NoteInfoEntity;

@Mapper
public interface NoteInfoMapper {


    @Insert("insert into NOTES_INFO (N_ID, PARENT_NOTE_ID, S_ID) " +
            "values (#{noteId}, #{parentNoteId}, #{spaceId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "ID")
    int insertNoteInfo(NoteInfoEntity noteInfoEntity);

    @Results(id = "noteInfoResultMap", value = {
            @Result(id = true, column = "ID", property = "id"),
            @Result(column = "N_ID", property = "noteId"),
            @Result(column = "PARENT_NOTE_ID", property = "parentNoteId"),
            @Result(column = "S_ID", property = "spaceId")
    })
    @Select("select * from NOTES_INFO where N_ID=#{noteId}")
    NoteInfoEntity getNoteINFOById(Long noteId);


}
