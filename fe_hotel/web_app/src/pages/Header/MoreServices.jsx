import { Image, Layout} from "antd";
import React from "react";
import sevenup from "../../assets/minibar/sevenup.jpg";
import auqa from "../../assets/minibar/auqa.jpg";
import coca from "../../assets/minibar/coca.jpg";
import dasani from "../../assets/minibar/dasani.jpg";
import fuzetea from "../../assets/minibar/fuzetea.webp";
import nutriboost from "../../assets/minibar/nutriboost.png";
import olong from "../../assets/minibar/olong.jpg";
import pepsi from "../../assets/minibar/pepsi.jpg";
import redbull from "../../assets/minibar/redbull.png";
import sting from "../../assets/minibar/sting.webp";
import twister from "../../assets/minibar/twister.jpg";

const MoreServices = () => {
  return (
    <Layout className="more_service" style={{ padding: "20px" }}>
        <h3>MINI BAR</h3>
      <div
        style={{
          display: "flex",
          flexWrap: "wrap",
          justifyContent: "space-around",
          alignItems: "center",
          gap: "20px",
        }}
      >
        <div style={{ textAlign: "center" }}>
          <Image
            height={170}
            width={170}
            src={sevenup}
            preview={false}
            style={{ objectFit: "cover" }}
          />
          <p>Seven Up</p>
        </div>
        <div style={{ textAlign: "center" }}>
          <Image
            height={170}
            width={170}
            src={auqa}
            preview={false}
            style={{ objectFit: "cover" }}
          />
          <p>Auqa</p>
        </div>
        <div style={{ textAlign: "center" }}>
          <Image
            height={170}
            width={170}
            src={coca}
            preview={false}
            style={{ objectFit: "cover" }}
          />
          <p>Coca Cola</p>
        </div>
        <div style={{ textAlign: "center" }}>
          <Image
            height={170}
            width={170}
            src={dasani}
            preview={false}
            style={{ objectFit: "cover" }}
          />
          <p>Dasani</p>
        </div>
        <div style={{ textAlign: "center" }}>
          <Image
            height={170}
            width={170}
            src={fuzetea}
            preview={false}
            style={{ objectFit: "cover" }}
          />
          <p>Fuze Tea</p>
        </div>
        <div style={{ textAlign: "center" }}>
          <Image
            height={170}
            width={170}
            src={nutriboost}
            preview={false}
            style={{ objectFit: "cover" }}
          />
          <p>Nutriboost</p>
        </div>
        <div style={{ textAlign: "center" }}>
          <Image
            height={170}
            width={170}
            src={olong}
            preview={false}
            style={{ objectFit: "cover" }}
          />
          <p>Trà Olong</p>
        </div>
        <div style={{ textAlign: "center" }}>
          <Image
            height={170}
            width={170}
            src={pepsi}
            preview={false}
            style={{ objectFit: "cover" }}
          />
          <p>Pepsi</p>
        </div>
        <div style={{ textAlign: "center" }}>
          <Image
            height={170}
            width={170}
            src={redbull}
            preview={false}
            style={{ objectFit: "cover" }}
          />
          <p>Redbull</p>
        </div>
        <div style={{ textAlign: "center" }}>
          <Image
            height={170}
            width={170}
            src={sting}
            preview={false}
            style={{ objectFit: "cover" }}
          />
          <p>Sting</p>
        </div>
        <div style={{ textAlign: "center" }}>
          <Image
            height={170}
            width={170}
            src={twister}
            preview={false}
            style={{ objectFit: "cover" }}
          />
          <p>Twister</p>
        </div>
      </div>
      <h3>Khác</h3>
      <li>Thuê xe</li>
      <li>Dọn dẹp phòng</li>
    </Layout>
  );
};

export default MoreServices;
