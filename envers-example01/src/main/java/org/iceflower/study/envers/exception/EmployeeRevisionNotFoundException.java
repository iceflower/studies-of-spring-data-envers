package org.iceflower.study.envers.exception;

public class EmployeeRevisionNotFoundException extends RuntimeException {

  public EmployeeRevisionNotFoundException(long employeeNo) {
    super(String.format("%d번 사원번호의 수정이력이 존재하지 않습니다.", employeeNo));
  }
}
