export interface Trip {
    id: Number;
    pickupPlace: String;
    dropOffPlace: String;
    tripStartingTime: Date;
    tripEndTime: Date;
    isRoundTrip: boolean;
    numberOfAvailableSeats: Number;
    requestedReserveSeat: Number;
    tripPrice: Number;
    tripDescription: String;
}