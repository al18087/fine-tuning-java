package jp.example.www.ai.finetuning.gpt;

public enum Gpt35RoleType {

	SYSTEM("system"),
	
	USER("user"),
	
	ASSISTANT("assistant"),
	;
	
	private final String name;
	
	private Gpt35RoleType(final String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
