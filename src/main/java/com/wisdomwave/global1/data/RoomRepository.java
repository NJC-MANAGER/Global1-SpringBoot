package com.wisdomwave.global1.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository            //shows that this is a data repository
public interface RoomRepository extends CrudRepository<Room, Long>{
}
