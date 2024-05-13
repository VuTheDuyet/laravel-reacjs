import Home from "../pages/frontend/Home";
import UserLogin from "../pages/frontend/Home/UserLogin";
import DetailProduct from "../pages/frontend/ProductAtHome/DetailProduct";

const RouterPublic = [
    { path: "/", component: Home },
    { path: "/trang-chu", component: Home },
    { path: "/detail-product/:id", component: DetailProduct },
    { path: "/login", component:  UserLogin},
]
export default RouterPublic;