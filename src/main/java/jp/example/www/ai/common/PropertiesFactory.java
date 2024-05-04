package jp.example.www.ai.common;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class PropertiesFactory {

	private static final Properties properties = new Properties();
	
	private static final String OPENAI_PROPERTIES_PATH = "/WEB-INF/conf/openai.properties";
	
	private PropertiesFactory() {}
	
	public static void read(final HttpServletRequest request) throws IOException {
		properties.load(Files.newBufferedReader(Paths.get(request.getServletContext()
				.getRealPath(OPENAI_PROPERTIES_PATH)), StandardCharsets.UTF_8));
	}
	
	public static String getProperty(final String key) {
		return properties.getProperty(key);
	}
}
