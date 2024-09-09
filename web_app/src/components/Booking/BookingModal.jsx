import React, { useState, useEffect } from 'react';
import { Modal, Form, Input, Button, Select } from 'antd';
import { useDispatch, useSelector } from 'react-redux';
import { createBooking } from '../../redux/slices/bookingSlice'; // Tạo slice booking
import { showLoginModal } from '../../redux/slices/authSlice'; // Tạo slice cho đăng nhập
import moment from 'moment';

const BookingModal = ({ visible, onCancel }) => {
  const [form] = Form.useForm();
  const dispatch = useDispatch();

  // Lấy trạng thái từ redux
  const { isLoggedIn, user } = useSelector((state) => state.auth);
  const { rooms } = useSelector((state) => state.room);

  // Tạo trạng thái cho việc chọn phòng
  // eslint-disable-next-line no-unused-vars
  const [selectedRoom, setSelectedRoom] = useState(null);

  // Hiệu ứng để kiểm tra đăng nhập
  useEffect(() => {
    if (!isLoggedIn) {
      dispatch(showLoginModal()); // Hiện modal login nếu chưa đăng nhập
    }
  }, [isLoggedIn, dispatch]);

  const handleSubmit = (values) => {
    if (!isLoggedIn) {
      dispatch(showLoginModal());
    } else {
      const bookingData = {
        idUser: user.id, // Lấy idUser từ thông tin người dùng
        idRoom: values.room,
        typeBooking: values.typeBooking,
        price: values.price,
        checkin: moment(values.checkin).format('YYYY-MM-DD HH:mm:ss'),
        checkout: moment(values.checkout).format('YYYY-MM-DD HH:mm:ss'),
      };
      dispatch(createBooking(bookingData)); // Gọi action để tạo booking
      form.resetFields();
      onCancel();
    }
  };

  return (
    <Modal
      open={visible}
      title="Booking Room"
      onCancel={onCancel}
      footer={null}
    >
      <Form
        form={form}
        layout="vertical"
        onFinish={handleSubmit}
      >
        <Form.Item
          label="Room"
          name="room"
          rules={[{ required: true, message: 'Please select a room!' }]}
        >
          <Select
            placeholder="Select a room"
            onChange={(value) => setSelectedRoom(value)}
          >
            {rooms.map((room) => (
              <Select.Option key={room.id} value={room.id}>
                {room.name}
              </Select.Option>
            ))}
          </Select>
        </Form.Item>

        <Form.Item
          label="Type of Booking"
          name="typeBooking"
          rules={[{ required: true, message: 'Please select booking type!' }]}
        >
          <Select placeholder="Select booking type">
            <Select.Option value="SHORT_TERM">Short Term</Select.Option>
            <Select.Option value="LONG_TERM">Long Term</Select.Option>
          </Select>
        </Form.Item>

        <Form.Item
          label="Price"
          name="price"
          rules={[{ required: true, message: 'Please input the price!' }]}
        >
          <Input type="number" />
        </Form.Item>

        <Form.Item
          label="Check-in Date"
          name="checkin"
          rules={[{ required: true, message: 'Please select check-in date!' }]}
        >
          <Input type="datetime-local" />
        </Form.Item>

        <Form.Item
          label="Check-out Date"
          name="checkout"
          rules={[{ required: true, message: 'Please select check-out date!' }]}
        >
          <Input type="datetime-local" />
        </Form.Item>

        <Form.Item>
          <Button type="primary" htmlType="submit" style={{ width: '100%' }}>
            Book Room
          </Button>
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default BookingModal;
