package com.adminconsole.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserAuthenticationRepository extends CrudRepository<UserAuthenticationEntity, String> {
}
