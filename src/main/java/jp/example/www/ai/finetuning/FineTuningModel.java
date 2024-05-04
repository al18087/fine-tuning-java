package jp.example.www.ai.finetuning;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public enum FineTuningModel {

	GPT_3_5_TURBO("gpt-3.5-turbo"),
	
	BAGGAGE_002("baggage_002"),
	
	DAVINCH_002("davinci-002"),
	;
	
	private final String name;
	
	private FineTuningModel(final String name) {
		this.name = name;
	}
	
	private String getName() {
		return name;
	}
	
	public static FineTuningModel getModel(final String name) {
		return Arrays
				.stream(FineTuningModel.values())
				.filter(e -> StringUtils.equals(e.getName(), name))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(name + "というmodelは存在しません"));
	}
}
