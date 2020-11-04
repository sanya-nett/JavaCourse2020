package otus.fragments;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

/**
 * Abstract class for common web fragments/elements
 */
public abstract class AbstractFragment {

    protected final WebElement element;
    protected Logger logger = LogManager.getLogger(this.getClass());

    public AbstractFragment(WebElement element) {
        this.element = element;
    }
}
