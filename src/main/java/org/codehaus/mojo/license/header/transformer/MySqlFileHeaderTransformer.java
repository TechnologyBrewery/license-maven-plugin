package org.codehaus.mojo.license.header.transformer;

/*
 * #%L
 * License Maven Plugin
 * %%
 * Copyright (C) 2012 Tony Chemit
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
 * Implementation of {@link FileHeaderTransformer} for mysql format.
 *
 * <strong>Note:</strong> Using mysql files, you can not just use the {@code sql} file header transformer
 * (see http://jira.codehaus.org/browse/MLICENSE-56) for more informations about the problem.
 *
 * @author tchemit dev@tchemit.fr
 * @since 1.4
 */
@Named("mysql")
@Singleton
public class MySqlFileHeaderTransformer extends AbstractFileHeaderTransformer {

    /**
     * Default constructor.
     */
    public MySqlFileHeaderTransformer() {
        super("mysql", "header transformer with mysql comment style", "-- -", "-- -", "-- ");
    }

    /**
     * {@inheritDoc}
     */
    public String[] getDefaultAcceptedExtensions() {
        return new String[] {"mysql"};
    }
}
