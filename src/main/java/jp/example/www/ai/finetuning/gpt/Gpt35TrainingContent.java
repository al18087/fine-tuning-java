package jp.example.www.ai.finetuning.gpt;

import java.util.List;

import com.google.gson.annotations.Expose;

import jp.example.www.ai.finetuning.TrainingContent;

public class Gpt35TrainingContent implements TrainingContent {

	@Expose
	private final List<Gpt35TrainingMessages> content;
	
	public Gpt35TrainingContent(final List<Gpt35TrainingMessages> content) {
		this.content = content;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gpt35TrainingMessages> getContent() {
		return content;
	}
}
