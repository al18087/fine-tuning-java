package jp.example.www.ai.finetuning.gpt;

import java.util.List;

import com.google.gson.annotations.Expose;

public class Gpt35TrainingMessages {

	@Expose
	private final List<Gpt35TrainingItem> messages;
	
	public Gpt35TrainingMessages(final List<Gpt35TrainingItem> messages) {
		this.messages = messages;
	}

	public List<Gpt35TrainingItem> getMessages() {
		return messages;
	}
}
