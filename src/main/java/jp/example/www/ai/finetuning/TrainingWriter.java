package jp.example.www.ai.finetuning;

import java.io.IOException;
import java.util.Map;

public interface TrainingWriter {

	public void write(final Map<String, String> traningContents, final String filePath)
			throws IOException;
}
