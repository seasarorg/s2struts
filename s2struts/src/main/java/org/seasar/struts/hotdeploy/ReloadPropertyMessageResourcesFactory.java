package org.seasar.struts.hotdeploy;

import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public class ReloadPropertyMessageResourcesFactory extends MessageResourcesFactory implements
        ReloadMessageResourcesFactory {

    private static final long serialVersionUID = -1968401236492401918L;

    public MessageResources createResources(String config) {
        return new ReloadPropertyMessageResources(this, config, this.returnNull);
    }

    public MessageResources createResources(MessageResources resources) {
        ReloadPropertyMessageResources result = new ReloadPropertyMessageResources(this, resources.getConfig(),
                resources.getReturnNull());
        result.setEscape(resources.isEscape());
        return result;
    }

}
