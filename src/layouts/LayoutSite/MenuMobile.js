import { useEffect, useRef, useState } from 'react';
import {
    IoHeartOutline,
    IoBagHandleOutline, IoLogoFacebook, IoLogoTwitter,
    IoLogoInstagram, IoLogoLinkedin, IoMenuOutline, IoGridOutline,
    IoCloseOutline, IoAddOutline, IoRemoveOutline, IoCaretBackOutline, IoHomeOutline
} from 'react-icons/io5';
import { useSidebar } from '../../FatherContext/SidebarContext';
import { useOverlay } from '../../FatherContext/OverlayContext';
function MenuMobile() {
    const [menuOpen, setMenuOpen] = useState(false);
    const {overlayActive, setOverlayActive} = useOverlay();
    const menuRef = useRef(null);
    const { sidebarActive, setSidebarActive } = useSidebar();

    // Hàm xử lý sự kiện click đóng và để mở sidebar
    const handleSidebarToggle = (e) => {
        e.stopPropagation();
        setSidebarActive((prevSidbarOpen) => {
            const newSidebarOpen = !prevSidbarOpen;
            setOverlayActive(newSidebarOpen);
            return newSidebarOpen;
        });
    };

    

    

    // Hàm xử lý sự kiện click để đóng menu
    const handleCloseMenu = () => {
        setMenuOpen(false);
        setOverlayActive(false);
    };


    // Sử dụng useEffect để thêm sự kiện click vào document
    useEffect(() => {
        const menuClickHandler = (event) => {
            if (menuOpen && menuRef.current && !menuRef.current.contains(event.target)) {
                setMenuOpen(false);
                setOverlayActive(false);
            }
        };
    
        document.addEventListener('click', menuClickHandler);
    
        return () => {
            document.removeEventListener('click', menuClickHandler);
        };
    }, [menuOpen, menuRef]);
    
   

    const [expandedMenus, setExpandedMenus] = useState({
        men: false,
        women: false,
        jewelry: false,
        perfume: false,
        language: false,
        currency: false,
    });

    const toggleExpanded = (menu) => {
        setExpandedMenus((prevMenus) => {
            const updatedMenus = { ...prevMenus };
            Object.keys(updatedMenus).forEach((key) => {
                updatedMenus[key] = key === menu ? !prevMenus[key] : false;
            });
            return updatedMenus;
        });
    };
    
    const handleMenuToggle = (e, menu) => {
        e.stopPropagation();
        // Ngăn chặn sự kiện click lan truyền lên
        setMenuOpen((prevMenuOpen) => {
            // Invert the previous value of menuOpen
            const newMenuOpen = !prevMenuOpen;
            // Update overlayActive based on the new value of menuOpen
            setOverlayActive(newMenuOpen);
            // Close all menus except the clicked one
            toggleExpanded(menu);
            // Return the new value of menuOpen
            return newMenuOpen;
        });
    };
    
    return (
        <>
            <div className={`overlay ${overlayActive ? 'active' : ''}`}></div>
            <div className="mobile-bottom-navigation">
                <button className="action-btn" data-mobile-menu-open-btn="" onClick={handleMenuToggle}>
                    {menuOpen ? <IoMenuOutline /> : <IoMenuOutline />}
                </button>
                <button className="action-btn">
                    <IoBagHandleOutline />
                    <span className="count">0</span>
                </button>
                <button className="action-btn">
                    <IoHomeOutline />
                </button>
                <button className="action-btn">
                    <IoHeartOutline />
                    <span className="count">0</span>
                </button>
                <button className="action-btn" data-mobile-menu-open-btn="" onClick={handleSidebarToggle}>
                    {sidebarActive ? <IoGridOutline /> : <IoGridOutline />}
                </button>
            </div>
            <nav ref={menuRef} className={`mobile-navigation-menu ${menuOpen ? 'has-scrollbar active' : 'has-scrollbar'}`} data-mobile-menu="">
                <div className="menu-top">
                    <h2 className="menu-title">Menu</h2>
                    <button className="menu-close-btn" onClick={handleCloseMenu}>
                        <IoCloseOutline />
                    </button>
                </div>
                <ul className="mobile-menu-category-list">
                    <li className="menu-category">
                        <a href="#" className="menu-title">
                            Home
                        </a>
                    </li>
                    <li className="menu-category">
                        <button className={`accordion-menu ${expandedMenus.men ? 'active' : ''}`} data-accordion-btn="" onClick={() => toggleExpanded('men')}>
                            <p className="menu-title">Men's</p>
                            <div>
                                <IoAddOutline className={`add-icon ${expandedMenus.men ? 'hidden' : ''}`} />
                                <IoRemoveOutline className={`remove-icon ${expandedMenus.men ? '' : 'hidden'}`} />
                            </div>
                        </button>
                        <ul className={`submenu-category-list ${expandedMenus.men ? 'active' : ''}`} data-accordion="">
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Shirt
                                </a>
                            </li>
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Shorts &amp; Jeans
                                </a>
                            </li>
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Safety Shoes
                                </a>
                            </li>
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Wallet
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li className="menu-category">
                        <button className={`accordion-menu ${expandedMenus.women ? 'active' : ''}`} data-accordion-btn="" onClick={() => toggleExpanded('women')}>
                            <p className="menu-title">Women's</p>
                            <div>
                                <IoAddOutline className={`add-icon ${expandedMenus.women ? 'hidden' : ''}`} />
                                <IoRemoveOutline className={`remove-icon ${expandedMenus.women ? '' : 'hidden'}`} />
                            </div>
                        </button>
                        <ul className={`submenu-category-list ${expandedMenus.women ? 'active' : ''}`} data-accordion="">
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Dress &amp; Frock
                                </a>
                            </li>
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Earrings
                                </a>
                            </li>
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Necklace
                                </a>
                            </li>
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Makeup Kit
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li className="menu-category">
                        <button className={`accordion-menu ${expandedMenus.jewelry ? 'active' : ''}`} data-accordion-btn="" onClick={() => toggleExpanded('jewelry')}>
                            <p className="menu-title">Jewelry</p>
                            <div>
                                <IoAddOutline className={`add-icon ${expandedMenus.jewelry ? 'hidden' : ''}`} />
                                <IoRemoveOutline className={`remove-icon ${expandedMenus.jewelry ? '' : 'hidden'}`} />
                            </div>
                        </button>
                        <ul className={`submenu-category-list ${expandedMenus.jewelry ? 'active' : ''}`} data-accordion="">
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Earrings
                                </a>
                            </li>
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Couple Rings
                                </a>
                            </li>
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Necklace
                                </a>
                            </li>
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Bracelets
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li className="menu-category">
                        <button className={`accordion-menu ${expandedMenus.perfume ? 'active' : ''}`} data-accordion-btn="" onClick={() => toggleExpanded('perfume')}>
                            <p className="menu-title">Perfume</p>
                            <div>
                                <IoAddOutline className={`add-icon ${expandedMenus.perfume ? 'hidden' : ''}`} />
                                <IoRemoveOutline className={`remove-icon ${expandedMenus.perfume ? '' : 'hidden'}`} />
                            </div>
                        </button>
                        <ul className={`submenu-category-list ${expandedMenus.perfume ? 'active' : ''}`} data-accordion="">
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Clothes Perfume
                                </a>
                            </li>
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Deodorant
                                </a>
                            </li>
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Flower Fragrance
                                </a>
                            </li>
                            <li className="submenu-category">
                                <a href="#" className="submenu-title">
                                    Air Freshener
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li className="menu-category">
                        <a href="#" className="menu-title">
                            Blog
                        </a>
                    </li>
                    <li className="menu-category">
                        <a href="#" className="menu-title">
                            Hot Offers
                        </a>
                    </li>
                </ul>
                <div className="menu-bottom">
                    <ul className="menu-category-list">
                        <li className="menu-category">
                            <button className={`accordion-menu ${expandedMenus.language ? 'active' : ''}`} data-accordion-btn="" onClick={() => toggleExpanded('language')}>
                                <p className="menu-title">Language</p>
                                <IoCaretBackOutline className={`caret-back ${expandedMenus.language ? 'remove-icon' : 'add-icon'}`} />

                            </button>
                            <ul className={`submenu-category-list ${expandedMenus.language ? 'active' : ''}`} data-accordion="">
                                <li className="submenu-category">
                                    <a href="#" className="submenu-title">
                                        English
                                    </a>
                                </li>
                                <li className="submenu-category">
                                    <a href="#" className="submenu-title">
                                        Español
                                    </a>
                                </li>
                                <li className="submenu-category">
                                    <a href="#" className="submenu-title">
                                        Frençh
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li className="menu-category">
                            <button className={`accordion-menu ${expandedMenus.currency ? 'active' : ''}`} data-accordion-btn="" onClick={() => toggleExpanded('currency')}>
                                <p className="menu-title">Currency</p>
                                <IoCaretBackOutline className={`caret-back ${expandedMenus.currency ? 'remove-icon' : 'add-icon'}`} />
                            </button>
                            <ul className={`submenu-category-list ${expandedMenus.currency ? 'active' : ''}`} data-accordion="">
                                <li className="submenu-category">
                                    <a href="#" className="submenu-title">
                                        USD $
                                    </a>
                                </li>
                                <li className="submenu-category">
                                    <a href="#" className="submenu-title">
                                        EUR €
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <ul className="menu-social-container">
                        <li>
                            <a href="#" className="social-link">
                                <IoLogoFacebook />
                            </a>
                        </li>
                        <li>
                            <a href="#" className="social-link">
                                <IoLogoTwitter />
                            </a>
                        </li>
                        <li>
                            <a href="#" className="social-link">
                                <IoLogoInstagram />
                            </a>
                        </li>
                        <li>
                            <a href="#" className="social-link">
                                <IoLogoLinkedin />
                            </a>
                        </li>
                    </ul>
                </div>

            </nav>

        </>
    );
}

export default MenuMobile;