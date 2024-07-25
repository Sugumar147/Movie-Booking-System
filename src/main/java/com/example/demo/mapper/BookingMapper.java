package com.example.demo.mapper;

import com.example.demo.model.Ticket;
import com.example.demo.handler.StringListTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface BookingMapper {

    @Insert("INSERT INTO bookings (username, booking_id, movie, theatre, timing, seatlist, amount) " +
            "VALUES (#{userName}, #{bookingId}, #{movie}, #{theatre}, #{timing}, #{seatNumbers, jdbcType=ARRAY}," +
            " #{amount})")
    void insertBooking(String userName, String bookingId,String movie,String theatre,String timing,
                       String[] seatNumbers,int amount);


    @Select("SELECT seatlist FROM bookings WHERE theatre = #{theatreName} AND timing = #{timing}")
    String[] getOccupiedSeatsAsString(@Param("theatreName") String theatreName, @Param("timing") String timing);

    default List<String> getOccupiedSeats(String theatreName, String timing) {

        String seatListAsString = Arrays.toString(getOccupiedSeatsAsString(theatreName, timing));

        return Arrays.stream(seatListAsString.split(","))
                .collect(Collectors.toList());
    }
    @Select("SELECT * FROM bookings WHERE username = #{userName}")
    @Results({
            @Result(property = "bookingId", column = "booking_id"),
            @Result(property = "seatNumbers" ,column = "seatlist", jdbcType = JdbcType.VARCHAR, typeHandler = StringListTypeHandler.class)
    })
    List<Ticket> getAllBookingsByUserName(String userName);
}
