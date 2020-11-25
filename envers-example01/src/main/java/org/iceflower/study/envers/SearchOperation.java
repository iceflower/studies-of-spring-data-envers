package org.iceflower.study.envers;

/**
 * 조회조건 키워드.
 */
public enum SearchOperation {
  /** 초과. **/
  GREATER_THAN,
  /** 미만. **/
  LESS_THAN,
  /** 이상 **/
  GREATER_THAN_EQUAL,
  /** 이하. **/
  LESS_THAN_EQUAL,
  /** 같지 않음. **/
  NOT_EQUAL,
  /** 같음. **/
  EQUAL,
  /** 조건 값이 비교대상에 포함되는지 여부 (like %키워드%). **/
  MATCH,
  /** 조건값과 시작 부분 일치 여부 (like 키워드%). **/
  MATCH_START,
  /** 조건값과 종료 부분 일치 여부 (like %키워드) **/
  MATCH_END,
  /** 여러 조건 값 중 하나와 값과 일치 여부. **/
  IN,
  /** 여러 조건 값 중 어떤 값과도 일치가 되지 않는지 여부. **/
  NOT_IN
}
