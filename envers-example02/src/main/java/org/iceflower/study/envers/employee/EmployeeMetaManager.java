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
import org.iceflower.study.envers.web.employee.meta.command.EmployeePagedListQueryCommand;
import org.iceflower.study.envers.web.employee.meta.command.EmployeePagedRivisionListQueryCommand;
import org.iceflower.study.envers.web.employee.meta.command.EmployeeSaveCommand;
import org.iceflower.study.envers.web.employee.meta.command.EmployeeUpdateCommand;
import org.iceflower.study.envers.web.employee.meta.statement.EmployeeStatement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeMetaManager {
  private final EmployeeMetaRepository employeeMetaRepository;

  public EmployeeStatement saveNewEmployee(EmployeeSaveCommand command)
      throws DuplicatedEmailException {

    if (employeeMetaRepository.existsByAccountId(command.getAccountId())) {
      throw new DuplicatedEmailException(command.getAccountId());
    }

    var newEmployee = EmployeeMeta.builder()
        .accountId(command.getAccountId())
        .firstName(command.getFirstName())
        .middleName(command.getMiddleName())
        .lastName(command.getLastName())
        .gender(command.getGender())
        .requestedBy(command.getRequestedBy())
        .build();

    return employeeMetaRepository.save(newEmployee)
        .toStatement();
  }

  public EmployeeStatement updateEmployee(EmployeeUpdateCommand command)
      throws EmployeeNotFoundException, DuplicatedEmailException {

    var savedEmployee = employeeMetaRepository.findById(command.getEmployeeNo())
        .orElseThrow(() -> new EmployeeNotFoundException(command.getEmployeeNo()));


    if (employeeMetaRepository.existsByAccountId(command.getAccountId())
        && !savedEmployee.getAccountId().equals(command.getAccountId())) {
      throw new DuplicatedEmailException(command.getAccountId());
    }

    var requestedBy = command.getRequestedBy();

    savedEmployee.setAccountId(command.getAccountId(), requestedBy);
    savedEmployee.setFirstName(command.getFirstName(), requestedBy);
    savedEmployee.setMiddleName(command.getMiddleName(), requestedBy);
    savedEmployee.setLastName(command.getLastName(), requestedBy);
    savedEmployee.setGender(command.getGender(), requestedBy);

    return employeeMetaRepository.save(savedEmployee)
        .toStatement();
  }

  public void deleteEmployee(long employeeNo) throws EmployeeNotFoundException {

    if (!employeeMetaRepository.existsById(employeeNo)) {
      throw new EmployeeNotFoundException(employeeNo);
    }

    employeeMetaRepository.deleteById(employeeNo);
  }

  public EmployeeStatement getEmployee(long employeeNo) throws EmployeeNotFoundException {

    return employeeMetaRepository.findById(employeeNo)
        .map(EmployeeMeta::toStatement)
        .orElseThrow(() -> new EmployeeNotFoundException(employeeNo));
  }

  public Page<EmployeeStatement> getEmployeeList(EmployeePagedListQueryCommand command) {
    var spec = getEmployeeSearchSpec(command);


    if (spec.getConditionsCount() > 0) {

      var employees = employeeMetaRepository.findAll(spec, command.getPageRequest());
      var statements = employees.get()
          .map(EmployeeMeta::toStatement)
          .collect(toList());

      return new PageImpl<>(statements, command.getPageRequest(), employees.getTotalElements());
    }

    var employees = employeeMetaRepository.findAll(command.getPageRequest());
    var statements = employees.get()
        .map(EmployeeMeta::toStatement)
        .collect(toList());

    return new PageImpl<>(statements, command.getPageRequest(), employees.getTotalElements());
  }


  public Page<Revision<Integer, EmployeeStatement>> getEmployeeRevisionList(
      EmployeePagedRivisionListQueryCommand command) throws EmployeeRevisionNotFoundException {

    if (employeeMetaRepository.findRevisions(command.getEmployeeNo()).isEmpty()) {
      throw new EmployeeRevisionNotFoundException(command.getEmployeeNo());
    }

    var revisions =
        employeeMetaRepository.findRevisions(command.getEmployeeNo(), command.getPageRequest());

    var revisionStatements = revisions.get()
        .map(r -> Revision.of(r.getMetadata(), r.getEntity().toStatement()))
        .collect(toList());

    return new PageImpl<>(
        revisionStatements,
        command.getPageRequest(),
        revisions.getTotalElements());
  }

  private EmployeeMetaSpecification getEmployeeSearchSpec(EmployeePagedListQueryCommand command) {
    var specification = new EmployeeMetaSpecification();

    if (command.getEmployeeNo() != null) {
      specification.add(SearchCriteria.of("id", command.getEmployeeNo(), EQUAL));
    }

    if (command.getAccountId() != null) {
      specification.add(SearchCriteria.of("accountId", command.getAccountId(), MATCH_START));
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
