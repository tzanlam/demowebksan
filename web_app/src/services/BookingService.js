import axiosInstance from "./axiosConfigs";

const BookingService = {
    create(body){
        return axiosInstance.post('Booking/createBooking', body)
    },
    getAll(page, size){
        return axiosInstance.get(`Booking/getAllBookings/${page}/${size}`)
    },
    getId(id){
        return axiosInstance.get(`Booking/findById/${id}`)
    },
    update(id, body){
        return axiosInstance.put(`Booking/updateBooking/${id}`, body)
    },
    delete(id){
        return axiosInstance.get(`Booking/deleteBooking/${id}`)
    }
}
export default BookingService;