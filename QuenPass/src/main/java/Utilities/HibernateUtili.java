/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtili {

    private final static SessionFactory FACTORY;

    static {

        // Configuration configuration = new Configuration();
        Configuration configuration = new Configuration();
        java.util.Properties properties = new java.util.Properties();
        configuration.setProperties(properties);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=QL_DUAN1");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123");

        configuration.setProperties(properties);
        configuration.addAnnotatedClass(DomainModels.NhanVien.class);

        org.hibernate.service.ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        FACTORY = configuration.buildSessionFactory(registry);
    }

    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }
}
