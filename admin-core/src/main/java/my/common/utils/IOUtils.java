package my.common.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.util.Assert;

import my.common.io.FastByteArrayOutputStream;

public class IOUtils {
	
	/** 默认缓存大小 */
	public static final int DEFAULT_BUFFER_SIZE = 1024;
	/** 默认中等缓存大小 */
	public static final int DEFAULT_MIDDLE_BUFFER_SIZE = 4096;
	/** 默认大缓存大小 */
	public static final int DEFAULT_LARGE_BUFFER_SIZE = 8192;
	
	/** 数据流末尾 */
	public static final int EOF = -1;
	
	public static long copy(InputStream in, OutputStream out) throws IOException {
		return copy(in, out, DEFAULT_BUFFER_SIZE);
	}
	
	public static long copy(InputStream in, OutputStream out, int bufferSize) throws IOException {
		Assert.notNull(in, "InputStream is null !");
		Assert.notNull(out, "OutputStream is null !");
		if (bufferSize <= 0) {
			bufferSize = DEFAULT_BUFFER_SIZE;
		}

		byte[] buffer = new byte[bufferSize];
		long size = 0;

		try {
			for (int readSize = -1; (readSize = in.read(buffer)) != EOF;) {
				out.write(buffer, 0, readSize);
				size += readSize;
				out.flush();
			}
		} catch (IOException e) {
			throw e;
		}
		return size;
	}
	
	/**
	 * 从流中读取bytes
	 * 
	 * @param in {@link InputStream}
	 * @return bytes
	 * @throws IORuntimeException IO异常
	 */
	public static byte[] readBytes(InputStream in) throws IOException {
		final FastByteArrayOutputStream out = new FastByteArrayOutputStream();
		copy(in, out);
		return out.toByteArray();
	}
	
	public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }
}
