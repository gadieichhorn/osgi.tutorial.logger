package osgi.tutorial.logger.foo.provider;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
@Component(name = "osgi.tutorial.logger.foo", immediate=true)
public class FooImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(FooImpl.class);

	public FooImpl() {
		logger.error("Error");
		logger.warn("Warn");
		logger.info("Info");
		logger.debug("Debug");
		logger.trace("Trace");
	}
}
