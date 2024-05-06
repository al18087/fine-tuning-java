package jp.example.www.ai.finetuning;

public class TrainingWriterFactory {

	public TrainingWriter getTraningWriter(final FineTuningModel model) {
		switch (model) {
		case GPT_3_5_TURBO:
			return new GptTrainingWriter();
		default:
			throw new IllegalArgumentException("ファインチューニングをすることができない学習モデルです。");
		}
	}
}
