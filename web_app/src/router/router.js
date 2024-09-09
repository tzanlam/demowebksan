import { createBrowserRouter } from "react-router-dom";
import HomePage from "../pages/Header/HomePage";
import TypeRoom from "../pages/Header/TypeRoom";
import Booking from "../pages/Header/Booking";
import MoreServices from "../pages/Header/MoreServices";
import Sales from "../pages/Header/Sales";
import Resgister from "../pages/Header/Resgister";
import Login from "../pages/Header/Login";
import SecurityPolicy from "../pages/Footer/SecurityPolicy";
import SupportPolicy from "../pages/Footer/SupportPolicy";
import RefuseService from "../pages/Footer/RefuseService";
import MainLayout from "../components/Layout/MainLayout";
const router = createBrowserRouter([
    {
        path: "/",
        element: <MainLayout />,
        children: [
            {index: true, element: <HomePage/>},
            {path: "/type-room", element: <TypeRoom/>},
            {path: "/booking", element:<Booking/>},
            {path: "/more-service", element: <MoreServices/>},
            {path: "/sales", element: <Sales/>},
            {path: "/resgister", element: <Resgister/>},
            {path: "/login", element: <Login/>},
            {path: "/security-policy", element: <SecurityPolicy/>},
            {path: "/support-policy", element: <SupportPolicy/>},
            {path: "/refuse-service", element: <RefuseService/>}
        ]
    }
])

export default router;