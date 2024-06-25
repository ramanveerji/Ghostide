/*
 *    sora-editor - the awesome code editor for Android
 *    https://github.com/Rosemoe/sora-editor
 *    Copyright (C) 2020-2022  Rosemoe
 *
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License, or (at your option) any later version.
 *
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 *     USA
 *
 *     Please contact Rosemoe by email 2073412493@qq.com if you need
 *     additional information or have any questions
 */
package io.github.rosemoe.sora.text;

/**
 * Utility class for text style related operations
 *
 * @author Rosemoe
 */
public class TextStyle {

  public static final int COLOR_ID_BIT_COUNT = 19;
  public static final long FOREGROUND_BITS = ((1 << (COLOR_ID_BIT_COUNT)) - 1);
  public static final long BACKGROUND_BITS = FOREGROUND_BITS << COLOR_ID_BIT_COUNT;
  public static final long BOLD_BIT = 1L << (COLOR_ID_BIT_COUNT * 2);
  public static final long ITALICS_BIT = BOLD_BIT << 1;
  public static final long STRIKETHROUGH_BIT = ITALICS_BIT << 1;
  public static final long BELOWLINE = STRIKETHROUGH_BIT << 1;
  public static final long ShadowColor = BELOWLINE << 1;
  public static final String hexColor = "#3FFD091D";

  /** Convenient method */
  public static long makeStyle(int foregroundColorId) {
    checkColorId(foregroundColorId);
    return foregroundColorId;
  }

  /**
   * Make a TextStyle with the given style arguments
   *
   * <p>Note: colorId must be less than 20 bits
   */
  public static long makeStyle(
      int foregroundColorId,
      int backgroundColorId,
      boolean bold,
      boolean italic,
      boolean strikeThrough) {
    checkColorId(foregroundColorId);
    checkColorId(backgroundColorId);
    return ((long) foregroundColorId)
        + (((long) backgroundColorId) << COLOR_ID_BIT_COUNT)
        + (bold ? BOLD_BIT : 0)
        + (italic ? ITALICS_BIT : 0)
        + (strikeThrough ? STRIKETHROUGH_BIT : 0);
  }

  public static long makeStyle(
      int foregroundColorId,
      int backgroundColorId,
      boolean bold,
      boolean italic,
      boolean strikeThrough,
      boolean blow) {
    checkColorId(foregroundColorId);
    checkColorId(backgroundColorId);
    return ((long) foregroundColorId)
        + (((long) backgroundColorId) << COLOR_ID_BIT_COUNT)
        + (bold ? BOLD_BIT : 0)
        + (italic ? ITALICS_BIT : 0)
        + (strikeThrough ? STRIKETHROUGH_BIT : 0)
        + (blow ? BELOWLINE : 0);
  }

  public static long makeStyle(
      int foregroundColorId,
      int backgroundColorId,
      boolean bold,
      boolean italic,
      boolean strikeThrough,
      boolean blow,
      boolean sh) {
    checkColorId(foregroundColorId);
    checkColorId(backgroundColorId);
    return ((long) foregroundColorId)
        + (((long) backgroundColorId) << COLOR_ID_BIT_COUNT)
        + (bold ? BOLD_BIT : 0)
        + (italic ? ITALICS_BIT : 0)
        + (strikeThrough ? STRIKETHROUGH_BIT : 0)
        + (blow ? BELOWLINE : 0)
        + (sh ? ShadowColor : 0);
  }

  public static int getForegroundColorId(long style) {
    return (int) (style & FOREGROUND_BITS);
  }

  public static int getBackgroundColorId(long style) {
    return (int) ((style & BACKGROUND_BITS) >> COLOR_ID_BIT_COUNT);
  }

  public static boolean isBold(long style) {
    return (style & BOLD_BIT) != 0;
  }

  public static boolean isItalics(long style) {
    return (style & ITALICS_BIT) != 0;
  }

  public static boolean isStrikeThrough(long style) {
    return (style & STRIKETHROUGH_BIT) != 0;
  }

  public static boolean isBELOWLINE(long style) {
    return (style & BELOWLINE) != 0;
  }

  public static boolean isShadowLine(long style) {
    return (style & ShadowColor) != 0;
  }

  public static long getStyleBits(long style) {
    return style & (~(BACKGROUND_BITS + FOREGROUND_BITS));
  }

  public static void checkColorId(int colorId) {
    if (colorId > (1 << COLOR_ID_BIT_COUNT) - 1 || colorId < 0) {
      throw new IllegalArgumentException(
          "color id must be positive and bit count is less than " + COLOR_ID_BIT_COUNT);
    }
  }
  public static String getHexColor(){
    return hexColor;
  }
}
