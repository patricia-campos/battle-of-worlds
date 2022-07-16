package br.com.dbc.trabalhofinalmodulo2.banco;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DbConfiguration {

    @Value("${jdbc-string}")
    private String jdbcString;

    @Value("${jdbc-user}")
    private String user;

    @Value("${jdbc-password}")
    private String pass;

    @Value("${jdbc-schema}")
    private String schema;

    @Bean
    @RequestScope
    public Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(jdbcString, user, pass);

        con.createStatement().execute("alter session set current_schema=" + schema);

        return con;
    }
}
