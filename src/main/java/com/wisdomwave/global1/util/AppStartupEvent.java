package com.wisdomwave.global1.util;

import com.wisdomwave.global1.data.Room;
import com.wisdomwave.global1.data.RoomRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
   private final RoomRepository roomRepository;

    public AppStartupEvent(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
Iterable<Room> rooms = this.roomRepository.findAll();
rooms.forEach(System.out::println);
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
