package com.pankov.roadtosenior.onlineshop;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.pankov.roadtosenior.onlineshop.dao.ProductDao;
import com.pankov.roadtosenior.onlineshop.dao.UserDao;
import com.pankov.roadtosenior.onlineshop.dao.jdbc.ConnectionFactory;
import com.pankov.roadtosenior.onlineshop.dao.jdbc.JdbcUserDao;
import com.pankov.roadtosenior.onlineshop.dao.jdbc.JdbcProductDao;
import com.pankov.roadtosenior.onlineshop.service.ProductService;
import com.pankov.roadtosenior.onlineshop.service.UserService;
import com.pankov.roadtosenior.onlineshop.util.CachedPropertiesReader;
import com.pankov.roadtosenior.onlineshop.web.filter.AdminFilter;
import com.pankov.roadtosenior.onlineshop.web.filter.GuestFilter;
import com.pankov.roadtosenior.onlineshop.security.SecurityService;
import com.pankov.roadtosenior.onlineshop.web.filter.UserFilter;
import com.pankov.roadtosenior.onlineshop.web.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import javax.sql.DataSource;
import java.util.EnumSet;
import java.util.Properties;

public class Starter {
    public static void main(String[] args) throws Exception {

        CachedPropertiesReader propertiesReader = new CachedPropertiesReader("application.properties");
        Properties properties = propertiesReader.getCachedProperties();
        Long sessionTimeToLive = propertiesReader.getLongProperty("sessionTimeToLive");

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(properties.getProperty("db.url"));
        dataSource.setUser(properties.getProperty("db.username"));
        dataSource.setPassword(properties.getProperty("db.password"));
//        DataSource connectionFactory = new ConnectionFactory(properties);
        ProductDao jdbcProductDao = new JdbcProductDao(dataSource);
        UserDao jdbUserDao = new JdbcUserDao(dataSource);

        ProductService productService = new ProductService();
        productService.setJdbcProductDao(jdbcProductDao);

        UserService userService = new UserService();
        SecurityService securityService = new SecurityService();
        userService.setUserDao(jdbUserDao);
        userService.setSecurityService(securityService);
        userService.setProductService(productService);
        securityService.setUserService(userService);
        securityService.setSessionTimeToLive(sessionTimeToLive);

        ProductServlet productServlet = new ProductServlet(productService);
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(productServlet), "/products");
        contextHandler.addServlet(new ServletHolder(productServlet), "");
        contextHandler.addServlet(new ServletHolder(new AddProductServlet(productService)), "/product/add");
        contextHandler.addServlet(new ServletHolder(new EditProductServlet(productService)), "/product/edit");
        contextHandler.addServlet(new ServletHolder(new LoginServlet(securityService)), "/login");
        contextHandler.addServlet(new ServletHolder(new CartServlet(securityService)), "/cart");
        contextHandler.addServlet(new ServletHolder(new AddProductToCartServlet(userService)), "/cart/add");
        contextHandler.addServlet(new ServletHolder(new RemoveProductFromCartServlet(userService)), "/cart/remove");

        contextHandler.addFilter(new FilterHolder(new AdminFilter(securityService)),
                "/product/*", EnumSet.of(DispatcherType.REQUEST));

        contextHandler.addFilter(new FilterHolder(new UserFilter(securityService)),
                "/cart/*", EnumSet.of(DispatcherType.REQUEST));

        contextHandler.addFilter(new FilterHolder(new GuestFilter(securityService)),
                "/", EnumSet.of(DispatcherType.REQUEST));
        contextHandler.addFilter(new FilterHolder(new GuestFilter(securityService)),
                "/products", EnumSet.of(DispatcherType.REQUEST));
        contextHandler.addFilter(new FilterHolder(new GuestFilter(securityService)),
                "/search/*", EnumSet.of(DispatcherType.REQUEST));

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();
    }
}