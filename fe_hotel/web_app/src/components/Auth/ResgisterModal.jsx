import React from "react";
import { Modal, Form, Input, Button, DatePicker } from "antd";
import { useDispatch } from "react-redux";
// eslint-disable-next-line no-unused-vars
import { register, hideRegisterModal } from "../../redux/slices/authSlice";
import moment from 'moment';

const RegisterModal = ({ isVisible, handleCancel }) => {
  const [form] = Form.useForm();
  const dispatch = useDispatch();

  const handleRegister = (values) => {
    // Dispatch action đăng ký tài khoản với dữ liệu từ form
    dispatch(register(values));
    handleCancel(); // Đóng modal sau khi đăng ký thành công
  };

  return (
    <Modal
      title="Đăng ký tài khoản"
      open={isVisible}
      onCancel={handleCancel}
      footer={null}
    >
      <Form
        form={form}
        onFinish={handleRegister}
        layout="vertical"
      >
        <Form.Item
          label="Tên đăng nhập"
          name="username"
          rules={[{ required: true, message: "Vui lòng nhập tên đăng nhập!" }]}
        >
          <Input placeholder="Nhập tên đăng nhập" />
        </Form.Item>

        <Form.Item
          label="Mật khẩu"
          name="password"
          rules={[{ required: true, message: "Vui lòng nhập mật khẩu!" }]}
        >
          <Input.Password placeholder="Nhập mật khẩu" />
        </Form.Item>

        <Form.Item
          label="Họ và tên"
          name="fullname"
          rules={[{ required: true, message: "Vui lòng nhập họ và tên!" }]}
        >
          <Input placeholder="Nhập họ và tên" />
        </Form.Item>

        <Form.Item
          label="Email"
          name="email"
          rules={[{ required: true, message: "Vui lòng nhập email!" }]}
        >
          <Input placeholder="Nhập email" />
        </Form.Item>

        <Form.Item
          label="Số điện thoại"
          name="phoneNumber"
          rules={[{ required: true, message: "Vui lòng nhập số điện thoại!" }]}
        >
          <Input placeholder="Nhập số điện thoại" />
        </Form.Item>

        <Form.Item
          label="Ngày sinh"
          name="birthDate"
          rules={[{ required: true, message: "Vui lòng chọn ngày sinh!" }]}
        >
          <DatePicker
            format="DD/MM/YYYY"
            // Set giá trị để hiển thị
            value={form.getFieldValue('birthDate') ? moment(form.getFieldValue('birthDate'), "DD/MM/YYYY") : null}
          />
        </Form.Item>

        <Form.Item
          label="Số CMND/CCCD"
          name="idCard"
          rules={[{ required: true, message: "Vui lòng nhập số CMND/CCCD!" }]}
        >
          <Input placeholder="Nhập số CMND/CCCD" />
        </Form.Item>

        <Form.Item
          label="Địa chỉ"
          name="address"
          rules={[{ required: true, message: "Vui lòng nhập địa chỉ!" }]}
        >
          <Input placeholder="Nhập địa chỉ" />
        </Form.Item>

        <Form.Item>
          <Button type="primary" htmlType="submit">
            Đăng ký
          </Button>
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default RegisterModal;
