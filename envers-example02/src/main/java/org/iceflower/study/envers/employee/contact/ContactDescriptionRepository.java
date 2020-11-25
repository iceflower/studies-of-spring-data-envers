package org.iceflower.study.envers.employee.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDescriptionRepository
    extends JpaRepository<ContactDescription, Long>,
    RevisionRepository<ContactDescription, Long, Integer>,
    JpaSpecificationExecutor<ContactDescription> {

}
