package org.iceflower.study.envers.exception;

public class DuplicatedEmailException extends RuntimeException {

  public DuplicatedEmailException(String email) {
    super(String.format("입력받은 이메일 주소 [%s]는 이미 사용중입니다.", email));
  }
}
