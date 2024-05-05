package jp.example.www.ai.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.theokanning.openai.fine_tuning.FineTuningJobRequest;
import com.theokanning.openai.fine_tuning.Hyperparameters;
import com.theokanning.openai.service.OpenAiService;

import jp.example.www.ai.ActionType;
import jp.example.www.ai.common.JspPageConst;
import jp.example.www.ai.common.PropertiesConst;
import jp.example.www.ai.common.PropertiesFactory;
import jp.example.www.ai.exception.UploadFileException;
import jp.example.www.ai.message.MessageType;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/Setting")
@MultipartConfig(maxFileSize=102400000)
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final Logger logger = LoggerFactory.getLogger(SettingServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(JspPageConst.SETTING).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) 
			throws ServletException, IOException {
		final ActionType actionType = ActionType.getAction(request.getParameter("action"));
		if (actionType == ActionType.FINE_TUNING) {
			try {
				final String uploadJsonlPath = uploadTrainingFile(request);
				PropertiesFactory.read(request);
				final String apiKey = PropertiesFactory.getProperty(PropertiesConst.OPENAI_API_KEY);
				final Integer epochNum = Integer.parseInt(request.getParameter("epoch"));
				final String traningModel = request.getParameter("traning-model");
				
				final OpenAiService service = new OpenAiService(apiKey, Duration.ZERO);
				final com.theokanning.openai.file.File jsonlFile = service
						.uploadFile("fine-tune", uploadJsonlPath);
				final Hyperparameters parameters = Hyperparameters.builder().nEpochs(epochNum).build();
				final FineTuningJobRequest ftjRequest = FineTuningJobRequest.builder()
						.model(jsonlFile.getId())
						.trainingFile(traningModel)
						.hyperparameters(parameters)
						.build();
				service.createFineTuningJob(ftjRequest);
				
			} catch (final NumberFormatException e) {
				getLogger().error(MessageType.EPOCH_FORMAT_INCORRECT.getLogContent(), e);
				request.setAttribute("message", MessageType.EPOCH_FORMAT_INCORRECT.getContent());
			} catch (final UploadFileException e) {
				request.setAttribute("message", e.getLocalizedMessage());
			} finally {
				request.getRequestDispatcher(JspPageConst.SETTING).forward(request, response);
			}
		} else {
			throw new IllegalArgumentException("actionが存在しません。");
		}
	}
	
	private String uploadTrainingFile(final HttpServletRequest request) 
			throws IOException, ServletException, UploadFileException {
		final Part part = request.getPart("upload");
		final String fileName = Paths.get(part.getSubmittedFileName())
				.getFileName().toString();
		validateUploadTrainingFile(fileName);
		final String uploadJsonlPath = new StringBuilder(
				getServletContext().getRealPath("/WEB-INF/upload"))
				.append(File.separator)
				.append(fileName)
				.toString();
		part.write(uploadJsonlPath);
		
		return uploadJsonlPath;
	}
	
	private void validateUploadTrainingFile(final String fileName) throws UploadFileException {
		if (StringUtils.isEmpty(fileName)) {
			getLogger().error(MessageType.FILE_NOT_SELECTED.getLogContent());
			throw new UploadFileException(MessageType.FILE_NOT_SELECTED);
		}
		
		if (!fileName.endsWith(".jsonl")) {
			getLogger().error(MessageType.NOT_JSONL_FILE.getLogContent());
			throw new UploadFileException(MessageType.NOT_JSONL_FILE);
		}
	}

	Logger getLogger() {
		return logger;
	}
}
