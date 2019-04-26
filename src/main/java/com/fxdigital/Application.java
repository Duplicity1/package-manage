package com.fxdigital;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@SpringBootApplication
@EnableScheduling
//@PropertySource(value = "file:/usr/local/v5/storeServer/application.properties")
//@PropertySource(value = "file:C:/Users/fx/Desktop/upgradeServer/src/main/resources/application.properties")
public class Application {
    private static Logger log = LoggerFactory.getLogger(Application.class);

    @Value("${server.port}")
    private int serverPort;

    @Value("${server.http.port}")
    private int serverHttpPort;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    /**
     * 设置http请求转换为https
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");//confidential
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(serverHttpPort);
        connector.setSecure(false);
        connector.setRedirectPort(serverPort);
        return connector;
    }

    /**
     * 使用c3p0连接池
     *
     * @return
     * @throws PropertyVetoException
     */
    @Bean
    @Primary
    public DataSource dataSource(ApplicationContext ac, Environment env) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUser(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        // 配置信息
        // #连接池中保留的最小连接数
        dataSource.setMinPoolSize(0);
        // #连接池中保留的最大连接数
        dataSource.setMaxPoolSize(3);
        // 最大空闲时间，超过空闲时间的连接将被丢弃
        dataSource.setMaxIdleTime(600);
        return dataSource;
    }

    /***
     * 对节点的检查
     */
    @Bean
    public Node createContext(DataSource dataSource) {
        Node node = new Node();

        if (!node.init(dataSource)) {
            log.info("无节点信息，启动服务失败！！！");
        }
        return node;
    }
}
