package org.seasar.struts.hotdeploy;

import org.apache.struts.util.MessageResources;

/**
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public interface ReloadMessageResourcesFactory {

    MessageResources createResources(MessageResources resources);

}
