package com.example.demo.mapper;

import com.example.demo.model.Booking;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookingMapper {
    @Insert("INSERT INTO booking (theatre_name, timing, seat_number) " +
            "VALUES (#{theatreName}, #{timing}, #{seatNumber})")
    void insertBooking(Booking booking);

    @Select("SELECT seat_number FROM booking WHERE theatre_name = #{theatreName} AND timing = #{timing}")
    List<String> getOccupiedSeats(@Param("theatreName") String theatreName, @Param("timing") String timing);

}
