package org.seasar.struts.examples;

import javax.sql.DataSource;

import org.seasar.dao.AnnotationReaderFactory;
import org.seasar.dao.DaoMetaData;
import org.seasar.dao.impl.DaoMetaDataFactoryImpl;
import org.seasar.extension.jdbc.ResultSetFactory;
import org.seasar.extension.jdbc.StatementFactory;

public class MyDaoMetaDataFactoryImpl extends DaoMetaDataFactoryImpl {

    public MyDaoMetaDataFactoryImpl(DataSource arg0, StatementFactory arg1, ResultSetFactory arg2, AnnotationReaderFactory arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public synchronized DaoMetaData getDaoMetaData(Class daoClass) {
        DaoMetaData dmdi = createDaoMetaData(daoClass);
        return dmdi;
    }

}
