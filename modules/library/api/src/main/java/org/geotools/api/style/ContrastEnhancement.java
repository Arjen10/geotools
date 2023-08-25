/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2011, Open Source Geospatial Foundation (OSGeo)
 *    (C) 2008, Open Geospatial Consortium Inc.
 *
 *    All Rights Reserved. http://www.opengis.org/legal/
 */
package org.geotools.api.style;

import org.geotools.api.filter.expression.Expression;

import java.util.Map;

/**
 * The ContrastEnhancement object defines contrast enhancement for a channel of a false-color image
 * or for a color image.
 *
 * <p>In the case of a color image, the relative grayscale brightness of a pixel color is used.
 * ?Normalize? means to stretch the contrast so that the dimmest color is stretched to black and the
 * brightest color is stretched to white, with all colors in between stretched out linearly.
 * ?Histogram? means to stretch the contrast based on a histogram of how many colors are at each
 * brightness level on input, with the goal of producing equal number of pixels in the image at each
 * brightness level on output. This has the effect of revealing many subtle ground features. A
 * ?GammaValue? tells how much to brighten (value greater than 1.0) or dim (value less than 1.0) an
 * image. The default GammaValue is 1.0 (no change). If none of Normalize, Histogram, or GammaValue
 * are selected in a ContrastEnhancement, then no enhancement is performed.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding
 *     Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Ian Turton, CCG
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface ContrastEnhancement {

    /** We use a codeList to enable more enchancement type possibilities. */
    public ContrastMethod getMethod();

    /**
     * How much to brighten (values greater than 1.0) or dim (values less than 1.0) an image. The
     * default GammaValue is 1.0 (no change).
     *
     * @return Expression, if <code>null</code> a value of 1.0 is assumed indicating no change
     */
    Expression getGammaValue();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);

    /**
     * check if vendor option key is available
     *
     * @param key - the name of the option to check
     * @return true if present
     */
    boolean hasOption(String key);

    /** Traversal of the style data structure. */
    void accept(StyleVisitor visitor);

    /** @return An expression for the matching VendorOption */
    Expression getOption(String string);

    /**
     * Return vendor options relating to the enhancement method
     *
     * @return a Map containing expressions with string keys.
     */
    Map<String, Expression> getOptions();
}
