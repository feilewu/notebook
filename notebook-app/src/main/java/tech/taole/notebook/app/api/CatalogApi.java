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
 * @Date: 2024/7/18 22:58
 * @email：pfxuchn@gmail.com
 */
package tech.taole.notebook.app.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tech.taole.notebook.app.entity.CatalogEntity;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/catalog", produces = MediaType.APPLICATION_JSON_VALUE)
public class CatalogApi {


    @GetMapping("/{id}")
    public List<CatalogEntity> getChildren(@PathVariable("id") String id) {
        return Arrays.asList(new CatalogEntity("1", "你好dada1"), new CatalogEntity("2", "你好dadada2"));
    }

}
