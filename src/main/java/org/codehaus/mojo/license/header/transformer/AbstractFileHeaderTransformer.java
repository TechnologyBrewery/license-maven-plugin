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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.mojo.license.header.FileHeader;
import org.codehaus.mojo.license.model.Copyright;

/**
 * Abstract implementation of {@link FileHeaderTransformer}.
 *
 * Concrete implementation should only have to give comment configuration.
 *
 * @author tchemit dev@tchemit.fr
 * @since 1.0
 */
public abstract class AbstractFileHeaderTransformer implements FileHeaderTransformer {

    /**
     * pattern of the copyright string representation :
     * <ul>
     * <li>group(1) is Copyright prefix</li>
     * <li>group(2) is Copyright first year</li>
     * <li>group(3) is Copyright last year with prefix (can be null)</li>
     * <li>group(4) is Copyright last year (can be null)</li>
     * <li>group(5) is Copyright holder</li>
     * </ul>
     */
    static final Pattern COPYRIGHT_PATTERN =
            Pattern.compile("(.[^\\d]+)?\\s(\\d{4})?(\\s+-\\s+(\\d{4})?){0,1}\\s+(.+)?", Pattern.DOTALL);

    /**
     * name of transformer.
     */
    private String name;

    /**
     * description of transfomer.
     */
    private String description;

    /**
     * section delimiter.
     */
    private String sectionDelimiter = DEFAULT_SECTION_DELIMITER;

    /**
     * start process tag.
     */
    private String processStartTag = DEFAULT_PROCESS_START_TAG;

    /**
     * end process tag.
     */
    private String processEndTag = DEFAULT_PROCESS_END_TAG;

    /**
     * comment start tag.
     */
    private String commentStartTag;

    /**
     * comment end tag.
     */
    private String commentEndTag;

    /**
     * comment line prefix (to add for header content).
     */
    private String commentLinePrefix;

    /**
     * Flag if there should be an empty line after the header.
     */
    private boolean emptyLineAfterHeader;

    /**
     * Flag if line should be trimmed when boxed
     */
    private boolean trimHeaderLine;

    /**
     * Line separator.
     */
    private String lineSeparator;

    protected AbstractFileHeaderTransformer(
            String name, String description, String commentStartTag, String commentEndTag, String commentLinePrefix) {
        this.name = name;
        this.description = description;

        // checks comment start tag is different from comment prefix
        if (commentStartTag.equals(commentLinePrefix)) {
            throw new IllegalStateException(
                    "commentStartTag can not be equals to commentPrefixLine, " + "but was [" + commentStartTag + "]");
        }

        // checks comment end tag is different from comment prefix
        if (commentEndTag.equals(commentLinePrefix)) {
            throw new IllegalStateException(
                    "commentEndTag can not be equals to commentPrefixLine, " + "but was [" + commentEndTag + "]");
        }

        this.commentStartTag = commentStartTag;
        this.commentEndTag = commentEndTag;
        this.commentLinePrefix = commentLinePrefix;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    public String getSectionDelimiter() {
        return sectionDelimiter;
    }

    /**
     * {@inheritDoc}
     */
    public void setSectionDelimiter(String sectionDelimiter) {
        this.sectionDelimiter = sectionDelimiter;
    }

    /**
     * {@inheritDoc}
     */
    public String getProcessStartTag() {
        return processStartTag;
    }

    /**
     * {@inheritDoc}
     */
    public void setProcessStartTag(String processStartTag) {
        this.processStartTag = processStartTag;
    }

    /**
     * {@inheritDoc}
     */
    public String getProcessEndTag() {
        return processEndTag;
    }

    /**
     * {@inheritDoc}
     */
    public void setProcessEndTag(String processEndTag) {
        this.processEndTag = processEndTag;
    }

    /**
     * {@inheritDoc}
     */
    public String getCommentStartTag() {
        return commentStartTag;
    }

    /**
     * {@inheritDoc}
     */
    public void setCommentStartTag(String commentStartTag) {
        this.commentStartTag = commentStartTag;
    }

    /**
     * {@inheritDoc}
     */
    public String getCommentEndTag() {
        return commentEndTag;
    }

    /**
     * {@inheritDoc}
     */
    public void setCommentEndTag(String commentEndTag) {
        this.commentEndTag = commentEndTag;
    }

    /**
     * {@inheritDoc}
     */
    public String getCommentLinePrefix() {
        return commentLinePrefix;
    }
    /**
     * {@inheritDoc}
     */
    public String getLineSeparator() {
        if (lineSeparator == null) {
            lineSeparator = LINE_SEPARATOR;
            return lineSeparator;
        } else {
            return lineSeparator;
        }
    }
    /**
     * {@inheritDoc}
     */
    public void setLineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
    }

    /**
     * {@inheritDoc}
     */
    public String addHeader(String header, String content) {
        if (isEmptyLineAfterHeader()) {
            String[] contentSplit = content.split("\\r?\\n", 2);
            if (contentSplit.length > 0) {
                final String line = contentSplit[0].trim();
                if (line.length() > 0) {
                    return header + getLineSeparator() + content;
                }
            }
        }
        return header + content;
    }

