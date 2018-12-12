package ebooking.cinema;

import java.util.ArrayList;
import java.util.List;

public class SeatList {

    List<Seat> seatList = new ArrayList<Seat>();

    public void addSeat(Seat seat) {
        this.seatList.add(seat);
    }

    public void addSeatList(List<Seat> newSeatList) {
        for(Seat seat: newSeatList) {
            this.seatList.add(seat);
        }
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }
}
