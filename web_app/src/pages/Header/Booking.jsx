import React from 'react';
import { Form, Button, DatePicker, Select } from 'antd';
import { useDispatch, useSelector } from 'react-redux';
import { createBooking } from '../../redux/slices/bookingSlice';
import { showLoginModal } from '../../redux/slices/authSlice';
import { Room } from '../../helpers/constants/Room';
import { toast } from 'react-toastify';

const BookingPage = () => {
  const dispatch = useDispatch();
  const { user, isAuthenticated } = useSelector(state => state.auth);

  const handleSubmit = (values) => {
    if (!isAuthenticated) {
      // Nếu chưa đăng nhập, hiển thị modal đăng nhập
      toast.error("Đăng nhập để có thể đặt phòng");
      dispatch(showLoginModal());
      return;
    }

    // Xử lý đặt phòng
    const bookingDetails = {
      ...values,
      idUser: user.id,
    };
    dispatch(createBooking(bookingDetails));
  };

  return (
    <div className="booking-page">
      <h2>Đặt phòng</h2>
      <Form onFinish={handleSubmit}>
        <Form.Item
          name="idRoom"
          label="Phòng"
          rules={[{ required: true, message: 'Vui lòng chọn phòng!' }]}
        >
          <Select placeholder="Chọn loại phòng">
            {Room.map(room => (
              <Select.Option key={room.key} value={room.value}>
                {room.value}
              </Select.Option>
            ))}
          </Select>
        </Form.Item>
        <Form.Item
          name="checkin"
          label="Ngày nhận phòng"
          rules={[{ required: true, message: 'Vui lòng chọn ngày nhận phòng!' }]}
        >
          <DatePicker />
        </Form.Item>
        <Form.Item
          name="checkout"
          label="Ngày trả phòng"
          rules={[{ required: true, message: 'Vui lòng chọn ngày trả phòng!' }]}
        >
          <DatePicker />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit">Đặt phòng</Button>
        </Form.Item>
      </Form>
    </div>
  );
};

export default BookingPage;
