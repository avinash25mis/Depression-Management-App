/*
package com;

import com.ApplicationStarter;
import com.configuration.DefaultProfileConfig;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



public class ApplicationWebXml extends SpringBootServletInitializer {


*/
/*This will be invoked only when the application is deployed to a Servlet container
 like Tomcat, JBoss etc.an alternative to creating a {@code web.xml}*//*


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // set a default to use when no profile is configured.
        DefaultProfileConfig.addDefaultProfile(application.application());
        return application.sources(ApplicationStarter.class);
    }
}
*/
