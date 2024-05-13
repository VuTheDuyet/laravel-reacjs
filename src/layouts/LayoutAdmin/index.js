
import { Link, Outlet } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import { FaClone, FaProductHunt, } from 'react-icons/fa';
import './LayoutAdminStyle.css';
function LayoutAdmin() {
    function handleItemClick(item) {
        const hdlitem = document.getElementById(item);
        hdlitem.classList.toggle("active");
     }  
    return (
        <>
        <section className="hdl-header sticky-top">
           <div className="container-fluid">
              <ul className="menutop">
                 <li>
                    <a href="#nqt">
                       <FaClone />
                       {/* <i className="fa-brands fa-dashcube"></i>  */}
                       Vũ Thế Duyệt
                    </a>
                 </li>
                 <li className="text-phai">
                    <a href="/admin">

                       {/* <i className="fa-solid fa-power-off"></i>  */}
                       Thoát
                    </a>
                 </li>
                 <li className="text-phai">
                    <a href="#nqt">

                       {/* <i className="fa fa-user" aria-hidden="true"></i> */}
                       Chào quản lý
                    </a>
                 </li>
                 <li className="text-phai">
                    <a href="/">

                       {/* <i className="fa-solid fa-power-off"></i>  */}
                       Về tramg chủ
                    </a>
                 </li>
              </ul>
           </div>
        </section>
        <section className="hdl-content">
           <div className="container-fluid">
              <div className="row">
                 <div className="col-md-2 bg-dark p-0 hdl-left">
                    <div className="hdl-left">
                       <div className="dashboard-name">
                          Bản điều khiển
                       </div>
                       <nav className="m-2 mainmenu">
                          <ul className="main">
                             <li className="hdlitem item-sub" id="item1" onClick={() => handleItemClick('item1')}>
                                <FaProductHunt className="icon-left" />
                                <a href="#nt">Sản phẩm</a>
                                <i className="fa-solid fa-plus icon-right"></i>
                                <ul className="submenu">
                                   <li>
                                      <Link to="/admin/Product/Index" className="margin-left-submenu">Tất cả sản phẩm</Link>
                                   </li>
                                   <li>
                                      <a href="/admin/Product/Import" className="margin-left-submenu">Nhập hàng</a>
                                   </li>
                                   <li>
                                      <a href="/admin/category/index" className="margin-left-submenu">Danh mục</a>
                                   </li>
                                   <li>
                                      <Link to="/admin/brand/index" className="margin-left-submenu">Thương hiệu</Link>
                                   </li>
                                   <li>
                                      <a href="/admin/Product/Sale" className="margin-left-submenu">Khuyễn mãi</a>
                                   </li>
                                </ul>
                             </li>
                             <li className="hdlitem item-sub" id="item2" onClick={() => handleItemClick('item2')}>
                                <FaProductHunt className="icon-left" />
                                <a href="#nt">Bài viết</a>
                                <i className="fa-solid fa-plus icon-right"></i>
                                <ul className="submenu">
                                   <li>
                                      <Link to="/admin/post/Index" className="margin-left-submenu">Tất cả bài viết</Link>
                                   </li>
                                   <li>
                                      <a href="/admin/topic/Index" className="margin-left-submenu">Chủ đề</a>
                                   </li>
                                   <li>
                                      <a href="/admin/page/Index" className="margin-left-submenu">Trang đơn</a>
                                   </li>
                                </ul>
                             </li>
                             <li className="hdlitem item-sub" id="item3" onClick={() => handleItemClick('item3')}>
                                <FaProductHunt className="icon-left" />
                                <a href="#nt">Quản lý bán hàng</a>
                                <i className="fa-solid fa-plus icon-right"></i>
                                <ul className="submenu">
                                   <li>
                                      <Link to="/admin/order/Index" className="margin-left-submenu">Tất cả đơn hàng</Link>
                                   </li>
                                   <li>
                                      <a href="/admin/order/export" className="margin-left-submenu">Xuất hàng</a>
                                   </li>
                                </ul>
                             </li>
                             <li className="hdlitem">
                                <a className="icon-left" />
                                {/* <i className="fa-regular fa-circle icon-left"></i> */}
                                <a href="/admin/customer/Index">Khách hàng</a>
                             </li>
                             <li className="hdlitem">
                                <a className="icon-left" />
                                {/* <i className="fa-regular fa-circle icon-left"></i> */}
                                <a href="/admin/contact/Index">Liên hệ</a>
                             </li>
                             <li className="hdlitem item-sub" id="item4" onClick={() => handleItemClick('item4')}>
                                <FaProductHunt className="icon-left" />
                                <a href="#nt">Giao diện</a>
                                <i className="fa-solid fa-plus icon-right"></i>
                                <ul className="submenu">
                                   <li>
                                      <Link to="/admin/menu/Index" className="margin-left-submenu">Menu</Link>
                                   </li>
                                   <li>
                                      <a href="/admin/banner/Index" className="margin-left-submenu">Banner</a>
                                   </li>
                                </ul>
                             </li>
                             <li className="hdlitem item-sub" id="item5" onClick={() => handleItemClick('item5')}>
                                <FaProductHunt className="icon-left" />
                                <a href="#nt">Hệ thống</a>
                                <i className="fa-solid fa-plus icon-right"></i>
                                <ul className="submenu">
                                   <li>
                                      <Link to="/admin/user/Index" className="margin-left-submenu">Thành viên</Link>
                                   </li>
                                   <li>
                                      <a href="/admin/config/Index" className="margin-left-submenu">Cấu hình</a>
                                   </li>
                                </ul>
                             </li>
                          </ul>
                       </nav>
                    </div>
                 </div>
                 <div className="col-md-10">
                    <div className="content">
                       <Outlet />
                    </div>
                 </div>
              </div>
           </div>
        </section>

     </>
    );

}

export default LayoutAdmin;