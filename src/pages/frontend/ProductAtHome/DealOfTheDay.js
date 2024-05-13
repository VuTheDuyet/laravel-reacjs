import React, { useState, useEffect } from 'react';
import { MdStar, MdStarBorder } from 'react-icons/md';
import productservice from "../../../services/ProductService";

function DealOfTheDay() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        productservice.GET_PRODUCTS_BY_TAG_ID('products', "b377823e-34cc-467b-a287-5a9eb1001089")
            .then(response => {
                setProducts(response.data);
            })
            .catch(error => {
                console.error('Error fetching products:', error);
            });
    }, []);

    return (
        <>
            <div className="product-featured">
                <h2 className="title">Deal of the day</h2>
                <div className="showcase-wrapper has-scrollbar">
                    {products.map((product, index) => (
                        <div className="showcase-container" key={index}>
                            <div className="showcase" key={product.productId}>
                                <div className="showcase-banner">
                                    <img
                                        src={require(`../../../assets/images/products/${product.productGalleries[0].imagePath}`)}
                                        alt={product.productName}
                                        className="showcase-img"
                                    />
                                </div>
                                <div className="showcase-content">
                                    <div className="showcase-rating">
                                        <MdStar />
                                        <MdStar />
                                        <MdStar />
                                        <MdStarBorder />
                                        <MdStarBorder />
                                    </div>
                                    <a href="#">
                                        <h3 className="showcase-title">{product.productName}</h3>
                                    </a>
                                    <p className="showcase-desc">{product.shortDescription}</p>
                                    <div className="price-box">
                                        <p className="price">${product.salePrice.toFixed(2)}</p>
                                        <del>${product.comparePrice.toFixed(2)}</del>
                                    </div>
                                    <button className="add-cart-btn">add to cart</button>
                                    <div className="showcase-status">
                                        <div className="wrapper">
                                            <p>
                                                already sold: <b>20</b>
                                            </p>
                                            <p>
                                                available: <b>40</b>
                                            </p>
                                        </div>
                                        <div className="showcase-status-bar" />
                                    </div>
                                    <div className="countdown-box">
                                        <p className="countdown-desc">Hurry Up! Offer ends in:</p>
                                        <div className="countdown">
                                            <div className="countdown-content">
                                                <p className="display-number">360</p>
                                                <p className="display-text">Days</p>
                                            </div>
                                            <div className="countdown-content">
                                                <p className="display-number">24</p>
                                                <p className="display-text">Hours</p>
                                            </div>
                                            <div className="countdown-content">
                                                <p className="display-number">59</p>
                                                <p className="display-text">Min</p>
                                            </div>
                                            <div className="countdown-content">
                                                <p className="display-number">00</p>
                                                <p className="display-text">Sec</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </>
    );
}

export default DealOfTheDay;
