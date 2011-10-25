---
author: ianp
date: '2006-04-17 17:12:49'
layout: post
slug: dynamic-date-formatting
status: publish
title: Dynamic Date Formatting
wordpress_id: '60'
categories:
- Java
- RvSnoop
- Technology
- User Experience
---

I've noticed a neat feature in [Path
Finder](http://www.cocoatech.com/pf4/) where it changes the date format
used to display time stamps in the main table based on the width of the
column. In [RvSnoop](http://rvsnoop.org) I was allowing the user to set
a preferred format as a configuration option, but this seems much
better. It turns out that this is pretty easy to achieve in Java, just
use the following class:

~~~~ {lang="Java"}
public class DateCellRenderer extends DefaultTableCellRenderer {
    // Or load these from a user preference...
    private static final DateFormat[] dateFormats = {
    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"),
    new SimpleDateFormat("yy-MM-dd HH:mm:ss.SSS"),
    new SimpleDateFormat("MM/dd HH:mm:ss.SSS"),
    new SimpleDateFormat("HH:mm:ss.SSS"),
    new SimpleDateFormat("HH:mm:ss.SS"),
    new SimpleDateFormat("HH:mm:ss.S"),
    new SimpleDateFormat("HH:mm:ss"),
    new SimpleDateFormat("HH:mm") };
    private int currentWidth;
    private Font currentFont;
    private DateFormat currentFormat;
    private final Date date = new Date();
    public Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int col) {
        DateFormat format = getFormat(table.getColumnModel().getColumn(col).getWidth(), table);
        String displayed = value != null ? format.format((Date) value) : "";
        return super.getTableCellRendererComponent(table, displayed, isSelected, hasFocus, row, col);
    }
    private DateFormat getFormat(int width, JTable table) {
        Font font = table.getFont();
        if (currentWidth == width && currentFormat != null && font.equals(currentFont)) {
            return currentFormat;
        }
        currentWidth = width;
        currentFont = font;
        FontMetrics metrics = table.getFontMetrics(font);
        date.setTime(System.currentTimeMillis())
        for (DateFormat df : dateFormats) {
            if (metrics.stringWidth(df.format(date)) < width) {
                currentFormat = df;
                return df;
                }
            }
        }
        currentFormat = dateFormats[dateFormats.length - 1];
        return currentFormat;
    }
}
~~~~

You will need to register it with your \`JTable\` via
\`myTable.getColumnModel().getColumn(0).setCellRenderer(myRenderer);\`
and away you go. You can have more or less format options by altering
the static array in the class.
