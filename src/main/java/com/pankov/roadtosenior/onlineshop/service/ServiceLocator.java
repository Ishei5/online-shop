package com.pankov.roadtosenior.onlineshop.service;

import com.pankov.roadtosenior.JdbcTemplate;
import com.pankov.roadtosenior.onlineshop.dao.ProductDao;
import com.pankov.roadtosenior.onlineshop.dao.UserDao;
import com.pankov.roadtosenior.onlineshop.dao.jdbc.JdbcProductDao;
import com.pankov.roadtosenior.onlineshop.dao.jdbc.JdbcUserDao;
import com.pankov.roadtosenior.onlineshop.security.SecurityService;
import com.pankov.roadtosenior.onlineshop.util.CachedPropertiesReader;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.ds.PGSimpleDataSource;
//import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class ServiceLocator {

    private static final Map<Class<?>, Object> SERVICES = new HashMap<>();

    static {
        CachedPropertiesReader propertiesReader = new CachedPropertiesReader("application.properties");
        Properties properties = propertiesReader.getProperties();
        Long sessionTimeToLive = propertiesReader.getLongProperty("sessionTimeToLive");

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(properties.getProperty("db.url"));
        dataSource.setUser(properties.getProperty("db.username"));
        dataSource.setPassword(properties.getProperty("db.password"));
        dataSource.setCurrentSchema(properties.getProperty("db.schema"));

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        ProductDao jdbcProductDao = new JdbcProductDao(jdbcTemplate);
        UserDao jdbcUserDao = new JdbcUserDao(jdbcTemplate);

        ProductService productService = new ProductService(jdbcProductDao);
        addService(ProductService.class, productService);

        UserService userService = new UserService(jdbcUserDao);
        addService(UserService.class, userService);

        SecurityService securityService = new SecurityService();
        securityService.setSessionTimeToLive(sessionTimeToLive);
        addService(SecurityService.class, securityService);

        CartService cartService = new CartService();
        addService(CartService.class, cartService);
    }

    public static <T> T getService(Class<T> serviceType) {
        return serviceType.cast(SERVICES.get(serviceType));
    }

    public static <T> void addService(Class<T> serviceType, Object service) {
        SERVICES.put(serviceType, service);
    }
}
