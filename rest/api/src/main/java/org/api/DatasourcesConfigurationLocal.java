package org.api;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.api")
@Profile("local")
public class DatasourcesConfigurationLocal {

    @org.springframework.beans.factory.annotation.Value("file:src/test/resources/schema.sql")
    private org.springframework.core.io.Resource sqlScriptSchema;

    @org.springframework.beans.factory.annotation.Value("file:src/test/resources/data.sql")
    private Resource sqlScriptData;

    @Value("${spring.datasource.datasource-initializer-enabled:false}")
    private boolean dataSourceInitializerEnabled;

    @Bean
    public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(databasePopulator());
        dataSourceInitializer.setEnabled(dataSourceInitializerEnabled);
        return dataSourceInitializer;
    }

    private DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(sqlScriptSchema);
        databasePopulator.addScript(sqlScriptData);
        databasePopulator.setSeparator(";");
        return databasePopulator;
    }
}