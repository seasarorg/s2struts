package org.seasar.struts.hotdeploy;

import org.apache.struts.util.MessageResources;

/**
 * リロード可能な{@link MessageResources}を作成するファクトリです。
 * 
 * @author Katsuhiko Nagashima
 * 
 */
public interface ReloadMessageResourcesFactory {

    /**
     * {@link MessageResources}を作成します。
     * 
     * @param resources
     * @return
     */
    MessageResources createResources(MessageResources resources);

}
