package osgi.tutorial.logger.adapter;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class JulToSlf4jActivator implements BundleActivator {

	 private static final Logger logger = LoggerFactory.getLogger(JulToSlf4jActivator.class);

	    @Override
	    public void start(BundleContext context) throws Exception {
	        if (!SLF4JBridgeHandler.isInstalled()) {
	            SLF4JBridgeHandler.removeHandlersForRootLogger();
	            SLF4JBridgeHandler.install();
	            logger.info("INSTALLED");
	        } else {
	            logger.info("NOT INSTALLED");
	        }
	    }

	    @Override
	    public void stop(BundleContext context) throws Exception {
	        if (SLF4JBridgeHandler.isInstalled()) {
	            SLF4JBridgeHandler.uninstall();
	            SLF4JBridgeHandler.removeHandlersForRootLogger();
	            logger.info("UNINSTALLED");
	        } else {
	            logger.info("NOT INSTALLED");
	        }
	    }
}
