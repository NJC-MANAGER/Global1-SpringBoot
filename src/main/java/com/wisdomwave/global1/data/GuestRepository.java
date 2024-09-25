package com.wisdomwave.global1.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}


//CHALLENGE: ADDITIONAL REPOSITORIES
//1. Implement Repositories for our guest and reservation tables
//2. Don't forget the entities(create the entity objects, so you can use them with those repositories' interfaces)
//3. Leverage the schema.sql file in src/main/resources
//4. On the Reservation entity, add a method to get the Reservations for a given date(Hint: the name is all that really matters; make sure the name matches)