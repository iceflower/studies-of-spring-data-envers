package org.iceflower.study.envers.web.employee;

import lombok.RequiredArgsConstructor;
import org.iceflower.study.envers.employee.EmployeeInformationManager;
import org.iceflower.study.envers.exception.DuplicatedEmailException;
import org.iceflower.study.envers.exception.EmployeeNotFoundException;
import org.iceflower.study.envers.exception.EmployeeRevisionNotFoundException;
import org.iceflower.study.envers.web.employee.command.EmployeePagedListQueryCommand;
import org.iceflower.study.envers.web.employee.command.EmployeePagedRivisionListQueryCommand;
import org.iceflower.study.envers.web.employee.command.EmployeeSaveCommand;
import org.iceflower.study.envers.web.employee.command.EmployeeUpdateCommand;
import org.iceflower.study.envers.web.employee.statement.EmployeeStatement;
import org.springframework.data.domain.Page;
import org.springframework.data.history.Revision;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/employee")
@RestController
@RequiredArgsConstructor
public class EmployeeRestController {

  private final EmployeeInformationManager employeeInformationManager;

  @PostMapping("/add")
  public ResponseEntity<EmployeeStatement> save(
      @RequestBody @Validated EmployeeSaveCommand command) {

    var statement = employeeInformationManager.saveNewEmployee(command);

    return ResponseEntity.ok(statement);
  }

  @PutMapping("/edit")
  public ResponseEntity<EmployeeStatement> update(
      @RequestBody @Validated EmployeeUpdateCommand command) {
    var statement = employeeInformationManager.updateEmployee(command);
    return ResponseEntity.ok(statement);
  }

  @GetMapping("/{employeeNo}")
  public ResponseEntity<EmployeeStatement> getEmployee(@PathVariable long employeeNo) {
    var statement = employeeInformationManager.getEmployee(employeeNo);
    return ResponseEntity.ok(statement);
  }

  @DeleteMapping("/{employeeNo}")
  public ResponseEntity<?> deleteEmployee(@PathVariable long employeeNo) {
    employeeInformationManager.deleteEmployee(employeeNo);
    return ResponseEntity.ok().body("deleted");
  }

  @GetMapping("/list")
  public ResponseEntity<Page<EmployeeStatement>> getPagedList(
      EmployeePagedListQueryCommand command) {
    var pagedStatement = employeeInformationManager.getEmployeeList(command);
    return ResponseEntity.ok(pagedStatement);
  }

  @GetMapping("/revision/list")
  public Page<Revision<Integer, EmployeeStatement>> getPagedRevisionList(
      EmployeePagedRivisionListQueryCommand command) {
    var pagedStatement = employeeInformationManager.getEmployeeRevisionList(command);
    return pagedStatement;
  }

  @ExceptionHandler(DuplicatedEmailException.class)
  public ResponseEntity<String> handleDuplicatedEmailException(DuplicatedEmailException e) {
    return ResponseEntity.badRequest()
        .body(e.getMessage());
  }

  @ExceptionHandler(EmployeeNotFoundException.class)
  public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException e) {
    return ResponseEntity.badRequest()
        .body(e.getMessage());
  }

  @ExceptionHandler(EmployeeRevisionNotFoundException.class)
  public ResponseEntity<String> handleEmployeeRevisionNotFoundException(
      EmployeeRevisionNotFoundException e) {
    return ResponseEntity.badRequest()
        .body(e.getMessage());
  }
}
