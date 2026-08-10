package io.herald.MySpringWeb.Repository;

import io.herald.MySpringWeb.Model.UserTable;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Repository -> Uses JPA and Hibernate to connect to our required database and table
public interface UserRepository extends JpaRepository<UserTable, Integer>{
    // Custom Query
    // Join Queries

    boolean existsByUsernameAndPassword(String un, String pwd);

    UserTable findByUsername(String username);
}