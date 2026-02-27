package context;

public class BookingContext {
    private static BookingContext instance;
    private int bookingId;


    private BookingContext() {}

    public static BookingContext getInstance() {
        if (instance == null) {
            instance = new BookingContext();
        }
        return instance;
    }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

}
