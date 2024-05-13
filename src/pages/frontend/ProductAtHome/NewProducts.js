import React, { useEffect, useState } from 'react';
import { MdStar, MdStarBorder } from 'react-icons/md';
import { AiOutlineHeart, AiOutlineEye, AiOutlineReload, AiOutlineShoppingCart } from 'react-icons/ai';
import productservice from "../../../services/ProductService";

function NewProducts() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        productservice.GET_PRODUCTS_BY_TAG_ID('products', "e09fae3f-0916-42b3-94c0-a1e9951d79a0")
            .then(response => {
                setProducts(response.data);
            })
            .catch(error => {
                console.error('Error fetching products:', error);
            });
    }, []);

    return (
        <div className="product-main">
            <h2 className="title">New Products</h2>
            <div className="product-grid">
                {products.map(product => (
                    <div className="showcase" key={product.productId}>
                        <div  className="showcase-banner">
                            <img
                                src={require(`../../../assets/images/products/${product.productGalleries[0].imagePath}`)}
                                alt={product.productName}
                                width={300}
                                className="product-img default"
                            />
                            <img
                                src={require(`../../../assets/images/products/${product.productGalleries[0].placeholder.split(', ')[0]}`)}
                                alt={product.productName}
                                width={300}
                                className="product-img hover"
                            />
                            {product.productType === "15%" && <p className="showcase-badge">15%</p>}
                            {product.productType === "sale" && <p className="showcase-badge angle black">sale</p>}
                            {product.productType === "new" && <p className="showcase-badge angle pink">new</p>}
                            <div className="showcase-actions">
                                <button className="btn-action">
                                    <AiOutlineHeart />
                                </button>
                                <button className="btn-action">
                                    <AiOutlineEye />
                                </button>
                                <button className="btn-action">
                                    <AiOutlineReload />
                                </button>
                                <button className="btn-action">
                                    <AiOutlineShoppingCart />
                                </button>
                            </div>
                        </div>
                        <div href={`/detail-product/${product.productId}`} className="showcase-content">
                            <a href={`/detail-product/${product.productId}`} className="showcase-category">
                                {product.productCategories.map(category => category.category.categoryName).join(', ')}
                            </a>
                            <a href={`/detail-product/${product.productId}`}>
                                <h3 className="showcase-title">{product.productName}</h3>
                            </a>
                            <div className="showcase-rating">
                                <MdStar />
                                <MdStar />
                                <MdStar />
                                <MdStarBorder />
                                <MdStarBorder />
                            </div>
                            <div className="price-box">
                                <p className="price">${product.salePrice.toFixed(2)}</p>
                                <del>${product.comparePrice.toFixed(2)}</del>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default NewProducts;
