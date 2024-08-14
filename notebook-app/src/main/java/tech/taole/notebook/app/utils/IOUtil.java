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
 * @Date: 2024/8/14 20:08
 * @email：pfxuchn@gmail.com
 */
package tech.taole.notebook.app.utils;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

public class IOUtil {

    public static String getJarPath(Class<?> clazz) {
        String filePath = null;
        URL url = clazz.getProtectionDomain().getCodeSource().getLocation();
        try {
            filePath = URLDecoder.decode(url.getPath(), "utf-8");// 转化为utf-8编码，支持中文
            if (filePath.endsWith(".jar")) {

                // 可执行jar包运行的结果里包含".jar"
                // 获取jar包所在目录
                filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
                File file = new File(filePath);
                filePath = file.getAbsolutePath();//得到windows下的正确路径
            }
        } catch (Exception ignored) {
        }
        return filePath;
    }





}
