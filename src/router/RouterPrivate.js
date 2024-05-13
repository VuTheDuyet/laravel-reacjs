import Dashbord from "../pages/backend/Dashbord";
import ProductCreate from "../pages/backend/Product/ProductCreate";
import ProductList from "../pages/backend/Product/ProductList";
import ProductUpdate from "../pages/backend/Product/ProductUpdate";

const RouterPrivate = [
    {path:'/admin', component:Dashbord},
    {path:'/admin/Product/Index', component:ProductList},
    {path:'/admin/product/create', component:ProductCreate},
    {path:'/admin/product-update/:id', component:ProductUpdate},
]
export default RouterPrivate;