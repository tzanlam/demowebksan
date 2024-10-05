import React from "react";
import { Modal, Form, Input, Button, notification } from "antd";
import { useDispatch, useSelector } from "react-redux";
import { login, hideLoginModal } from "../../redux/slices/authSlice";

const LoginModal = () => {
  const dispatch = useDispatch();
  const { showLoginModal } = useSelector(state => state.auth);

  const [form] = Form.useForm();

  const handleLogin = async (values) => {
    try {
      await dispatch(login(values));
      dispatch(hideLoginModal()); // Đóng modal sau khi đăng nhập thành công
    } catch (error) {
      notification.error({
        message: 'Đăng nhập thất bại',
        description: 'Tên đăng nhập hoặc mật khẩu không chính xác.',
      });
    }
  };

  return (
    <Modal
      title="Đăng nhập"
      visible={showLoginModal}
      onCancel={() => dispatch(hideLoginModal())}
      footer={null}
    >
      <Form
        form={form}
        onFinish={handleLogin}
        layout="vertical"
        initialValues={{ username: "", password: "" }}
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
        <Form.Item>
          <Button type="primary" htmlType="submit">
            Đăng nhập
          </Button>
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default LoginModal;
