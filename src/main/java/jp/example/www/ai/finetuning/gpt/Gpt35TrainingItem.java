package jp.example.www.ai.finetuning.gpt;

import com.google.gson.annotations.Expose;

public class Gpt35TrainingItem {

	@Expose
	private final String role;
	
	@Expose
	private final String content;
	
	public Gpt35TrainingItem(final String role, final String content) {
		this.role = role;
		this.content = content;
	}

	public String getRole() {
		return role;
	}

	public String getContent() {
		return content;
	}
}
