import { createSlice } from "@reduxjs/toolkit";
import AuthService from "../../services/AuthService";
import { toast } from "react-toastify";
const initialState = {
  isAuthenticated: false,
  user: null,
  showLoginModal: false,
  showRegisterModal: false, // Thêm trạng thái modal đăng ký
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    loginSuccess(state, action) {
      state.isAuthenticated = true;
      state.user = action.payload;
      state.showLoginModal = false; // Đóng modal khi đăng nhập thành công
    },
    logout(state) {
      state.isAuthenticated = false;
      state.user = null;
      // Xóa token khi đăng xuất
    },
    registerSuccess(state, action) {
      state.isAuthenticated = true;
      state.user = action.payload;
      state.showLoginModal = false; // Đóng modal khi đăng ký thành công
    },
    showLoginModal(state) {
      state.showLoginModal = true; // Hiển thị modal đăng nhập
    },
    hideLoginModal(state) {
      state.showLoginModal = false; // Ẩn modal đăng nhập
    },
    showRegisterModal(state) {
      state.showRegisterModal = true; // Hiển thị modal đăng ký
    },
    hideRegisterModal(state) {
      state.showRegisterModal = false; // Ẩn modal đăng ký
    }
  },
});

export const { loginSuccess, logout, registerSuccess, showLoginModal, hideLoginModal, showRegisterModal, hideRegisterModal } = authSlice.actions;
export const login = (loginForm) => async (dispatch) => {
  try {
    const response = await AuthService.login(loginForm);
    const { token, account } = response.data; // Nhận token và thông tin user từ backend
    AuthService.saveToken(token); // Lưu token vào localStorage
    dispatch(loginSuccess(account)); // Cập nhật thông tin người dùng
    toast.success("Đăng nhập thành công!");
  } catch (error) {
    toast.error("Đăng nhập thất bại!");
  }
};

export const register = (registerForm) => async (dispatch) => {
  try {
    const response = await AuthService.register(registerForm);
    const { token, account } = response.data; // Nhận token và thông tin user từ backend sau khi đăng ký
    AuthService.saveToken(token); // Lưu token vào localStorage
    dispatch(registerSuccess(account)); // Cập nhật thông tin người dùng
    toast.success("Đăng ký thành công!");
  } catch (error) {
    toast.error("Đăng ký thất bại!");
  }
};

export default authSlice.reducer;
