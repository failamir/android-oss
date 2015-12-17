package com.kickstarter.libs.utils;

import com.kickstarter.libs.NumberOptions;

import junit.framework.TestCase;

import java.util.Locale;

public class NumberUtilsTest extends TestCase {
  public void testFormatNumber() {
    assertEquals("100", NumberUtils.format(100.0f));
    assertEquals("1,000", NumberUtils.format(1000.0f));
    assertEquals("1,001", NumberUtils.format(1000.6f));
  }

  public void testFormatNumber_withPrecision() {
    assertEquals("100.12", NumberUtils.format(100.12f, NumberOptions.builder().precision(2).build()));
    assertEquals("100.2", NumberUtils.format(100.16f, NumberOptions.builder().precision(1).build()));
    assertEquals("100.00", NumberUtils.format(100.0f, NumberOptions.builder().precision(2).build()));
  }

  public void testFormatNumber_withBucket() {
    assertEquals("100", NumberUtils.format(100.0f, NumberOptions.builder().bucketAbove(100.0f).build()));

    assertEquals("100", NumberUtils.format(100.0f, NumberOptions.builder().bucketAbove(1000.0f).build()));
    assertEquals("1K", NumberUtils.format(1000.0f, NumberOptions.builder().bucketAbove(1000.0f).build()));
    assertEquals("10K", NumberUtils.format(10000.0f, NumberOptions.builder().bucketAbove(1000.0f).build()));
    assertEquals("100K", NumberUtils.format(100_000.0f, NumberOptions.builder().bucketAbove(1000.0f).build()));
    assertEquals("1,000K", NumberUtils.format(1_000_000.0f, NumberOptions.builder().bucketAbove(1000.0f).build()));

    assertEquals("100", NumberUtils.format(100.0f, NumberOptions.builder().bucketAbove(10000.0f).build()));
    assertEquals("1,000", NumberUtils.format(1000.0f, NumberOptions.builder().bucketAbove(10000.0f).build()));
    assertEquals("10K", NumberUtils.format(10000.0f, NumberOptions.builder().bucketAbove(10000.0f).build()));
    assertEquals("100K", NumberUtils.format(100_000.0f, NumberOptions.builder().bucketAbove(10000.0f).build()));
    assertEquals("1,000K", NumberUtils.format(1_000_000.0f, NumberOptions.builder().bucketAbove(10000.0f).build()));

    assertEquals("100", NumberUtils.format(100.0f, NumberOptions.builder().bucketAbove(100_000.0f).build()));
    assertEquals("1,000", NumberUtils.format(1000.0f, NumberOptions.builder().bucketAbove(100_000.0f).build()));
    assertEquals("10,000", NumberUtils.format(10000.0f, NumberOptions.builder().bucketAbove(100_000.0f).build()));
    assertEquals("100K", NumberUtils.format(100_000.0f, NumberOptions.builder().bucketAbove(100_000.0f).build()));
    assertEquals("1,000K", NumberUtils.format(1_000_000.0f, NumberOptions.builder().bucketAbove(100_000.0f).build()));

    assertEquals("100", NumberUtils.format(100.0f, NumberOptions.builder().bucketAbove(1_000_000.0f).build()));
    assertEquals("1,000", NumberUtils.format(1000.0f, NumberOptions.builder().bucketAbove(1_000_000.0f).build()));
    assertEquals("10,000", NumberUtils.format(10000.0f, NumberOptions.builder().bucketAbove(1_000_000.0f).build()));
    assertEquals("100,000", NumberUtils.format(100_000.0f, NumberOptions.builder().bucketAbove(1_000_000.0f).build()));
    assertEquals("1M", NumberUtils.format(1_000_000.0f, NumberOptions.builder().bucketAbove(1_000_000.0f).build()));

    assertEquals("111", NumberUtils.format(111.0f, NumberOptions.builder().bucketAbove(1000.0f).bucketPrecision(1).build()));
    assertEquals("111.00", NumberUtils.format(111.0f, NumberOptions.builder().bucketAbove(111.0f).bucketPrecision(1).precision(2).build()));
    assertEquals("1.1K", NumberUtils.format(1111.0f, NumberOptions.builder().bucketAbove(1000.0f).bucketPrecision(1).build()));
    assertEquals("1.1K", NumberUtils.format(1111.0f, NumberOptions.builder().bucketAbove(1000.0f).bucketPrecision(1).precision(2).build()));
    assertEquals("11.1K", NumberUtils.format(11111.0f, NumberOptions.builder().bucketAbove(1000.0f).bucketPrecision(1).build()));
    assertEquals("111.1K", NumberUtils.format(111_111.0f, NumberOptions.builder().bucketAbove(1000.0f).bucketPrecision(1).build()));
    assertEquals("1,111.1K", NumberUtils.format(1_111_111.0f, NumberOptions.builder().bucketAbove(1000.0f).bucketPrecision(1).build()));
  }

  public void testFormatNumber_withCurrency() {
    assertEquals("$100", NumberUtils.format(100.0f, NumberOptions.builder().currencySymbol("$").build()));
    assertEquals("€100", NumberUtils.format(100.0f, NumberOptions.builder().currencySymbol("€").build()));
    assertEquals("$100 CAD", NumberUtils.format(100.0f, NumberOptions.builder().currencySymbol("$").currencyCode("CAD").build()));
  }

  public void testFormatNumber_withGermanLocale() {
    assertEquals("100 $", NumberUtils.format(100.0f, NumberOptions.builder().currencySymbol("$").build(), Locale.GERMANY));
    assertEquals("1.000", NumberUtils.format(1000.0f, NumberOptions.builder().build(), Locale.GERMANY));
    assertEquals("100,12", NumberUtils.format(100.12f, NumberOptions.builder().precision(2).build(), Locale.GERMANY));
  }
}

