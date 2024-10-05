import axiosInstance from "./axiosConfigs"

const AccountService = {
    create(body){
        return axiosInstance.post('Account/createAccount', body)
    },
    getAll(page, size){
        return axiosInstance.get(`Account/getAllAccount/${page}/${size}`)
    },
    getId(id){
        return axiosInstance.get(`Account/getById/${id}`)
    },
    update(id, body){
        return axiosInstance.put(`Account/updateAccount/${id}`)
    },
    delete(id){
        return axiosInstance.get(`Account/deleteAccount/${id}`)
    }
}
export default AccountService;