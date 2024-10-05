// src/services/AuthService.js
import axiosInstance from "./axiosConfigs"; // axiosInstance đã cấu hình baseURL

const AuthService = {
  login(loginForm) {
    // Gửi yêu cầu POST đến API login với thông tin từ form đăng nhập
    return axiosInstance.post("/noAuth/login", loginForm);
  },
  saveToken(token) {
    // Lưu JWT token vào localStorage sau khi đăng nhập thành công
    localStorage.setItem("jwtToken", token);
  },
  getToken() {
    // Lấy JWT token từ localStorage để sử dụng trong các API yêu cầu xác thực
    return localStorage.getItem("jwtToken");
  },
  logout() {
    // Xóa token khỏi localStorage khi người dùng đăng xuất
    localStorage.removeItem("jwtToken");
  },
};
export default AuthService;
