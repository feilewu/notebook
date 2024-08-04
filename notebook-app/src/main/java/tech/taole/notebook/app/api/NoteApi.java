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
 * @Date: 2024/7/26 22:06
 * @email：pfxuchn@gmail.com
 */
package tech.taole.notebook.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tech.taole.notebook.app.api.dto.NoteDto;
import tech.taole.notebook.app.entity.NoteEntity;
import tech.taole.notebook.app.service.NoteService;

@RestController
@RequestMapping(value = "/api/note", produces = MediaType.APPLICATION_JSON_VALUE)
public class NoteApi {

    @Autowired
    private NoteService noteService;

    @PostMapping("")
    public Long addNote(@RequestBody NoteDto noteDto) {
        return noteService.addOneNote(noteDto);
    }


    @PutMapping("")
    public Integer updateNote(@RequestBody NoteDto noteDto) {
        return noteService.updateOneNode(noteDto);
    }

    @GetMapping("/{noteId}")
    public NoteEntity getNote(@PathVariable("noteId") String noteId) {
        return noteService.getOneNote(Long.parseLong(noteId));
    }



    

}
