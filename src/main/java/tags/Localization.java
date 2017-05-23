package tags;

import constant.Attribute;
import constant.Language;
import service.ResourceManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by 20_ok on 28.03.2017.
 */
public class Localization extends TagSupport {

    private String key;

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(getLocale());
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }


    private String getLocale() {

        String currentLocale = (String) pageContext.getSession().getAttribute(Attribute.LOCALE.getAttribute());
        if (currentLocale == null){
            currentLocale = Language.RU_RU.getLanguage();
        }
        if (currentLocale.equals(Language.EN_US.getLanguage())) {
            return ResourceManager.getResource(key, Language.EN_US.getLanguage());
        } else {
            return ResourceManager.getResource(key, Language.RU_RU.getLanguage());
        }
    }


    public void setKey(String key) {
        this.key = key;
    }
}
