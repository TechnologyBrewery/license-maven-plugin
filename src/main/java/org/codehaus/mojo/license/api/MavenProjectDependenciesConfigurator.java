package org.codehaus.mojo.license.api;

/*
 * #%L
 * License Maven Plugin
 * %%
 * Copyright (C) 2011 CodeLutin, Codehaus, Tony Chemit
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

/**
 * Contract to configure which dependencies will be loaded by the dependency tool via the method
 * {@link DependenciesTool#loadProjectDependencies(org.apache.maven.project.MavenProject,
 * MavenProjectDependenciesConfigurator, org.apache.maven.artifact.repository.ArtifactRepository,
 * List, java.util.SortedMap)}
 *
 * @author tchemit dev@tchemit.fr
 * @see DependenciesTool
 * @since 1.0
 */
public interface MavenProjectDependenciesConfigurator {

    /**
     * @return {@code true} if should include transitive dependencies, {@code false} to include only direct
     *         dependencies.
     */
    boolean isIncludeTransitiveDependencies();

    /**
     * @return {@code true} if should exclude transitive dependencies from excluded artifacts, else {@code false}.
     */
    boolean isExcludeTransitiveDependencies();

    /**
     * @return {@link ArtifactFilters} to apply when processing dependencies
     */
    ArtifactFilters getArtifactFilters();

    /**
     * @return {@code true} if verbose mode is on, {@code false} otherwise.
     */
    boolean isVerbose();
}
