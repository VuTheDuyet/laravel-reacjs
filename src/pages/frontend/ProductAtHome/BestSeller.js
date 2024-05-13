import React, { useEffect, useState } from 'react';
import { MdStar, MdStarBorder, MdStarHalf } from 'react-icons/md';
import productservice from "../../../services/ProductService";// Thay thế 'api' bằng đường dẫn tới module API thực tế

function BestSeller() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        productservice.GET_PRODUCTS_BY_TAG_ID('products', "42795dd0-2bf0-437c-b8f5-ba0a6f20b9f4")
            .then(response => {
                setProducts(response.data);
            })
            .catch(error => {
                console.error('Error fetching products:', error);
            });
    }, []);

    return (
        <div className="product-showcase">
            <h3 className="showcase-heading">best sellers</h3>
            <div className="showcase-wrapper">
                <div className="showcase-container">
                    {products.map(product => (
                        <div className="showcase" key={product.productId}>
                            <a href="#" className="showcase-img-box">
                                <img
                                    src={require(`../../../assets/images/products/${product.productGalleries[0].imagePath}`)} // Sử dụng product.productId để thay đổi đường dẫn hình ảnh
                                    alt={product.productName}
                                    width={75}
                                    height={75}
                                    className="showcase-img"
                                />
                            </a>
                            <div className="showcase-content">
                                <a href={`/detail-product/${product.productId}`}>
                                    <h4 className="showcase-title">{product.productName}</h4>
                                </a>
                                <div className="showcase-rating">
                                    <MdStar />
                                    <MdStar />
                                    <MdStar />
                                    <MdStar />
                                    <MdStarHalf />
                                </div>
                                <div className="price-box">
                                    <del>${product.comparePrice.toFixed(2)}</del>
                                    <p className="price">${product.salePrice.toFixed(2)}</p>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default BestSeller;
