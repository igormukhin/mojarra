/*
 * Copyright (c) 2017, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

/*
 * $Id: MockResultSetMetaData.java,v 1.1 2005/10/18 17:48:03 edburns Exp $
 */



package com.sun.faces.mock;


import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


/**
 * <p>Mock object that implements enough of
 * <code>java.sql.ResultSetMetaData</code>
 * to exercise the <code>ResultSetDataModel</code> functionality.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2005/10/18 17:48:03 $
 */

public class MockResultSetMetaData implements ResultSetMetaData {


    // ------------------------------------------------------------ Constructors


    /**
     * <p>Construct a new <code>ResultSetMetaData</code> object wrapping the
     * properties of the specified Java class.</p>
     *
     * @param clazz Class whose properties we treat like columns
     */
    public MockResultSetMetaData(Class clazz) throws SQLException {

        this.clazz = clazz;
        try {
            descriptors =
                Introspector.getBeanInfo(clazz).getPropertyDescriptors();
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }

    }
    

    // ------------------------------------------------------ Instance Variables


    // The Class whose properties we are treating as columns
    private Class clazz = null;


    // The PropertyDescriptors for the Class we are wrapping
    private PropertyDescriptor descriptors[] = null;


    // ---------------------------------------------------------- Public Methods


    public PropertyDescriptor getDescriptor(int columnIndex)
        throws SQLException {

        try {
            return (descriptors[columnIndex - 1]);
        } catch (IndexOutOfBoundsException e) {
            throw new SQLException("Invalid columnIndex " + columnIndex);
        }

    }


    // ----------------------------------------------------- Implemented Methods


    public String getColumnClassName(int columnIndex) throws SQLException {

        return (getDescriptor(columnIndex).getPropertyType().getName());

    }


    public int getColumnCount() throws SQLException {

        return (descriptors.length);

    }

    public String getColumnName(int columnIndex) throws SQLException {

        return (getDescriptor(columnIndex).getName());

    }


    public boolean isReadOnly(int columnIndex) throws SQLException {

        return (getDescriptor(columnIndex).getWriteMethod() == null);

    }



    // --------------------------------------------------- Unimplemented Methods


    public String getCatalogName(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public int getColumnDisplaySize(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public String getColumnLabel(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public int getColumnType(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public String getColumnTypeName(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public int getPrecision(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public int getScale(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public String getSchemaName(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public String getTableName(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public boolean isAutoIncrement(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public boolean isCaseSensitive(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public boolean isCurrency(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public boolean isDefinitelyWritable(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public int isNullable(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public boolean isSearchable(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public boolean isSigned(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public boolean isWritable(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}