import { createSlice } from '@reduxjs/toolkit';
import RoomService from "../../services/RoomService";
import { toast } from 'react-toastify';

const roomSlice = createSlice({
  name: 'room',
  initialState: {
    rooms: [],
  },
  reducers: {
    setRooms: (state, action) => {
      state.rooms = action.payload;
    },
    addRoom: (state, action) => {
      state.rooms.push(action.payload);
    },
    updateRoom: (state, action) => {
      const index = state.rooms.findIndex(room => room.id === action.payload.id);
      if (index !== -1) {
        state.rooms[index] = action.payload;
      }
    },
    deleteRoom: (state, action) => {
      state.rooms = state.rooms.filter(room => room.id !== action.payload);
    },
  },
});

export const { setRooms, addRoom, updateRoom, deleteRoom } = roomSlice.actions;

export const fetchRooms = (page, size) => async (dispatch) => {
  try {
    const response = await RoomService.getAll(page, size);
    if (response && response.data) {
      dispatch(setRooms(response.data));
      toast.success("Phòng đã được tải thành công!");
    } else {
      throw new Error('Không có dữ liệu phòng');
    }
  } catch (error) {
    toast.error(`Lỗi khi tải phòng: ${error.message}`);
  }
};

export const createRoom = (room) => async (dispatch) => {
  try {
    const response = await RoomService.create(room);
    if (response && response.data) {
      dispatch(addRoom(response.data));
      toast.success("Phòng mới đã được thêm thành công!");
    } else {
      throw new Error('Không thể thêm phòng mới');
    }
  } catch (error) {
    toast.error(`Lỗi khi thêm phòng: ${error.message}`);
  }
};

export const editRoom = (id, room) => async (dispatch) => {
  try {
    const response = await RoomService.update(id, room);
    if (response && response.data) {
      dispatch(updateRoom(response.data));
      toast.success("Cập nhật phòng thành công!");
    } else {
      throw new Error('Không thể cập nhật phòng');
    }
  } catch (error) {
    toast.error(`Lỗi khi cập nhật phòng: ${error.message}`);
  }
};

export const removeRoom = (id) => async (dispatch) => {
  try {
    await RoomService.delete(id);
    dispatch(deleteRoom(id));
    toast.success("Xóa phòng thành công!");
  } catch (error) {
    toast.error(`Lỗi khi xóa phòng: ${error.message}`);
  }
};

export default roomSlice.reducer;
