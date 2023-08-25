/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2008, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.styling;

import org.geotools.api.util.Cloneable;
import org.geotools.util.Utilities;

public class Extent implements org.geotools.api.style.Extent, Cloneable {
    private String name;
    private String value;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Extent) {
            Extent other = (Extent) obj;

            return Utilities.equals(this.name, other.name)
                    && Utilities.equals(this.value, other.value);
        }

        return false;
    }

    @Override
    public int hashCode() {
        final int PRIME = 1000003;
        int result = 0;

        if (name != null) {
            result = (PRIME * result) + name.hashCode();
        }

        if (value != null) {
            result = (PRIME * result) + value.hashCode();
        }

        return result;
    }

    @Override
    public Object clone() {
        try {
            Extent clone = (Extent) super.clone();
            clone.setName(name);
            clone.setValue(value);

            return clone;
        } catch (CloneNotSupportedException e) {
            // This will never happen
            throw new RuntimeException("Failed to clone ExtentImpl");
        }
    }
}
