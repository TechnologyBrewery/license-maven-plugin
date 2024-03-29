package org.codehaus.mojo.license.header.transformer;

/*
 * #%L
 * License Maven Plugin
 * %%
 * Copyright (C) 2008 - 2011 CodeLutin, Codehaus, Tony Chemit
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Implementation of {@link FileHeaderTransformer} for xml format.
 *
 * @author tchemit dev@tchemit.fr
 * @since 1.0
 */
@Named("xml")
@Singleton
public class XmlFileHeaderTransformer extends AbstractFileHeaderTransformer {

    /**
     * Default constructor.
     */
    public XmlFileHeaderTransformer() {
        super("xml", "header transformer with xml comment style", "<!--", "  -->", "  ");
    }

    /**
     * {@inheritDoc}
     */
    public String[] getDefaultAcceptedExtensions() {
        return new String[] {"pom", "xml", "mxlm", "dtd", "fml", "xsl", "jaxx", "kml", "gsp", "tml", "svg"};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addHeader(String header, String content) {

        String result;

        String prolog = null;
        int startProlog = content.indexOf("<?xml");
        if (startProlog > -1) {

            // prolog start was detected
            int endProlog = content.indexOf("?>", startProlog);

            if (endProlog > -1) {

                // prolog end was detected
                prolog = content.substring(0, endProlog + 2);
            }
        }

        if (prolog == null) {

            // no prolog detected
            result = super.addHeader(header, content);
        } else {

            // prolog detected
            content = content.substring(prolog.length());
            result = super.addHeader(prolog + LINE_SEPARATOR + header, content);
        }
        return result;
    }
}
