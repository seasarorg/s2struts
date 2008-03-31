/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.struts.lessconfig.plugin;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.container.hotdeploy.HotdeployBehavior;
import org.seasar.framework.container.impl.S2ContainerBehavior;
import org.seasar.framework.util.ClassUtil;
import org.seasar.struts.lessconfig.autoregister.StrutsConfigRegister;
import org.seasar.struts.lessconfig.config.AutoStrutsConfigRule;
import org.seasar.struts.lessconfig.util.ClassFinder;
import org.seasar.struts.lessconfig.util.ClassFinderImpl;

/**
 * 無設定Strutsを利用するために必要な{@link PlugIn}の実装クラスです。
 * <p>
 * 使用するには、struts-config.xmlに次のように設定します。
 * </p>
 * 
 * <pre>
 * &lt;plug-in className="org.seasar.struts.plugin.AutoStrutsConfigRegisterPlugIn"&gt;
 *     &lt;set-property property="enableJar" value="false"/&gt;
 *     &lt;set-property property="jarFilePattern" value="^My.*\.jar$"/&gt;
 *     &lt;set-property property="actionClassPattern" value="foo.bar.action.*Action"/&gt;
 *     &lt;set-property property="formClassPattern" value="foo.bar.form.*Form"/&gt;
 *     &lt;set-property property="docRoot" value="/WEB-INF/jsp"/&gt;
 *     &lt;set-property property="viewExtension" value="jsp,html,view"/&gt;
 * &lt;/plug-in&gt;
 * </pre>
 * 
 * @author Satoshi Kimura
 */
public class AutoStrutsConfigRegisterPlugIn implements PlugIn {

    private ClassFinder classFinder = new ClassFinderImpl();

    private boolean enableJar;

    private String jarFilePattern = "";

    private String referenceClassName = null;

    /**
     * インスタンスを構築します。
     */
    public AutoStrutsConfigRegisterPlugIn() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.PlugIn#destroy()
     */
    public void destroy() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet,
     *      org.apache.struts.config.ModuleConfig)
     */
    public void init(ActionServlet actionServlet, ModuleConfig config) throws ServletException {
        S2ContainerBehavior.Provider provider = S2ContainerBehavior.getProvider();
        if (provider instanceof HotdeployBehavior) {
            HotdeployBehavior ondemand = (HotdeployBehavior) provider;
            ondemand.start();
            try {
                register(actionServlet, config);
            } finally {
                ondemand.stop();
            }
        } else {
            register(actionServlet, config);
        }
    }

    /**
     * Strutsに必要な設定を自動登録します。
     * 
     * @param actionServlet
     * @param config
     */
    public void register(ActionServlet actionServlet, ModuleConfig config) {
        try {
            this.classFinder.find(isEnableJar(), getJarFilePattern());

            if (actionServlet != null) {
                this.classFinder.find(actionServlet, isEnableJar(), getJarFilePattern());
            }

            if (getReferenceClass() != null) {
                this.classFinder.find(ClassUtil.forName(getReferenceClass()));
            }

            getConfigRegister().register(config, this.classFinder.getClassCollection());
        } finally {
            this.classFinder.destroy();
        }
    }

    // -----------------------------------------------------------------------
    // set-property

    /**
     * 自動登録対象のクラスをクラスパスに含まれているjarファイル内からも検索する場合<code>true</code>を返します。
     * 
     * @return 自動登録対象のクラスをクラスパスに含まれているjarファイル内からも検索する場合<code>true</code>、そうでない場合<code>false</code>
     */
    public boolean isEnableJar() {
        return this.enableJar;
    }

    /**
     * 自動登録対象のクラスをクラスパスに含まれているjarファイル内からも検索する場合<code>true</code>を設定します。
     * 
     * @param enableJar
     *            自動登録対象のクラスをクラスパスに含まれているjarファイル内からも検索する場合<code>true</code>
     */
    public void setEnableJar(boolean enableJar) {
        this.enableJar = enableJar;
    }

    /**
     * {@link #setEnableJar(boolean)}にtrueを設定したときに検索するjarファイルのファイル名パターンを返します。
     * 
     * @return {@link #setEnableJar(boolean)}にtrueを設定したときに検索するjarファイルのファイル名パターン
     */
    public String getJarFilePattern() {
        return this.jarFilePattern;
    }

    /**
     * {@link #setEnableJar(boolean)}にtrueを設定したときに検索するjarファイルのファイル名パターンを指定します。
     * <p>
     * ファイル名パターンは正規表現で設定します。
     * </p>
     * 
     * @param jarFilePattern
     *            使用するJARファイルのパターン
     */
    public void setJarFilePattern(String jarFilePattern) {
        this.jarFilePattern = jarFilePattern;
    }

    /**
     * 自動登録対象のディレクトリまたはjarファイルを特定するための基点となるクラスを返します。
     * 
     * @return 自動登録対象のディレクトリまたはjarファイルを特定するための基点となるクラス
     */
    public String getReferenceClass() {
        return this.referenceClassName;
    }

    /**
     * 自動登録対象のディレクトリまたはjarファイルを特定するための基点となるクラスを設定します。
     * 
     * @param referenceClassName
     *            自動登録対象のディレクトリまたはjarファイルを特定するための基点となるクラス
     */
    public void setReferenceClass(String referenceClassName) {
        this.referenceClassName = referenceClassName;
    }

    /**
     * 無設定S2StrutsのActionを特定するためのクラス名パターンを設定します。
     * <p>
     * パターンは正規表現で設定します。
     * </p>
     * 
     * @param actionClassPattern
     *            Actionクラス名のパターン
     */
    public void setActionClassPattern(String actionClassPattern) {
        configRule().setActionClassPattern(actionClassPattern);
    }

    /**
     * 無設定S2StrutsのActionFormを特定するためのクラス名パターンを設定します。
     * <p>
     * パターンは正規表現で設定します。
     * </p>
     * 
     * @param formClassPattern
     *            ActionFormクラス名のパターン
     */
    public void setFormClassPattern(String formClassPattern) {
        configRule().setFormClassPattern(formClassPattern);
    }

    /**
     * Viewテンプレートとなるファイルの置き場所のトップディレクトリを設定します。
     * 
     * @param docRoot
     *            Viewテンプレートとなるファイルの置き場所のトップディレクトリ
     */
    public void setDocRoot(String docRoot) {
        configRule().setDocRoot(docRoot);
    }

    /**
     * Viewテンプレートとなるファイルの拡張子を指定します。
     * 
     * @param viewExtension
     *            Viewテンプレートとなるファイルの拡張子
     */
    public void setViewExtension(String viewExtension) {
        configRule().setViewExtension(viewExtension);
    }

    // -----------------------------------------------------------------------

    private static StrutsConfigRegister getConfigRegister() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (StrutsConfigRegister) container.getComponent(StrutsConfigRegister.class);
    }

    private static AutoStrutsConfigRule configRule() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (AutoStrutsConfigRule) container.getComponent(AutoStrutsConfigRule.class);
    }

}