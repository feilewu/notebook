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
 * @Date: 2024/8/2 23:16
 * @email：pfxuchn@gmail.com
 */
package tech.taole.notebook.app.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Configuration
@MapperScan(basePackages = "tech.taole.notebook.app.dao")
public class MyBatisConfig {

    @Autowired
    private JDBCProperty jdbcProperty;

    @Autowired
    private AppProperty appProperty;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        return factoryBean.getObject();
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(jdbcProperty.getDriverClassName());
        driverManagerDataSource.setUsername(jdbcProperty.getUsername());
        driverManagerDataSource.setPassword(jdbcProperty.getPassword());
        driverManagerDataSource.setUrl(jdbcProperty.getUrl());
        if (Boolean.TRUE.equals(appProperty.getRefreshEnable())) {
            doInitDatabase(driverManagerDataSource);
        }
        return driverManagerDataSource;
    }

    @Bean
    public PlatformTransactionManager getPlatformTransactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager =new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    private void doInitDatabase(DataSource dataSource){
        String fileName = "";
        switch (jdbcProperty.getDriverClassName()) {
            case "org.h2.Driver":
                fileName = "init_h2.sql";
                break;
            default:
                throw new IllegalArgumentException("Unsupported driver:" + jdbcProperty.getDriverClassName());
        }
        String filePath = "sql/" + fileName;
        URL url = getClass().getClassLoader().getResource(filePath);
        if (url == null) {
            throw new IllegalStateException("Can not find file:" + filePath);
        }
        File file = new File(url.getFile());
        runScript(dataSource, file);
    }

    private static void runScript(DataSource dataSource, File file){
        try (Connection connection = dataSource.getConnection();
             FileReader reader = new FileReader(file)) {
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            Resources.setCharset(StandardCharsets.UTF_8);
            // 必须为true，不然容易报错
            scriptRunner.setSendFullScript(true);
            // 执行
            scriptRunner.runScript(reader);
        } catch (IOException | SQLException e) {
            log.warn("Failed to init database!", e);
        }

    }


}
