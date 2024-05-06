package jp.example.www.ai.finetuning;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jp.example.www.ai.finetuning.gpt.Gpt35RoleType;
import jp.example.www.ai.finetuning.gpt.Gpt35TrainingContent;
import jp.example.www.ai.finetuning.gpt.Gpt35TrainingItem;
import jp.example.www.ai.finetuning.gpt.Gpt35TrainingMessages;

public class GptTrainingWriter implements TrainingWriter {
	
	private static final String SYSTEM_CONTENT = "Please provide java source code.";
	
	@Override
	public void write(final Map<String, String> traningContents, final String filePath)
			throws IOException {
		final List<Gpt35TrainingMessages> contents = new ArrayList<>();
		for (final Entry<String, String> traningContent : traningContents.entrySet()) {
			final List<Gpt35TrainingItem> messages = new ArrayList<>();
			messages.add(new Gpt35TrainingItem(Gpt35RoleType.SYSTEM.getName(), 
					SYSTEM_CONTENT));
			messages.add(new Gpt35TrainingItem(Gpt35RoleType.USER.getName(), 
					traningContent.getKey()));
			messages.add(new Gpt35TrainingItem(Gpt35RoleType.ASSISTANT.getName(), 
					traningContent.getValue()));
			
			final Gpt35TrainingMessages traningMessages = new Gpt35TrainingMessages(messages);
			contents.add(traningMessages);
		}
		
		final TrainingContent traningContent = new Gpt35TrainingContent(contents);
		
		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (final FileWriter writer = new FileWriter(filePath)) {
			gson.toJson(traningContent, Gpt35TrainingContent.class, writer);
		}
	}
}
