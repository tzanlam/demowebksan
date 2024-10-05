import { configureStore } from '@reduxjs/toolkit';
import roomReducer from './slices/roomSlice';
import bookingReducer from './slices/bookingSlice';
import accountReducer from './slices/accountSlice';
import authReducer from './slices/authSlice';

const store = configureStore({
  reducer: {
    room: roomReducer,
    booking: bookingReducer,
    account: accountReducer,
    auth: authReducer,
  },
});

export default store;
