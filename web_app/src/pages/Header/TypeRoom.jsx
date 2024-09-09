/* eslint-disable no-unused-vars */
// BUSSINESS
import BUSS_1 from "../../assets/hotel/bussiness/BUSS_1.jpg";
import BUSS_2 from "../../assets/hotel/bussiness/BUSS_2.jpg";
import BUSS_3 from "../../assets/hotel/bussiness/BUSS_3.jpg";
import BUSS_4 from "../../assets/hotel/bussiness/BUSS_4.jpg";
import WC from "../../assets/hotel/bussiness/WC.jpg";
// COUPLE
import COUPLE_1 from "../../assets/hotel/couple/COUPLE_1.jpg";
import COUPLE_2 from "../../assets/hotel/couple/COUPLE_2.jpg";
import COUPLE_3 from "../../assets/hotel/couple/COUPLE_3.jpg";
import COUPLE_4 from "../../assets/hotel/couple/COUPLE_4.jpg";
import WCC from "../../assets/hotel/couple/WCC.jpg";
//FONTVIEW
import VIEW1_F from "../../assets/hotel/fontview/VIEW1_F.jpg";
import VIEW2_F from "../../assets/hotel/fontview/VIEW2_F.jpg";
import VIEW3_F from "../../assets/hotel/fontview/VIEW3_F.jpg";
import WC_F from "../../assets/hotel/fontview/WC_F.jpg";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchRooms } from "../../redux/slices/roomSlice";
import { Button, Card, Image, Modal } from "antd";
import "../../assets/css/TypeRoom.css";
import BookingModal from "../../components/Booking/BookingModal";
import { Link } from "react-router-dom";
const TypeRoom = () => {
  const dispatch = useDispatch();
  const { rooms } = useSelector((state) => state.room);
  const [visible, setVisible] = useState(false);
  const [selectedRoom, setSelectedRoom] = useState(null);
  const [roomImages, setRoomImages] = useState([]);

  useEffect(() => {
    dispatch(fetchRooms());
  }, [dispatch]);

  const roomData = [
    {
      name: "Couple Room",
      mainImage: COUPLE_1,
      images: [COUPLE_2, COUPLE_3, COUPLE_4, WCC],
    },
    {
      name: "Fontview Room",
      mainImage: VIEW1_F,
      images: [VIEW2_F, VIEW3_F, WC_F],
    },
    {
      name: "Business Room",
      mainImage: BUSS_1,
      images: [BUSS_2, BUSS_3, BUSS_4, WC],
    },
  ];

  const handleClick = (room) => {
    setSelectedRoom(room);
    setRoomImages(room.images);
    setVisible(true);
  };

  const handleCancel = () => {
    setVisible(false);
  };

  return (
    <div className="type-room-container">
      {roomData.map((room) => (
        <Card
          key={room.name}
          hoverable
          style={{ width: 240 }}
          cover={<Image src={room.mainImage} />}
          onClick={() => handleClick(room)}
        >
          <Card.Meta title={room.name} />
        </Card>
      ))}

      <Modal
        style={{ textAlign: "center" }}
        open={visible}
        title={selectedRoom?.name}
        footer={null}
        onCancel={handleCancel}
      >
        {roomImages.map((img, index) => (
          <Image key={index} src={img} style={{ marginBottom: "10px" }} />
        ))}
        <Button>
          <Link to="/booking">Đặt phòng ngay</Link>
        </Button>
      </Modal>
    </div>
  );
};

export default TypeRoom;
