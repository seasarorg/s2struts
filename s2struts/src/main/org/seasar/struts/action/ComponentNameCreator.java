package org.seasar.struts.action;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.container.S2Container;

/**
 * ActionMappingから、コンポート名を作成するためのインターフェース。
 * 
 * @author Satoshi Kimura
 */
public interface ComponentNameCreator {
    /**
     * ActionMappingからコンポーネント名を、作成し、そのコンポーネント名が使用対象のコンテナに見つからなければ、 pathをコンポーネント名として返す。
     * 
     * @param container 検索対象のコンテナ
     * @param mapping コンテナ名を作成するのに使用するマッピング
     * @return コンテナ名
     */
    String createComponentName(S2Container container, ActionMapping mapping);
}