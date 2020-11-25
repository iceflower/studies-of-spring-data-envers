package org.iceflower.study.envers.employee;

import static java.util.stream.Collectors.toList;
import static org.iceflower.study.envers.SearchOperation.EQUAL;
import static org.iceflower.study.envers.SearchOperation.MATCH_START;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.iceflower.study.envers.SearchCriteria;
import org.iceflower.study.envers.exception.DuplicatedEmailException;
import org.iceflower.study.envers.exception.EmployeeNotFoundException;
import org.iceflower.study.envers.exception.EmployeeRevisionNotFoundException;
import org.iceflower.study.envers.web.employee.command.EmployeePagedListQueryCommand;
import org.iceflower.study.envers.web.employee.command.EmployeePagedRivisionListQueryCommand;
import org.iceflower.study.envers.web.employee.command.EmployeeSaveCommand;
import org.iceflower.study.envers.web.employee.command.EmployeeUpdateCommand;
import org.iceflower.study.envers.web.employee.statement.EmployeeStatement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeInformationManager {
  private final EmployeeRepository employeeRepository;

  public EmployeeStatement saveNewEmployee(EmployeeSaveCommand command)
      throws DuplicatedEmailException {

    if (employeeRepository.existsByEmail(command.getEmail())) {
      throw new DuplicatedEmailException(command.getEmail());
    }

    var newEmployee = Employee.builder()
        .email(command.getEmail())
        .firstName(command.getFirstName())
        .middleName(command.getMiddleName())
        .lastName(command.getLastName())
        .gender(command.getGender())
        .requestedBy(command.getRequestedBy())
        .build();

    return employeeRepository.save(newEmployee)
        .toStatement();
  }

  public EmployeeStatement updateEmployee(EmployeeUpdateCommand command)
      throws EmployeeNotFoundException, DuplicatedEmailException {

    var savedEmployee = employeeRepository.findById(command.getEmployeeNo())
        .orElseThrow(() -> new EmployeeNotFoundException(command.getEmployeeNo()));


    if (employeeRepository.existsByEmail(command.getEmail())
        && !savedEmployee.getEmail().equals(command.getEmail())) {
      throw new DuplicatedEmailException(command.getEmail());
    }

    var requestedBy = command.getRequestedBy();

    savedEmployee.setEmail(command.getEmail(), requestedBy);
    savedEmployee.setFirstName(command.getFirstName(), requestedBy);
    savedEmployee.setMiddleName(command.getMiddleName(), requestedBy);
    savedEmployee.setLastName(command.getLastName(), requestedBy);
    savedEmployee.setGender(command.getGender(), requestedBy);

    return employeeRepository.save(savedEmployee)
        .toStatement();
  }

  public void deleteEmployee(long employeeNo) throws EmployeeNotFoundException {

    if (!employeeRepository.existsById(employeeNo)) {
      throw new EmployeeNotFoundException(employeeNo);
    }

    employeeRepository.deleteById(employeeNo);
  }

  public EmployeeStatement getEmployee(long employeeNo) throws EmployeeNotFoundException {

    return employeeRepository.findById(employeeNo)
        .map(Employee::toStatement)
        .orElseThrow(() -> new EmployeeNotFoundException(employeeNo));
  }

  public Page<EmployeeStatement> getEmployeeList(EmployeePagedListQueryCommand command) {
    var spec = getEmployeeSearchSpec(command);


    if (spec.getConditionsCount() > 0) {

      var employees = employeeRepository.findAll(spec, command.getPageRequest());
      var statements = employees.get()
          .map(Employee::toStatement)
          .collect(toList());

      return new PageImpl<>(statements, command.getPageRequest(), employees.getTotalElements());
    }

    var employees = employeeRepository.findAll(command.getPageRequest());
    var statements = employees.get()
        .map(Employee::toStatement)
        .collect(toList());

    return new PageImpl<>(statements, command.getPageRequest(), employees.getTotalElements());
  }


  public Page<Revision<Integer, EmployeeStatement>> getEmployeeRevisionList(
      EmployeePagedRivisionListQueryCommand command) throws EmployeeRevisionNotFoundException {

    if (employeeRepository.findRevisions(command.getEmployeeNo()).isEmpty()) {
      throw new EmployeeRevisionNotFoundException(command.getEmployeeNo());
    }

    var revisions =
        employeeRepository.findRevisions(command.getEmployeeNo(), command.getPageRequest());

    var revisionStatements = revisions.get()
        .map(r -> Revision.of(r.getMetadata(), r.getEntity().toStatement()))
        .collect(toList());

    return new PageImpl<>(
        revisionStatements,
        command.getPageRequest(),
        revisions.getTotalElements());
  }

  private EmployeeSpecification getEmployeeSearchSpec(EmployeePagedListQueryCommand command) {
    var specification = new EmployeeSpecification();

    if (command.getEmployeeNo() != null) {
      specification.add(SearchCriteria.of("id", command.getEmployeeNo(), EQUAL));
    }

    if (command.getEmail() != null) {
      specification.add(SearchCriteria.of("email", command.getEmail(), MATCH_START));
    }

    if (command.getFirstName() != null) {
      specification.add(SearchCriteria.of("firstName", command.getFirstName(), MATCH_START));
    }

    if (command.getMiddleName() != null) {
      specification.add(SearchCriteria.of("middleName", command.getMiddleName(), MATCH_START));
    }

    if (command.getLastName() != null) {
      specification.add(SearchCriteria.of("lastName", command.getLastName(), MATCH_START));
    }

    if (command.getGender() != null) {
      specification.add(SearchCriteria.of("gender", command.getLastName(), EQUAL));
    }

    return specification;
  }
}
