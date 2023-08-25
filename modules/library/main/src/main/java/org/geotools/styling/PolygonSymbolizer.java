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

import javax.measure.Unit;
import javax.measure.quantity.Length;
import org.geotools.api.filter.expression.Expression;
import org.geotools.api.style.StyleVisitor;
import org.geotools.api.util.Cloneable;

/**
 * Provides a representation of a PolygonSymbolizer in an SLD Document. A PolygonSymbolizer defines
 * how a polygon geometry should be rendered.
 *
 * @author James Macgill, CCG
 * @author Johann Sorel (Geomatys)
 * @version $Id$
 */
public class PolygonSymbolizer extends AbstractSymbolizer
        implements  Cloneable, org.geotools.api.style.PolygonSymbolizer, Symbolizer {

    /** Pixels between each graphic of a polygon fill */
    public static final String GRAPHIC_MARGIN_KEY = "graphic-margin";
    private Expression offset;
    private Displacement disp;

    private Fill fill = new Fill();
    private Stroke stroke = new Stroke();

    /** Creates a new instance of DefaultPolygonStyler */
    protected PolygonSymbolizer() {
        this(null, null, null, null, null, null, null, null);
    }

    protected PolygonSymbolizer(
            Stroke stroke,
            Fill fill,
            Displacement disp,
            Expression offset,
            Unit<Length> uom,
            String geom,
            String name,
            Description desc) {
        super(name, desc, geom, uom);
        this.stroke = Stroke.cast(stroke);
        this.fill = fill;
        this.disp = Displacement.cast(disp);
        this.offset = offset;
    }

    @Override
    public Expression getPerpendicularOffset() {
        return offset;
    }

    public void setPerpendicularOffset(Expression offset) {
        this.offset = offset;
    }

    @Override
    public Displacement getDisplacement() {
        return disp;
    }

    public void setDisplacement(org.geotools.api.style.Displacement displacement) {
        this.disp = Displacement.cast(displacement);
    }
    /**
     * Provides the graphical-symbolization parameter to use to fill the area of the geometry.
     *
     * @return The Fill style to use when rendering the area.
     */
    @Override
    public Fill getFill() {
        return fill;
    }

    /**
     * Sets the graphical-symbolization parameter to use to fill the area of the geometry.
     *
     * @param fill The Fill style to use when rendering the area.
     */
    public void setFill(org.geotools.api.style.Fill fill) {
        if (this.fill == fill) {
            return;
        }
        this.fill = Fill.cast(fill);
    }

    /**
     * Provides the graphical-symbolization parameter to use for the outline of the Polygon.
     *
     * @return The Stroke style to use when rendering lines.
     */
    @Override
    public Stroke getStroke() {
        return stroke;
    }

    /**
     * Sets the graphical-symbolization parameter to use for the outline of the Polygon.
     *
     * @param stroke The Stroke style to use when rendering lines.
     */
    public void setStroke(org.geotools.api.style.Stroke stroke) {
        if (this.stroke == stroke) {
            return;
        }
        this.stroke = Stroke.cast(stroke);
    }

    /**
     * Accepts a StyleVisitor to perform some operation on this LineSymbolizer.
     *
     * @param visitor The visitor to accept.
     */
    @Override
    public Object accept(StyleVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    @Override
    public void accept(StyleVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Creates a deep copy clone. TODO: Need to complete the deep copy, currently only shallow copy.
     *
     * @return The deep copy clone.
     */
    @Override
    public Object clone() {
        PolygonSymbolizer clone;

        try {
            clone = (PolygonSymbolizer) super.clone();

            if (fill != null) {
                clone.fill = (Fill) ((Cloneable) fill).clone();
            }

            if (stroke != null) {
                clone.stroke = (Stroke) ((Cloneable) stroke).clone();
            }
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e); // this should never happen.
        }

        return clone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((disp == null) ? 0 : disp.hashCode());
        result = prime * result + ((fill == null) ? 0 : fill.hashCode());
        result = prime * result + ((offset == null) ? 0 : offset.hashCode());
        result = prime * result + ((stroke == null) ? 0 : stroke.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        PolygonSymbolizer other = (PolygonSymbolizer) obj;
        if (disp == null) {
            if (other.disp != null) return false;
        } else if (!disp.equals(other.disp)) return false;
        if (fill == null) {
            if (other.fill != null) return false;
        } else if (!fill.equals(other.fill)) return false;
        if (offset == null) {
            if (other.offset != null) return false;
        } else if (!offset.equals(other.offset)) return false;
        if (stroke == null) {
            if (other.stroke != null) return false;
        } else if (!stroke.equals(other.stroke)) return false;
        return true;
    }

    static PolygonSymbolizer cast(org.geotools.api.style.Symbolizer symbolizer) {
        if (symbolizer == null) {
            return null;
        } else if (symbolizer instanceof PolygonSymbolizer) {
            return (PolygonSymbolizer) symbolizer;
        } else if (symbolizer instanceof org.geotools.api.style.PolygonSymbolizer) {
            org.geotools.api.style.PolygonSymbolizer polygonSymbolizer =
                    (org.geotools.api.style.PolygonSymbolizer) symbolizer;
            PolygonSymbolizer copy = new PolygonSymbolizer();
            copy.setStroke(Stroke.cast(polygonSymbolizer.getStroke()));
            copy.setDescription(polygonSymbolizer.getDescription());
            copy.setDisplacement(polygonSymbolizer.getDisplacement());
            copy.setFill(polygonSymbolizer.getFill());
            copy.setGeometryPropertyName(polygonSymbolizer.getGeometryPropertyName());
            copy.setName(polygonSymbolizer.getName());
            copy.setPerpendicularOffset(polygonSymbolizer.getPerpendicularOffset());
            copy.setUnitOfMeasure(polygonSymbolizer.getUnitOfMeasure());
            return copy;
        } else {
            return null; // not possible
        }
    }
}