    /**
     * {@inheritDoc}
     */
    public void setCommentLinePrefix(String commentLinePrefix) {
        this.commentLinePrefix = commentLinePrefix;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEmptyLineAfterHeader() {
        return emptyLineAfterHeader;
    }

    /**
     * {@inheritDoc}
     */
    public void setEmptyLineAfterHeader(boolean emptyLine) {
        this.emptyLineAfterHeader = emptyLine;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isTrimHeaderLine() {
        return trimHeaderLine;
    }

    /**
     * {@inheritDoc}
     */
    public void setTrimHeaderLine(boolean trimLine) {
        this.trimHeaderLine = trimLine;
    }

    /**
     * {@inheritDoc}
     */
    public FileHeader toFileHeader(String header) {
        FileHeader model = new FileHeader();

        String[] sections = header.split(getSectionDelimiter());
        if (sections.length != 3) {
            throw new IllegalStateException("could not find 3 sections in\n" + header);
        }

        // first section is the description
        String description = sections[0].trim();
        model.setDescription(description);

        // second section is the copyright
        String copyrightModel = sections[1].trim();
        Matcher matcher = COPYRIGHT_PATTERN.matcher(copyrightModel);
        if (!matcher.matches()) {
            throw new IllegalStateException("copyright [" + copyrightModel + "] is not valid");
        }
        String firstYear = matcher.group(2);
        String lastYear = matcher.group(4);
        String holder = matcher.group(5);
        Copyright copyright1 = Copyright.newCopyright(
                Integer.valueOf(firstYear), lastYear == null ? null : Integer.valueOf(lastYear.trim()), holder.trim());
        model.setCopyright(copyright1);

        // third section is the license
        String license = sections[2].trim();
        model.setLicense(license);
        return model;
    }

    /**
     * {@inheritDoc}
     */
    public String toString(FileHeader model) {
        if (model == null) {
            throw new NullPointerException("model can not be null!");
        }
        StringBuilder buffer = new StringBuilder();

        String sectionDelimiter = getLineSeparator() + getSectionDelimiter() + getLineSeparator();

        // add description section
        buffer.append(model.getDescription().trim());
        buffer.append(sectionDelimiter);

        // add copyright section
        buffer.append(model.getCopyright().getText().trim());
        buffer.append(sectionDelimiter);

        // add license section
        buffer.append(model.getLicense().trim()).append(getLineSeparator());
        return buffer.toString();
    }

    /**
     * {@inheritDoc}
     */
    public String toHeaderContent(FileHeader model) {

        String result;

        // model to text
        result = toString(model);

        // box with process tag
        result = boxProcessTag(result);

        // box header with comment prefix
        result = boxComment(result, false);

        // remove all before process start tag
        // remove all after process end tag
        // this is a requirement for processor to respect involution.
        int index = result.indexOf(getProcessStartTag());
        int lastIndex =
                result.lastIndexOf(getProcessEndTag()) + getProcessEndTag().length();

        result = result.substring(index, lastIndex);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public String boxComment(String header, boolean withTags) {
        StringBuilder buffer = new StringBuilder();
        if (withTags) {
            buffer.append(getCommentStartTag()).append(getLineSeparator());
        }
        for (String line : header.split("\\r?\\n")) {
            if (isTrimHeaderLine()) {
                StringBuilder lineBuffer = new StringBuilder();
                lineBuffer.append(getCommentLinePrefix());
                lineBuffer.append(line);
                buffer.append(StringUtils.stripEnd(lineBuffer.toString(), null));
            } else {
                buffer.append(getCommentLinePrefix());
                buffer.append(line);
            }
            buffer.append(getLineSeparator());
        }
        if (withTags) {
            buffer.append(getCommentEndTag()).append(getLineSeparator());
        }
        return buffer.toString();
    }

    /**
     * {@inheritDoc}
     */
    public String unboxComent(String header) {
        StringBuilder buffer = new StringBuilder();
        int prefixLength = getCommentLinePrefix().length();
        for (String line : header.split(getLineSeparator() + "")) {
            if (StringUtils.isEmpty(line) || line.contains(getCommentStartTag()) || line.contains(getCommentEndTag())) {

                // not be unboxed, but just skipped
                continue;
            }
            int index = line.indexOf(getCommentLinePrefix());
            if (index > -1) {

                // remove comment prefix
                line = line.substring(index + prefixLength);
            } else {
                String s = getCommentLinePrefix().trim();
                if (line.startsWith(s)) {
                    line = line.substring(s.length());
                } else {
                    line = "";
                }
            }
            buffer.append(line).append(getLineSeparator());
        }
        return buffer.toString();
    }

    /**
     * {@inheritDoc}
     */
    public String boxProcessTag(String header) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(getProcessStartTag()).append(getLineSeparator());
        buffer.append(header.trim()).append(getLineSeparator());
        buffer.append(getProcessEndTag()).append(getLineSeparator());
        return buffer.toString();
    }

    /**
     * {@inheritDoc}
     */
    public String unboxProcessTag(String boxedHeader) {
        StringBuilder buffer = new StringBuilder();
        for (String line : boxedHeader.split(getLineSeparator() + "")) {
            if (StringUtils.isEmpty(line) || line.contains(getProcessStartTag()) || line.contains(getProcessEndTag())) {
                // not be unboxed, but just skipped
                continue;
            }
            buffer.append(line).append(getLineSeparator());
        }
        return buffer.toString();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isDescriptionEquals(FileHeader header1, FileHeader header2) {
        return header1.getDescription().equals(header2.getDescription());
    }

    /**
     * {@inheritDoc}
     */
    public boolean isCopyrightEquals(FileHeader header1, FileHeader header2) {
        return header1.getCopyright().equals(header2.getCopyright());
    }

    /**
     * {@inheritDoc}
     */
    public boolean isLicenseEquals(FileHeader header1, FileHeader header2) {
        String license1 = removeSpaces(header1.getLicense());
        String license2 = removeSpaces(header2.getLicense());
        return license1.equals(license2);
    }

    private static final Pattern REMOVE_SPACE_PATTERN = Pattern.compile("(\\s+)");

    private String removeSpaces(String str) {
        Matcher matcher = REMOVE_SPACE_PATTERN.matcher(str);
        String result;
        if (matcher.find()) {
            result = matcher.replaceAll("");
        } else {
            result = str;
        }
        return result;
    }
}
