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
 * @Date: 2024/8/3 18:44
 * @email：pfxuchn@gmail.com
 */
package tech.taole.notebook.app.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NoteDto {

    private long id;

    private String title;

    private String content;

    private int authorId;

    private int editorId;

    private Date createTime;

    private Date modifyTime;

    private long parentId;

    private int spaceId;

}
