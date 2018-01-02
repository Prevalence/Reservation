package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class userTagHandler extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}

	@Override
	public int doAfterBody() throws JspException {
		return super.doAfterBody();
	}

	@Override
	public int doEndTag() throws JspException {
		if (null == pageContext.getSession() || null == pageContext.getSession().getAttribute("loginUserName")) {
			try {
				getPreviousOut().write("Please login first!<br><a href=\"login.jsp\">Login Here</a>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return SKIP_PAGE;
		}
		return EVAL_PAGE;
	}
}
