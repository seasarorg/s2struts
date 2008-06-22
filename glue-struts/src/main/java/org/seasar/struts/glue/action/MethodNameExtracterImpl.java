package org.seasar.struts.glue.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.glue.MethodNameExtracter;
import org.seasar.struts.glue.exception.TooManyMethodNameParameterException;

public class MethodNameExtracterImpl implements MethodNameExtracter {

    protected Pattern pattern;

    public MethodNameExtracterImpl() {
        pattern = Pattern.compile("^\\*[^\\*]+$");
    }

    public String extract(final HttpServletRequest request) {
        final List<String> candidates = new ArrayList<String>();
        for (final Enumeration<String> e = request.getParameterNames(); e
                .hasMoreElements();) {
            final String name = e.nextElement();
            if (pattern.matcher(name).matches()) {
                candidates.add(name);
            }
        }
        if (candidates.isEmpty()) {
            return null;
        } else if (candidates.size() == 1) {
            final String s = candidates.get(0);
            return s.substring(1);
        }
        throw new TooManyMethodNameParameterException(candidates);
    }
}
