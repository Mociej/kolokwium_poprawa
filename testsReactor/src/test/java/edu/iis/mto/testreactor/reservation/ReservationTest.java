package edu.iis.mto.testreactor.reservation;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReservationTest {

    @Mock
    Id id;

    Reservation.ReservationStatus status;
    @Mock
    ClientData clientData;
    @Mock
    Date createDate;

    Reservation reservation;

    @BeforeEach
    void setUp(){
        reservation=new Reservation(id,status,clientData,createDate);
    }

    @Test
    public void itCompiles() {
        assertThat(true, Matchers.equalTo(true));
    }

    @Test
    void test1(){

    }

}