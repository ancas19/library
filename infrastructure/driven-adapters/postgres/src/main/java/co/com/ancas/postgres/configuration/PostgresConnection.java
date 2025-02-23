package co.com.ancas.postgres.configuration;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "co.com.ancas.postgres.repositories",
        entityManagerFactoryRef = "libraryEntityManager",
        transactionManagerRef = "libraryTransactionManager"
)
public class PostgresConnection {
    @Value("${spring.jpa.show-sql}")
    private String showSql;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hbm2ddlAuto;
    @Value("${postgres.dialect}")
    private String dialect;
    @Value("${postgres.driverClassName}")
    private String className;
    @Value("${postgres.url}")
    private String jdbcUrl;
    @Value("${postgres.user}")
    private String jdbcUser;
    @Value("${postgres.pass}")
    private String jdbcPass;

    @Bean
    public DataSource dataSource()  {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(className);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(jdbcUser);
        dataSource.setPassword(jdbcPass);
        dataSource.setMaximumPoolSize(10);
        dataSource.setMinimumIdle(5);
        dataSource.setIdleTimeout(30000);
        dataSource.setMaxLifetime(1800000);
        dataSource.setConnectionTimeout(20000);
        return dataSource;
    }


    @Bean(name = "libraryEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("co.com.ancas.postgres.entities");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.show_sql",showSql);
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        em.setJpaProperties(properties);
        return em;
    }

    @Bean(name = "libraryTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(@Qualifier("libraryEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
