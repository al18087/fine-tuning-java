package jp.example.www.ai;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public enum ActionType {

	/**
	 * 推論.
	 */
	INFERENCE("inference"),
	
	/**
	 * OpenAIのAPI Key.
	 */
	API_KEY("api-key"),
	
	/**
	 * ファインチューニング.
	 */
	FINE_TUNING("fine-tuning"),
	
	/**
	 * 学習済みモデル.
	 */
	LEARNED_MODEL("learned-model"),
	;
	
	private final String name;
	
	private ActionType(final String name) {
		this.name = name;
	}
	
	private String getName() {
		return name;
	}
	
	public static ActionType getAction(final String name) {
		return Arrays
			.stream(ActionType.values())
			.filter(e -> StringUtils.equals(e.getName(), name))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(name + "というactionは存在しません"));
	}
}
