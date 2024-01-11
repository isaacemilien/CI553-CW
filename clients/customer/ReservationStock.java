package clients.customer;

public class ReservationStock {
    private Integer reservationID;
    private String productNo;
    private Integer stockLevel;

    // Constructors


    public ReservationStock(Integer reservationID, String productNo, Integer stockLevel) {
        this.reservationID = reservationID;
        this.productNo = productNo;
        this.stockLevel = stockLevel;
    }


    public Integer getReservationID() {
        return reservationID;
    }


    public String getProductNo() {
        return productNo;
    }


    public Integer getStockLevel() {
        return stockLevel;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationID=" + reservationID +
                ", productNo='" + productNo + '\'' +
                ", stockLevel=" + stockLevel +
                '}';
    }
}
