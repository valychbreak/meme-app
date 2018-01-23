package com.int20h.task.memeapp.repository;

import com.int20h.task.memeapp.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Access to the user data. JpaRepository grants us convenient access methods here.
 */
public interface UserRepository extends CrudRepository<User, Long> {

	
	/**
	 * Find a user by username
	 *
	 * @param username the user's username
	 * @return user which contains the user with the given username or null.
	 */
	User findOneByUsername(String username);

}
