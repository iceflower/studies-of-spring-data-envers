package org.iceflower.study.envers.employee.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephoneNumberRepository
    extends JpaRepository<TelephoneNumber, Long>,
    RevisionRepository<TelephoneNumber, Long, Integer>,
    JpaSpecificationExecutor<TelephoneNumber> {

}
