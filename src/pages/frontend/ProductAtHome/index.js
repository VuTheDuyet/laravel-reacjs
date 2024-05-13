import { IoCloseOutline, IoAddOutline, IoRemoveOutline } from 'react-icons/io5';
import {
    AiOutlineHeart, AiOutlineEye,
    AiOutlineReload, AiOutlineShoppingCart
} from 'react-icons/ai';
import { MdStar, MdStarBorder, MdStarHalf } from 'react-icons/md';
import { useEffect, useRef, useState } from 'react';
import { useSidebar } from '../../../FatherContext/SidebarContext';
import { useOverlay } from '../../../FatherContext/OverlayContext';
import categoryservice from "../../../services/CategoryService";
import NewArrivals from './NewArrivals';
import Trending from './Trending';
import TopRated from './TopRated';
import DealOfTheDay from './DealOfTheDay';
import NewProducts from './NewProducts';
import BestSeller from './BestSeller';
function Product() {
    const { sidebarActive, setSidebarActive } = useSidebar();
    const { overlayActive, setOverlayActive } = useOverlay();
    const sidebarRef = useRef(null);
    const [expandedMenus, setExpandedMenus] = useState({});


    const toggleExpanded = (menu) => {
        setExpandedMenus((prevMenus) => {
            const updatedMenus = {};
            // Đảm bảo chỉ có một phần tử được mở ra mỗi lần
            Object.keys(prevMenus).forEach((key) => {
                updatedMenus[key] = key === menu ? !prevMenus[key] : false;
            });
            return updatedMenus;
        });
    };



    const handleCloseSidebar = () => {
        setSidebarActive(false);
        setOverlayActive(false);
    };

    useEffect(() => {
        const sidebarClickHandler = (event) => {
            if (sidebarActive && sidebarRef.current && !sidebarRef.current.contains(event.target)) {
                setSidebarActive(false);
                setOverlayActive(false);
            }
        };

        document.addEventListener('click', sidebarClickHandler);

        return () => {
            document.removeEventListener('click', sidebarClickHandler);
        };
    }, [sidebarActive, sidebarRef]);

    const [categories, setCategories] = useState([]);

    const normalizeName = (name) => {
        return name.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase();
    };

    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const response = await categoryservice.GET_ALL_CATEGORIES_WITHOUT_PARENT('categories/null-parent');
                const categoriesWithSubCategories = await Promise.all(
                    response.data.map(async (category) => {
                        const subResponse = await categoryservice.GET_CATEGORIES_BY_PARENT_ID('categories/parent', category.categoryId);
                        return {
                            ...category,
                            subCategories: subResponse.data
                        };
                    })
                );
                setCategories(categoriesWithSubCategories);
                const initialExpandedMenus = categoriesWithSubCategories.reduce((acc, category) => {
                    const normalizedName = normalizeName(category.categoryName);
                    return { ...acc, [normalizedName]: false };
                }, {});
                setExpandedMenus(initialExpandedMenus);
            } catch (error) {
                console.error('Error fetching categories:', error);
            }
        };

        fetchCategories();
    }, []);





    return (
        <>
            <div className={`overlay ${overlayActive ? 'active' : ''}`}></div>
            <div className="product-container">
                <div className="container">
                    {/*
    - SIDEBAR
  */}
                    <div ref={sidebarRef} className={`sidebar ${sidebarActive ? 'has-scrollbar active' : 'has-scrollbar'}`} data-mobile-menu="">
                        <div className="sidebar-category">
                            <div className="sidebar-top">
                                <h2 className="sidebar-title">Category</h2>
                                <button className="sidebar-close-btn" data-mobile-menu-close-btn="" onClick={handleCloseSidebar}>
                                    <IoCloseOutline />
                                </button>
                            </div>
                            <ul className="sidebar-menu-category-list">
                                {categories.map(category => (
                                    <li className="sidebar-menu-category" key={category.categoryId}>
                                        <button
                                            className={`sidebar-accordion-menu ${expandedMenus[normalizeName(category.categoryName)] ? 'active' : ''}`}
                                            data-accordion-btn=""
                                            onClick={() => toggleExpanded(normalizeName(category.categoryName), category.categoryId)}
                                        >
                                            <div className="menu-title-flex">
                                                <img
                                                    src={require(`../../../assets/images/icons-convert/${category.icon}`)}
                                                    alt = ''
                                                    width={20}
                                                    height={20}
                                                    className="menu-title-img"
                                                />
                                                <p className="menu-title">{category.categoryName}</p>
                                            </div>
                                            <div>
                                                <IoAddOutline className={`add-icon ${expandedMenus[normalizeName(category.categoryName)] ? 'hidden' : ''}`} />
                                                <IoRemoveOutline className={`remove-icon ${expandedMenus[normalizeName(category.categoryName)] ? '' : 'hidden'}`} />
                                            </div>
                                        </button>
                                        {expandedMenus[normalizeName(category.categoryName)] && (
                                            <ul className={`sidebar-submenu-category-list ${expandedMenus[normalizeName(category.categoryName)] ? 'active' : ''}`} data-accordion="">
                                                {category.subCategories.map(subCategory => (
                                                    <li className="sidebar-submenu-category" key={subCategory.categoryId}>
                                                        <a href="#" className="sidebar-submenu-title">
                                                            <p className="product-name">{subCategory.categoryName}</p>
                                                            
                                                        </a>
                                                    </li>
                                                ))}
                                            </ul>
                                        )}
                                    </li>
                                ))}
                            </ul>
                        </div>
                        <BestSeller/>
                    </div>
                    <div className="product-box">
                        {/*
      - PRODUCT MINIMAL
    */}
                        <div className="product-minimal">
                            <NewArrivals/>
                            <Trending/>
                            <TopRated/>
                        </div>
                        {/*
      - PRODUCT FEATURED
    */}
                        <DealOfTheDay/>
                        {/*
      - PRODUCT GRID
    */}
                        <NewProducts/>
                    </div>
                </div>
            </div>

        </>
    );
}

export default Product;