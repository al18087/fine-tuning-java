package jp.example.www.ai.finetuning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainingJsonFile {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TrainingJsonFile.class);
	
	public static void main(final String[] args) {
		final Map<String, String> traningContents = getContent(
				new File("C:\\traningJavaFile"));
		final TrainingWriterFactory factory = new TrainingWriterFactory();
		final TrainingWriter writer = factory.getTraningWriter(FineTuningModel.GPT_3_5_TURBO);
		try {
			writer.write(traningContents, "C:\\jsonformat\\outputTest.json");
		} catch (final IOException e) {
			LOGGER.error("jsonファイルの書き込みに失敗しました");
			e.printStackTrace();
		}
	}
	
	/**
	 * 学習データを取得する.
	 * 
	 * @param dir システムの回答が存在しているディレクトリ
	 * @return 学習データ {@literal Map<ユーザの入力文, ユーザの入力文に対するシステムの回答>}
	 */
	private static Map<String, String> getContent(final File dir) {
		final List<File> files = Arrays.asList(dir.listFiles());
		Collections.sort(files, new Comparator<File>() {
			@Override
			public int compare(final File file1, final File file2) {
				return file1.getName().compareTo(file2.getName());
			}
		});
		if (CollectionUtils.isEmpty(files)) {
			return null;
		}
	
		final List<String> inputStatements = getInputStatements();
		if (CollectionUtils.size(files) != CollectionUtils.size(inputStatements)) {
			return null;
		}
		
		final Map<String, String> content = new HashMap<>();
		for (int i = 0; i < files.size(); i++) {
			try (final FileReader fileReader = new FileReader(files.get(i).getAbsolutePath());
					final BufferedReader bufferedReader = new BufferedReader(fileReader)) {
				
				final StringBuilder sb = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					if (line.contains("package")) {
						continue;
					}
					sb.append(line + "\n");
				}
				
				content.put(inputStatements.get(i), sb.toString());
				
			} catch (final IOException e) {
				System.err.println("ファイルの読み込み中にエラーが発生しました: " + e.getMessage());
			}
		}
		
		return content;
	}
	
	/**
	 * 入力文を取得する.<br>
	 * ※Filesの要素数と等しくなるようにすること
	 * 
	 * @return 入力文
	 */
	private static List<String> getInputStatements() {
		return Arrays.asList(
				"指定された「書類ID」と「フォームコード」を条件に、書類検索をして、「書類ID」,「書類状態」,「書類の申請ユーザのユーザコード」を返してください",
				"指定された「書類ID」と「フォームコード」で書類検索をし、「書類ID」,「書類状態」,「書類の申請ユーザのユーザコード」を取得する処理を実装してください",
				"「書類ID」と「フォームコード」を条件に書類検索をして、「書類ID」,「書類状態」,「書類の申請ユーザコード」を取得してください"
				);
	}
}
