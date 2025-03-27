package com.asac7_hackathon.hackathon.domain.posts.types;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Category {
  SPORTS, TECHNOLOGY, LIFESTYLE, ENTERTAINMENT;
  @JsonCreator
  public static Category fromString(String value) {
    try {
      return Category.valueOf(value.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid category: " + value);
    }
  }
  public static boolean isValidCategory(Category category) {
    if (category == null) {
      return false;
    }
    for (Category c : values()) {
      if (c == category) {
        return true;
      }
    }
    return false;
  }
}