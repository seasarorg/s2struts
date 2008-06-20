package org.seasar.struts.glue.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.seasar.struts.glue.NameExtracter;
import org.seasar.struts.glue.exception.TooManyMethodNameParameterException;

public class NameExtracterImpl implements NameExtracter {

    protected Pattern pattern;

    public NameExtracterImpl() {
        pattern = Pattern.compile("^(@[^@]+)$");
    }

    public String extractComponentName(String value) {
        return extract(value);
    }

    public String extractMethodName(String value) {
        return extract(value);
    }

    protected String extract(String value) {
        if (value == null) {
            return null;
        }
        Matcher m = pattern.matcher(value);
        if (m.find()) {
            return chop(m.group());
        }
        return null;
    }

    public String extractMethodName(HttpServletRequest request) {
        List<String> candidates = new ArrayList<String>();
        for (Enumeration<String> e = request.getParameterNames(); e
                .hasMoreElements();) {
            String name = e.nextElement();
            if (pattern.matcher(name).matches()) {
                candidates.add(name);
            }
        }
        if (candidates.isEmpty()) {
            return null;
        } else if (candidates.size() == 1) {
            return chop(candidates.get(0));
        }
        throw new TooManyMethodNameParameterException(candidates);
    }

    protected String chop(String s) {
        if (s == null) {
            return null;
        }
        return s.substring(1);
    }
}
