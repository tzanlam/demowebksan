import { createSlice } from "@reduxjs/toolkit";
import BookingService from "../../services/BookingService";

const bookingSlice = createSlice({
  name: "booking",
  initialState: {
    bookings: [],
    status: "idle",
    error: null,
  },
  reducers: {
    setBookings: (state, action) => {
      state.bookings = action.payload;
    },
    addBooking: (state, action) => {
      state.bookings.push(action.payload);
    },
    updateBooking: (state, action) => {
      const index = state.bookings.findIndex(
        (booking) => booking.id === action.payload.id
      );
      if (index !== -1) {
        state.bookings[index] = action.payload;
      }
    },
    deleteBooking: (state, action) => {
      state.bookings = state.bookings.filter(
        booking => booking.id !== action.payload
      );
    },
    setStatus: (state, action) => {
      state.status = action.payload;
    },
    setError: (state, action) => {
      state.error = action.payload; // Chỉnh sửa: cập nhật state.error thay vì state.status
    },
  },
});

export const {
  setBookings,
  addBooking,
  updateBooking,
  deleteBooking,
  setStatus,
  setError,
} = bookingSlice.actions;

export const fetchBookings = (page, size) => async (dispatch) => {
  dispatch(setStatus('loading'));
  try {
    const response = await BookingService.getAll(page, size);
    dispatch(setBookings(response.data));
    dispatch(setStatus("succeeded")); // Chỉnh sửa: "succeeded" thay vì "successed"
  } catch (error) {
    dispatch(setError(error.message));
    dispatch(setStatus("failed")); // Chỉnh sửa: "failed" thay vì gọi setError hai lần
  }
};

export const createBooking = (booking) => async (dispatch) => {
  dispatch(setStatus('loading'));
  try {
    const response = await BookingService.create(booking);
    dispatch(addBooking(response.data));
    dispatch(setStatus("succeeded")); // Thêm: Cập nhật trạng thái thành công
  } catch (error) {
    dispatch(setError(error.message));
    dispatch(setStatus("failed")); // Thêm: Cập nhật trạng thái lỗi
  }
};

export const editBooking = (id, booking) => async (dispatch) => {
  dispatch(setStatus('loading'));
  try {
    const response = await BookingService.update(id, booking);
    dispatch(updateBooking(response.data));
    dispatch(setStatus("succeeded")); // Thêm: Cập nhật trạng thái thành công
  } catch (error) {
    dispatch(setError(error.message));
    dispatch(setStatus("failed")); // Thêm: Cập nhật trạng thái lỗi
  }
};

export const removeBooking = (id) => async (dispatch) => {
  dispatch(setStatus('loading')); // Thêm: Cập nhật trạng thái khi xóa
  try {
    await BookingService.delete(id); // Chỉnh sửa: sử dụng BookingService để xóa
    dispatch(deleteBooking(id));
    dispatch(setStatus("succeeded")); // Thêm: Cập nhật trạng thái thành công
  } catch (error) {
    dispatch(setError(error.message));
    dispatch(setStatus("failed")); // Thêm: Cập nhật trạng thái lỗi
  }
};

export default bookingSlice.reducer;
