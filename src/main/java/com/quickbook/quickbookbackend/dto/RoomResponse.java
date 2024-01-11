package com.quickbook.quickbookbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.quickbook.quickbookbackend.entity.Room;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomResponse {
    private int roomId;  // Added roomId
    private int roomNumber;
    private int numberOfBeds;

    // Additional room-related properties can be added here

    public static RoomResponse fromRoom(Room room) {
        return RoomResponse.builder()
                .roomId(room.getId())  // Set the roomId
                .roomNumber(room.getRoomNumber())
                .numberOfBeds(room.getNumberOfBeds())
                // Map other properties if needed
                .build();
    }

    public static List<RoomResponse> fromRooms(List<Room> rooms) {
        return rooms.stream()
                .map(RoomResponse::fromRoom)
                .collect(Collectors.toList());
    }
}
