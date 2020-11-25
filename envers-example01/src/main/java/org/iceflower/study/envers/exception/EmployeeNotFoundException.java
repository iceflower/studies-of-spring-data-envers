package org.iceflower.study.envers.exception;

public class EmployeeNotFoundException extends RuntimeException {

  public EmployeeNotFoundException(long employeeNo) {
    super(String.format("%d번 사원번호는 존재하지 않습니다.", employeeNo));
  }
}
