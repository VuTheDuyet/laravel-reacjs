import React, { Component, useState } from 'react';
import { Link } from 'react-router-dom';
import {
    IoSearchOutline, IoPersonOutline, IoHeartOutline,
    IoBagHandleOutline, IoLogoFacebook, IoLogoTwitter,
    IoLogoInstagram, IoLogoLinkedin
} from 'react-icons/io5';
import logo from '../../assets/images/logo/logo.svg';
import MenuMobile from './MenuMobile';

export default function Header() {
    const [userId, setUserId] = useState(localStorage.getItem('userId'));
    // Function to handle logout
    const handleLogout = () => {
        setUserId(null); // Đặt lại userId về null để đăng xuất
        localStorage.setItem('userId', "");
    };
    const gotoadmin = () => {
        window.location.href = "/admin";
    };
    return (
        <div>
            <header>
                <div className="header-top">
                    <div className="container">
                        <ul className="header-social-container">
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
                        <div className="header-alert-news">
                            <p>
                                <b>Free Shipping</b>
                                This Week Order Over - $55
                            </p>
                        </div>
                        <div className="header-top-actions">
                            <select name="currency">
                                <option value="usd">USD $</option>
                                <option value="eur">EUR €</option>
                            </select>
                            <select name="language">
                                <option value="en-US">English</option>
                                <option value="es-ES">Español</option>
                                <option value="fr">Français</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div className="header-main">
                    <div className="container">
                        <a href="#" className="header-logo">
                            <img
                                src={logo}
                                alt="Anon's logo"
                                width={120}
                                height={36}
                            />
                        </a>
                        <div className="header-search-container">
                            <input
                                type="search"
                                name="search"
                                className="search-field"
                                placeholder="Enter your product name..."
                            />
                            <button className="search-btn">
                                <IoSearchOutline />
                            </button>
                        </div>
                        <div className="header-user-actions">
                            <Link to={userId ? "#" : "/login"} onClick={userId ? handleLogout : null}>
                                <a href="#" className="widget-view">
                                    <div className="icon-area">
                                        <IoPersonOutline />
                                        <span className="notify"></span>
                                    </div>
                                    <small className="text">{userId ? `${userId}` : 'Login'}</small>
                                </a>
                            </Link>
                            <button className="action-btn">
                                <IoHeartOutline />
                                <span className="count">0</span>
                            </button>

                            <Link to={userId ? "#" : "/login"} onClick={userId ? gotoadmin : null}>
                                <a href="#" className="widget-view">
                                    <div className="icon-area">
                                        <IoBagHandleOutline />
                                        <span className="notify"></span>
                                    </div>
                                    <small className="text">{userId ? `Admin` : 'Shop'}</small>
                                </a>
                            </Link>

                        </div>
                    </div>
                </div>
                <nav className="desktop-navigation-menu">
                    <div className="container">
                        <ul className="desktop-menu-category-list">
                            <li className="menu-category">
                                <a href="#" className="menu-title">
                                    Home
                                </a>
                            </li>
                            <li className="menu-category">
                                <a href="#" className="menu-title">
                                    Categories
                                </a>
                                <div className="dropdown-panel">
                                    <ul className="dropdown-panel-list">
                                        <li className="menu-title">
                                            <a href="#">Electronics</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Desktop</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Laptop</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Camera</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Tablet</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Headphone</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">
                                                <img
                                                    src={require("../../assets/images/electronics-banner-1.jpg")}
                                                    alt="headphone collection"
                                                    width={250}
                                                    height={119}
                                                />
                                            </a>
                                        </li>
                                    </ul>
                                    <ul className="dropdown-panel-list">
                                        <li className="menu-title">
                                            <a href="#">Men's</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Formal</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Casual</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Sports</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Jacket</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Sunglasses</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">
                                                <img
                                                    src={require("../../assets/images/mens-banner.jpg")}
                                                    alt="men's fashion"
                                                    width={250}
                                                    height={119}
                                                />
                                            </a>
                                        </li>
                                    </ul>
                                    <ul className="dropdown-panel-list">
                                        <li className="menu-title">
                                            <a href="#">Women's</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Formal</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Casual</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Perfume</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Cosmetics</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Bags</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">
                                                <img
                                                    src={require("../../assets/images/womens-banner.jpg")}
                                                    alt="women's fashion"
                                                    width={250}
                                                    height={119}
                                                />
                                            </a>
                                        </li>
                                    </ul>
                                    <ul className="dropdown-panel-list">
                                        <li className="menu-title">
                                            <a href="#">Electronics</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Smart Watch</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Smart TV</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Keyboard</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Mouse</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">Microphone</a>
                                        </li>
                                        <li className="panel-list-item">
                                            <a href="#">
                                                <img
                                                    src={require("../../assets/images/electronics-banner-2.jpg")}
                                                    alt="mouse collection"
                                                    width={250}
                                                    height={119}
                                                />
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li className="menu-category">
                                <a href="#" className="menu-title">
                                    Men's
                                </a>
                                <ul className="dropdown-list">
                                    <li className="dropdown-item">
                                        <a href="#">Shirt</a>
                                    </li>
                                    <li className="dropdown-item">
                                        <a href="#">Shorts &amp; Jeans</a>
                                    </li>
                                    <li className="dropdown-item">
                                        <a href="#">Safety Shoes</a>
                                    </li>
                                    <li className="dropdown-item">
                                        <a href="#">Wallet</a>
                                    </li>
                                </ul>
                            </li>
                            <li className="menu-category">
                                <a href="#" className="menu-title">
                                    Women's
                                </a>
                                <ul className="dropdown-list">
                                    <li className="dropdown-item">
                                        <a href="#">Dress &amp; Frock</a>
                                    </li>
                                    <li className="dropdown-item">
                                        <a href="#">Earrings</a>
                                    </li>
                                    <li className="dropdown-item">
                                        <a href="#">Necklace</a>
                                    </li>
                                    <li className="dropdown-item">
                                        <a href="#">Makeup Kit</a>
                                    </li>
                                </ul>
                            </li>
                            <li className="menu-category">
                                <a href="#" className="menu-title">
                                    Jewelry
                                </a>
                                <ul className="dropdown-list">
                                    <li className="dropdown-item">
                                        <a href="#">Earrings</a>
                                    </li>
                                    <li className="dropdown-item">
                                        <a href="#">Couple Rings</a>
                                    </li>
                                    <li className="dropdown-item">
                                        <a href="#">Necklace</a>
                                    </li>
                                    <li className="dropdown-item">
                                        <a href="#">Bracelets</a>
                                    </li>
                                </ul>
                            </li>
                            <li className="menu-category">
                                <a href="#" className="menu-title">
                                    Perfume
                                </a>
                                <ul className="dropdown-list">
                                    <li className="dropdown-item">
                                        <a href="#">Clothes Perfume</a>
                                    </li>
                                    <li className="dropdown-item">
                                        <a href="#">Deodorant</a>
                                    </li>
                                    <li className="dropdown-item">
                                        <a href="#">Flower Fragrance</a>
                                    </li>
                                    <li className="dropdown-item">
                                        <a href="#">Air Freshener</a>
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
                    </div>
                </nav>
                <MenuMobile />
            </header>
        </div>
    )
}
