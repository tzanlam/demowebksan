import axios from "axios"

// lấy token từ localStorage
const token = localStorage.getItem("token")

// tạo instance của axios với cấu hình mặc định
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8081/',
    timeout: 5000,
    headers: {
        "Content-Type": "application/json"
    }
})

//thêm token vào headers 
if (token){
    axiosInstance.defaults.headers.common["Authorization"] = `Bearer ${token}`
}

//interceptor xử lí response
axiosInstance.interceptors.response.use(
    (response) => {
        // success
        return response
    },
    (error) =>{
        //xử lí lỗi token hết hạn
        if(error.response.status === 401){
            localStorage.getItem(token);
            window.location.href = "/";
        }
        return Promise.reject(error)
    }
)

export default axiosInstance;