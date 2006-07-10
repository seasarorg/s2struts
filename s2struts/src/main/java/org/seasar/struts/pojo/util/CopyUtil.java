package org.seasar.struts.pojo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;

import org.seasar.framework.exception.ClassNotFoundRuntimeException;
import org.seasar.framework.exception.IORuntimeException;

/**
 * 
 * @author Katsuhiko Nagashima
 *
 */
public class CopyUtil {

    public static Object deepCopy(Serializable obj, ClassLoader loader) throws IORuntimeException,
            ClassNotFoundRuntimeException {
        if (obj == null) {
            return null;
        }
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(obj);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ClassLoaderSpecifiedObjectInputStream in = new ClassLoaderSpecifiedObjectInputStream(byteIn, loader);
            return in.readObject();
        } catch (IOException ex) {
            throw new IORuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundRuntimeException(ex);
        }
    }

    public static class ClassLoaderSpecifiedObjectInputStream extends ObjectInputStream {

        private final ClassLoader loader;

        public ClassLoaderSpecifiedObjectInputStream(InputStream in, ClassLoader loader) throws IOException {
            super(in);
            this.loader = loader;
        }

        public Class resolveClass(ObjectStreamClass v) throws IOException, ClassNotFoundException {
            if (this.loader == null) {
                return super.resolveClass(v);
            }
            return Class.forName(v.getName(), false, this.loader);
        }

    }

}