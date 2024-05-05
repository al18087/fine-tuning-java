package jp.example.www.ai.message;

public enum MessageType {

	EPOCH_FORMAT_INCORRECT("エポック数が未入力、または入力値に不正があります。", 
			"The number of epochs has not been entered or has been entered incorrectly."),
	
	FILE_NOT_SELECTED("ファイルを選択してください", "Please select a file."),
	
	NOT_JSONL_FILE("jsonlファイルではありません", "It is not a jsonl file."),
	;
	
	private final String content;
	
	private final String logContent;
	
	private MessageType(final String content, final String logContent) {
		this.content = content;
		this.logContent = logContent;
	}
	
	public String getContent(final Object... args) {
		return String.format(content, args);
	}
	
	public String getLogContent() {
		return logContent;
	}
}
