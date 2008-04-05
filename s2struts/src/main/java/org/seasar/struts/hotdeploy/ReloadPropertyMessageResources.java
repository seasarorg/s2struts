package org.seasar.struts.hotdeploy;

import java.util.Locale;

import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResources;

/**
 * メッセージのプロパティを再ロードします。
 * 
 * @author Katsuhiko Nagashima
 */
public class ReloadPropertyMessageResources extends PropertyMessageResources {

    private static final long serialVersionUID = -5947088394904599401L;

    /**
     * インスタンスを構築します。
     * 
     * @param factory
     * @param config
     */
    public ReloadPropertyMessageResources(MessageResourcesFactory factory, String config) {
        super(factory, config);
        log.trace("Initializing, config='" + config + "'");
    }

    /**
     * インスタンスを構築します。
     * 
     * @param factory
     * @param config
     * @param returnNull
     */
    public ReloadPropertyMessageResources(MessageResourcesFactory factory, String config, boolean returnNull) {
        super(factory, config, returnNull);
        log.trace("Initializing, config='" + config + "', returnNull=" + returnNull);
    }

    public String getMessage(Locale locale, String key) {

        // synchronized?
        this.locales.clear();
        this.messages.clear();
        this.formats.clear();

        return super.getMessage(locale, key);
    }

}
