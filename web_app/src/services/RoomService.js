import axiosInstance from "./axiosConfigs";

const RoomService = {
    getAll(page, size){
        return axiosInstance.get(`Room/getALlRooms/${page}/${size}`)
    },
    getId(id){
        return axiosInstance.get(`Room/findById/${id}`)
    },
    create(body){
        return axiosInstance.post("Room/createRoom", body)
    },
    update(id, body){
        return axiosInstance.put("Room/updateRoom/"+id, body)
    },
    delete(id){
        return axiosInstance.get(`Room/deleteRoom/${id}`)
    }
}
export default RoomService;