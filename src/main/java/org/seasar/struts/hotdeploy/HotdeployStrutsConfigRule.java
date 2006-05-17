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
package org.seasar.struts.hotdeploy;

/**
 * 
 * @author Katsuhiko Nagashima
 */
public class HotdeployStrutsConfigRule implements AutoStrutsConfigRegisterRule,
		AutoActionRule, AutoActionFormRule, AutoActionForwardRule {

	private String rootPackageName;
	
	public String getRootPackageName() {
		return this.rootPackageName;
	}

	public void setRootPackageName(String rootPackageName) {
		this.rootPackageName = rootPackageName;
	}
	
	private boolean enableJar;

	public boolean isEnableJar() {
		return this.enableJar;
	}

	public void setEnableJar(boolean enableJar) {
		this.enableJar = enableJar;
	}
    
    private String actionMiddlePackageName;

	public String getActionMiddlePackageName() {
		return this.actionMiddlePackageName;
	}

	public void setActionMiddlePackageName(String actionMiddlePackageName) {
        this.actionMiddlePackageName = actionMiddlePackageName;
	}
    
    private String actionSuffix;

	public String getActionSuffix() {
		return this.actionSuffix;
	}

	public void setActionSuffix(String actionSuffix) {
        this.actionSuffix = actionSuffix;
	}
    
    private String actionFormMiddlePackageName;

    public String getActionFormMiddlePackageName() {
        return actionFormMiddlePackageName;
    }

    public void setActionFormMiddlePackageName(String actionFormMiddlePackageName) {
        this.actionFormMiddlePackageName = actionFormMiddlePackageName;
    }

    private String actionFormSuffix;

    public String getActionFormSuffix() {
        return actionFormSuffix;
    }

    public void setActionFormSuffix(String actionFormSuffix) {
        this.actionFormSuffix = actionFormSuffix;
    }

    private String docRoot;

    public String getDocRoot() {
        return docRoot;
    }

    public void setDocRoot(String docRoot) {
        this.docRoot = docRoot;
    }

    private String[] viewExtension;

    public String[] getViewExtension() {
        return viewExtension;
    }

    public void setViewExtension(String[] viewExtension) {
        this.viewExtension = viewExtension;
    }

}
