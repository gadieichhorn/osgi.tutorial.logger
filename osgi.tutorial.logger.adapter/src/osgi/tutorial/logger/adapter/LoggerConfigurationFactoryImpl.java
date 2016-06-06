package osgi.tutorial.logger.adapter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import java.util.Map;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.slf4j.LoggerFactory;

@Component(
		name="osgi.tutorial.logger.configuration.factory", 
		configurationPolicy=ConfigurationPolicy.REQUIRE		
		)
public class LoggerConfigurationFactoryImpl {
	 
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoggerConfigurationFactoryImpl.class);
	  
	private String packageName;
    private Level oldLogLevel;
    private Level newLogLevel;

    public LoggerConfigurationFactoryImpl() {
        logger.info("Constractor: [{}]", this);
	}
    
    @Activate
    public void activate(Map<String, String> config) {
    	logger.info("Activate::Config: [{}]", config);
    	try{
            packageName = (String) config.get(LoggerConfigurationFactory.PACKAGE_NAME_CONFIG_PROPERTY);
        	logger.debug("PackageName: [{}]", packageName);
            newLogLevel = Level.toLevel((String) config.get(LoggerConfigurationFactory.LOG_LEVEL_CONFIG_PROPERTY));
            Logger packageLogger = (Logger) LoggerFactory.getLogger(packageName);
            oldLogLevel = packageLogger.getLevel();
            packageLogger.setLevel(newLogLevel);
        	logger.debug("Old Level: [{}]", oldLogLevel);
        	logger.debug("New Level: [{}]", newLogLevel);
            logger.info("New log level [{}] is set for package [{}]", newLogLevel, packageName);    		
    	}catch(Exception ex){
    		logger.error("Failed to initialise component", ex);
    	}
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
        Logger packageLogger = (Logger) LoggerFactory.getLogger(packageName);
        packageLogger.setLevel(oldLogLevel);

        logger.info("Log level {} is reseted for package {}", oldLogLevel, packageName);
    }

    @Modified
    protected void modified(ComponentContext context) {
        String pn = (String) context.getProperties().get(LoggerConfigurationFactory.PACKAGE_NAME_CONFIG_PROPERTY);
        newLogLevel = Level.toLevel((String) context.getProperties().get(LoggerConfigurationFactory.LOG_LEVEL_CONFIG_PROPERTY));
        // reset old package value
        if (!this.packageName.equals(pn)) {
            Logger packageLogger = (Logger) LoggerFactory.getLogger(this.packageName);
            packageLogger.setLevel(oldLogLevel);
            this.packageName = pn;
        }

        Logger packageLogger = (Logger) LoggerFactory.getLogger(this.packageName);
        packageLogger.setLevel(newLogLevel);

        logger.info("New log level {} is set for package {}", newLogLevel, this.packageName);
    }    
}
