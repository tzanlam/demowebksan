package no.modal.request;

import lombok.Data;

@Data
public class BookingFilterRequest {
    private long roomId;
    private String checkinStart;
    private String checkinEnd;
    private String checkoutStart;
    private String checkoutEnd;
}
