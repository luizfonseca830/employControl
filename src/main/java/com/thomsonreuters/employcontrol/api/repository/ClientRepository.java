package com.thomsonreuters.employcontrol.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thomsonreuters.employcontrol.api.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
