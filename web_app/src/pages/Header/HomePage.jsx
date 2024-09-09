import React from 'react';
import mainimg from '../../assets/hotel/mainimg.jpg';
import { Image } from 'antd';
const HomePage = () => {
    return (
        <Image  
        style={{
            width: '1396px', // Hoặc kích thước cụ thể
            height: 'auto', // Hoặc chiều cao cụ thể
            objectFit: 'contain' // Hoặc 'contain' tùy thuộc vào cách bạn muốn hình ảnh hiển thị
        }}
        src={mainimg} />
    );
};

export default HomePage;