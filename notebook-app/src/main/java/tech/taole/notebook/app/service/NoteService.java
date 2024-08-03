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
package tech.taole.notebook.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.taole.notebook.app.api.dto.CatalogDto;
import tech.taole.notebook.app.api.dto.NoteDto;
import tech.taole.notebook.app.dao.NoteInfoMapper;
import tech.taole.notebook.app.dao.NoteMapper;
import tech.taole.notebook.app.entity.NoteEntity;
import tech.taole.notebook.app.entity.NoteInfoEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private NoteInfoMapper noteInfoMapper;


    @Transactional(rollbackFor = Exception.class)
    public Long addOneNote(NoteDto noteDto) {
        NoteEntity entity = new NoteEntity();
        entity.setTitle(noteDto.getTitle());
        entity.setContent(noteDto.getContent());
        entity.setAuthorId(noteDto.getAuthorId());
        entity.setEditorId(noteDto.getEditorId());
        entity.setCreateTime(noteDto.getCreateTime());
        entity.setModifyTime(noteDto.getModifyTime());
        noteMapper.insertNote(entity);

        long noteId = entity.getId();

        NoteInfoEntity noteInfoEntity = new NoteInfoEntity();
        noteInfoEntity.setNoteId(noteId);
        noteInfoEntity.setParentNoteId(noteDto.getParentId());
        noteInfoEntity.setSpaceId(noteDto.getSpaceId());
        noteInfoMapper.insertNoteInfo(noteInfoEntity);
        return noteId;
    }


    public NoteEntity getOneNote(long noteId) {
        return noteMapper.getNoteById(noteId);
    }


    public List<CatalogDto> getChildrenCatalog(long noteId) {

        List<NoteEntity> childrenNote = noteMapper.getChildrenNote(noteId);

        return childrenNote.stream().map(noteEntity -> {
            CatalogDto catalogDto = new CatalogDto();
            catalogDto.setId(String.valueOf(noteEntity.getId()));
            catalogDto.setName(noteEntity.getTitle());
            return catalogDto;
        }).collect(Collectors.toList());

    }






}
