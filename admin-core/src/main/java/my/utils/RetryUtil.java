package my.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetryUtil {
	private static Logger LOG = LoggerFactory.getLogger(RetryUtil.class);
	
	public static <V> V retryOnNull(int retryLimit, long sleepMillis, java.util.concurrent.Callable<V> retryCallable) throws InterruptedException {

		V v = null;
		for (int i = 0; i < retryLimit; i++) {
			try {
				v = retryCallable.call();
			} catch (Exception e) {
				LOG.warn("retry on " + (i + 1) + " times", e);
			}
			if (v != null) break;
			LOG.warn("retry on " + (i + 1) + " times but return null");
			Thread.sleep(sleepMillis);
		}
		return v;
	}
	
	public static <V extends Boolean> V retryOnFalse(int retryLimit, long sleepMillis, java.util.concurrent.Callable<V> retryCallable) throws InterruptedException {

		V v = null;
		for (int i = 0; i < retryLimit; i++) {
			try {
				v = retryCallable.call();
			} catch (Exception e) {
				LOG.warn("retry on " + (i + 1) + " times", e);
			}
			if (v == true) break;
			LOG.warn("retry on " + (i + 1) + " times but return false");
			Thread.sleep(sleepMillis);
		}
		return v;
	}
}
