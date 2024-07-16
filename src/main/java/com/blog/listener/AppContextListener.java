package com.blog.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class AppContextListener implements ServletContextListener {

    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Initialize data source
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/BlogDB");
            sce.getServletContext().setAttribute("dataSource", dataSource);
            System.out.println("Application initialized and DataSource set.");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Perform cleanup, if necessary
        System.out.println("Application destroyed.");
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
