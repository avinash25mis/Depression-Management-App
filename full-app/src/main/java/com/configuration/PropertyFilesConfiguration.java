package com.configuration;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author avinash.a.mishra
 */
@Component
@PropertySource({"classpath:messages/messages.properties","classpath:messages/notifications.properties"})
public class PropertyFilesConfiguration {


}
