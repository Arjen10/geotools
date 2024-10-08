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
package org.geotools.sld.bindings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;
import org.geotools.api.style.RasterSymbolizer;
import org.junit.Test;

public class SLDRasterSymbolizerBindingTest extends SLDTestSupport {
    @Test
    public void testType() throws Exception {
        assertEquals(RasterSymbolizer.class, new SLDRasterSymbolizerBinding(null).getType());
    }

    @Test
    public void test() throws Exception {
        SLDMockData.rasterSymbolizer(document, document);

        RasterSymbolizer rs = (RasterSymbolizer) parse();
        assertNotNull(rs);
        assertNotNull(rs.getChannelSelection());
        assertNotNull(rs.getColorMap());
        assertNotNull(rs.getContrastEnhancement());
        assertNotNull(rs.getImageOutline());
        assertNotNull(rs.getOpacity());
        assertNotNull(rs.getOverlap());
        assertNotNull(rs.getGeometryPropertyName());
        assertNotNull(rs.getShadedRelief());
    }

    @Test
    public void testWithVendorOptions() throws Exception {
        SLDMockData.rasterSymbolizerWithVendorOptions(document, document);

        RasterSymbolizer rs = (RasterSymbolizer) parse();
        assertNotNull(rs);
        assertNotNull(rs.getChannelSelection());
        assertNotNull(rs.getColorMap());
        assertNotNull(rs.getContrastEnhancement());
        assertNotNull(rs.getImageOutline());
        assertNotNull(rs.getOpacity());
        assertNotNull(rs.getOverlap());
        assertNotNull(rs.getGeometryPropertyName());
        assertNotNull(rs.getShadedRelief());
        Map<String, String> options = rs.getOptions();
        assertEquals(2, options.size());
        assertEquals("value", options.get("name"));
        assertEquals("value2", options.get("name2"));
    }
}
