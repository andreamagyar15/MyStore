package ssit.java0.springMVC.config;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {
        return new JdbcTemplate(dataSource());
    }
    @Bean
    public DataSource dataSource() {
        String url=new StringBuilder()
                .append("jdbc:")
                .append("postgresql")
                .append("://")
                .append("127.0.0.1")
                .append(":")
                .append(5432)
                .append("/MyStore")
                .append("?user=")
                .append("postgres")
                .append("&password=")
                .append("testPASS").toString();
        return new SingleConnectionDataSource(url,false);
    }
}
