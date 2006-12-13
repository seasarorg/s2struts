package org.seasar.struts.examples.extension;

import org.seasar.extension.dxo.converter.ConversionContext;
import org.seasar.extension.dxo.converter.impl.StringConverter;

public class LengthZeroToNullStringConverter extends StringConverter {

    public Object convert(final Object source, final Class destClass,
            final ConversionContext context) {

        Object result = super.convert(source, destClass, context);
        if (result == null) {
            return null;
        }

        if (result.toString().length() == 0) {
            return null;
        }
        return result;
    }

}
