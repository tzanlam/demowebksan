import React from 'react';
import { Image, Layout, Menu } from 'antd';
import { Content, Footer, Header } from 'antd/es/layout/layout';
import MenuItem from 'antd/es/menu/MenuItem';
import { Link, Outlet } from 'react-router-dom';
import logoHP from '../../assets/hotel/logoHP.png'; // Điều chỉnh đường dẫn theo cấu trúc dự án của bạn
import LoginModal from '../Auth/LoginModal';
import RegisterModal from '../../components/Auth/ResgisterModal'; // Đảm bảo đường dẫn đúng
import { useDispatch, useSelector } from 'react-redux';
import { showLoginModal, showRegisterModal, hideLoginModal, hideRegisterModal } from '../../redux/slices/authSlice';

const MainLayout = () => {
  const dispatch = useDispatch();
  const { showLoginModal, showRegisterModal } = useSelector(state => state.auth);

  const handleLoginModalCancel = () => {
    dispatch(hideLoginModal());
  };

  const handleRegisterModalCancel = () => {
    dispatch(hideRegisterModal());
  };

  return (
    <Layout className="layoutset">
      <Header style={{ padding: "0px", margin: "0px" }}>
        <Menu mode="horizontal" defaultSelectedKeys={['1']}>
          <MenuItem key="0" style={{ fontWeight: "bold", fontSize: "18px" }}>
            <Link to="/">
              <Image style={{ width: '50px', height: '50px' }} src={logoHP} preview={false} />
            </Link>
          </MenuItem>
          <MenuItem key="1">
            <Link to="/">TRANG CHỦ</Link>
          </MenuItem>
          <MenuItem key="2">
            <Link to="/type-room">LOẠI PHÒNG</Link>
          </MenuItem>
          <MenuItem key="3">
            <Link to="/booking">ĐẶT PHÒNG</Link>
          </MenuItem>
          <MenuItem key="4">
            <Link to="/more-service">DỊCH VỤ KHÁC</Link>
          </MenuItem>
          <MenuItem key="5">
            <Link to="/sales">ƯU ĐÃI</Link>
          </MenuItem>
          {/* Đẩy nhóm account sang bên phải */}
          <MenuItem key="6" style={{ marginLeft: "auto" }} onClick={() => dispatch(showRegisterModal())}>
            Đăng ký
          </MenuItem>
          {/* Khi click vào menu đăng nhập, show modal */}
          <MenuItem key="7" onClick={() => dispatch(showLoginModal())}>
            Đăng nhập
          </MenuItem>
        </Menu>
      </Header>
      <Content style={{ padding: "0px" }}>
        <div className="site-layout-content">
          <Outlet />
        </div>
      </Content>
      <Footer style={{ textAlign: "center" }}>
        <Menu mode="horizontal">
          <MenuItem key='1'>
            <Link to="/security-policy">Chính sách bảo mật</Link>
          </MenuItem>
          <MenuItem key='2'>
            <Link to="/support-policy">Chính sách hỗ trợ</Link>
          </MenuItem>
          <MenuItem key='3'>
            <Link to="/refuse-service">Từ chối phục vụ</Link>
          </MenuItem>
        </Menu>
      </Footer>

      {/* Modal đăng nhập */}
      <LoginModal />

      {/* Modal đăng ký */}
      <RegisterModal
        isVisible={showRegisterModal}
        handleCancel={handleRegisterModalCancel}
      />
    </Layout>
  );
};

export default MainLayout;
