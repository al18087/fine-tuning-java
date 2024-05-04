package jp.example.www.ai.servlet;

import java.io.IOException;
import java.time.Duration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

import jp.example.www.ai.ActionType;
import jp.example.www.ai.common.JspPageConst;
import jp.example.www.ai.common.PropertiesConst;
import jp.example.www.ai.common.PropertiesFactory;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/Main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(JspPageConst.MAIN).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final ActionType action = ActionType.getAction(request.getParameter("action"));
		switch (action) {
		case INFERENCE:
			PropertiesFactory.read(request);
			final String apiKey = PropertiesFactory.getProperty(PropertiesConst.OPENAI_API_KEY);
			final String learnedModelName = PropertiesFactory.getProperty(PropertiesConst.LEARNED_MODEL_NAME);
			final boolean echo = Boolean.valueOf(PropertiesFactory.getProperty(PropertiesConst.ECHO));
			final int maxTokenNum = Integer.parseInt(PropertiesFactory.getProperty(PropertiesConst.MAX_TOKEN_NUM));
			final double temperature = Double.parseDouble(PropertiesFactory.getProperty(PropertiesConst.TEMPERATURE));
			final String suffix = PropertiesFactory.getProperty(PropertiesConst.SUFFIX);
			final String content = request.getParameter("user-content");
			
			final OpenAiService service = new OpenAiService(apiKey, Duration.ZERO);
			final CompletionRequest completionRequest = CompletionRequest
					.builder()
					.model(learnedModelName)
					.echo(echo)
					.maxTokens(maxTokenNum)
					.temperature(temperature)
					.suffix(suffix)
					.prompt(content)
					.build();
			final String suggestion = service
					.createCompletion(completionRequest)
					.getChoices()
					.get(0)
					.getText();
			
			request.setAttribute("suggestion", suggestion);
			request.getRequestDispatcher(JspPageConst.MAIN).forward(request, response);
			return;
		
		default:
			throw new IllegalStateException("指定されたアクションが存在しません");
		}
	}

}
