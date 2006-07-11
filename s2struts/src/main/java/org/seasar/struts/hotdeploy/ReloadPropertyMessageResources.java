package org.seasar.struts.hotdeploy;

import java.util.Locale;

import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResources;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ReloadPropertyMessageResources extends PropertyMessageResources {

    private static final long serialVersionUID = -5947088394904599401L;

    public ReloadPropertyMessageResources(MessageResourcesFactory factory, String config) {
        super(factory, config);
        log.trace("Initializing, config='" + config + "'");
    }

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
