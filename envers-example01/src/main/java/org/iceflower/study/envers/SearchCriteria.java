package org.iceflower.study.envers;

import lombok.Getter;

/**
 * 조회조건 명세서.
 */
public class SearchCriteria {

  @Getter
  private final String key;
  @Getter
  private final Object value;
  @Getter
  private final SearchOperation operation;

  private SearchCriteria(String key, Object value, SearchOperation operation) {
    this.key = key;
    this.value = value;
    this.operation = operation;
  }

  public static SearchCriteria of(String key, Object value, SearchOperation operation) {
    return new SearchCriteria(key, value, operation);
  }
}
