import React, { useEffect, useState } from 'react'
import { makeStyles } from '@mui/styles';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Alert from '@mui/material/Alert';
import { useNavigate, useParams } from 'react-router-dom';
import productservice from '../../../services/ProductService';
import staffAccountsService from '../../../services/StaffAccountService';
import tagservice from '../../../services/TagService';
import categoryservice from '../../../services/CategoryService';
import galleryservice from '../../../services/GalleryService';
import { Chip } from '@mui/material';
import axios from "axios";


export default function DetailProduct() {



    const { id } = useParams();
    const [idProduct, setIdProduct] = useState(0);
    const [checkAdd, setCheckAdd] = useState(false);
    const [productName, setProductName] = useState(null)
    const [slug, setSlug] = useState(null)
    const [sku, setSku] = useState(null)
    const [salePrice, setSalePrice] = useState(null)
    const [comparePrice, setComparePrice] = useState(null)
    const [buyingPrice, setBuyingPrice] = useState(null)
    const [quantity, setQuantity] = useState(null)
    const [shortDescription, setShortDescription] = useState(null)
    const [productDescription, setProductDescription] = useState(null)
    const [note, setNote] = useState(null)
    const [productType, setProductType] = useState(null)
    const [disableOutOfStock, setDisableOutOfStock] = useState(false)
    const [published, setPublished] = useState(null)
    const [createdBy, setCreatedBy] = useState("no-one");
    const [updatedBy, setUpdatedBy] = useState("no-one");
    const [isThumbnail, setIsThumbnail] = useState(null)
    const [imagePath, setImagePath] = useState("");
    const [placeholder, setPlaceholder] = useState("");
    const [staffAccounts, setStaffAccounts] = useState([]);
    const [tags, setTags] = useState([]);
    const [categoryId, setCategoryId] = useState("no-category");
    const [categories, setCategories] = useState([]);
    const [selectedTags, setSelectedTags] = useState([]);
    const [initialTags, setInitialTags] = useState([]);
    const [tagsToDelete, setTagsToDelete] = useState([]);
    const [tagsToAdd, setTagsToAdd] = useState([]);

    const [allImg, setAllImg] = useState("");
    const [showImg, setShowImg] = useState("");

    const [hoveredImage, setHoveredImage] = useState(imagePath);

    const userId = localStorage.getItem('userId');


    useEffect(() => {
        productservice.GET_PRODUCT_ID(`products`, id).then(product => { // Sử dụng id từ useParams
            setIdProduct(product.data.productId)
            setProductName(product.data.productName);
            setSlug(product.data.slug);
            setSku(product.data.sku);
            setSalePrice(product.data.salePrice);
            setComparePrice(product.data.comparePrice);
            setBuyingPrice(product.data.buyingPrice);
            setQuantity(product.data.quantity);
            setShortDescription(product.data.shortDescription);
            setProductDescription(product.data.productDescription);
            setNote(product.data.note);
            setProductType(product.data.productType);
            setDisableOutOfStock(product.data.disableOutOfStock);
            setPublished(product.data.published);
            setCreatedBy(product.data.createdBy.staffAccountId);
            setUpdatedBy(product.data.updatedBy.staffAccountId);
            setIsThumbnail(product.data.productGalleries[0].thumbnail);
            setImagePath(product.data.productGalleries[0].imagePath);
            setShowImg(product.data.productGalleries[0].imagePath);
            setPlaceholder(product.data.productGalleries[0].placeholder);
            setCategoryId(product.data.productCategories[0].category.categoryId);
            let fileNames = product.data.productGalleries[0].imagePath;

            setAllImg(prev => prev ? product.data.productGalleries[0].placeholder + ", " + fileNames : fileNames);
        });
        staffAccountsService.GET_ALL_STAFFACCOUNTS(`staffAccounts`).then(response => {
            if (response && response.data) {
                setStaffAccounts(response.data);
            }
        }).catch(error => {
            console.error("Error fetching staff accounts:", error);
        });
        tagservice.GET_ALL_TAGS(`tags`).then(response => {
            if (response && response.data) {
                setTags(response.data);
            }
        }).catch(error => {
            console.error("Error fetching tags:", error);
        });
        categoryservice.GET_ALL_CATEGORIES(`categories`).then(response => {
            if (response && response.data) {
                setCategories(response.data);
            }
        }).catch(error => {
            console.error("Error fetching categories:", error);
        });

        // Lấy danh sách các tag đã chọn ban đầu cho sản phẩm
        productservice.GET_PRODUCT_TAGS_BY_PRODUCT_ID(`productTags`, id).then(product => {
            // Kiểm tra xem dữ liệu trả về có tồn tại không
            if (product && product.data && Array.isArray(product.data)) {
                // Lấy tất cả các tagId từ mảng JSON và lưu vào mảng initialTags
                const tagIds = product.data.map(item => item.tag.tagId);
                setInitialTags(tagIds);
                setSelectedTags(tagIds);
                console.log(tagIds)
            } else {
                // Xử lý trường hợp dữ liệu không hợp lệ
                console.error("Invalid data structure or missing data");
            }
        }).catch(error => {
            // Xử lý lỗi khi gọi API
            console.error("Error fetching product tags:", error);
        });


    }, [id]);
    const handleAddToCart = async () => {
        if (!userId) {
            // Nếu userId không tồn tại (người dùng chưa đăng nhập), điều hướng đến trang login
            window.location.href = "/login";
            return;
        }

        try {
            // Gửi yêu cầu POST để thêm sản phẩm vào giỏ hàng
            const response = await axios.post('https://localhost:7062/api/Cart', {
                idUser: userId,
                idProduct: id// Thay ID_PRODUCT_HERE bằng ID sản phẩm bạn muốn thêm vào giỏ hàng
            });

            // Xử lý kết quả sau khi thêm sản phẩm vào giỏ hàng (nếu cần)
            console.log('Product added to cart:', response);
        } catch (error) {
            console.error('Error adding product to cart: ', error);
        }
    };

    // const navigate = useNavigate();
    // if (checkAdd) {
    //     navigate('/admin/Product/Index');
    // }
    // const handleFileSelecta1 = (event) => {
    //     const fileName = event.target.files;
    //     let fileNames = "";
    //     fileNames = fileName[0].name;
    //     setImagePath(fileNames)
    // };

    // const handleFileSelect = (event) => {
    //     const files = event.target.files;
    //     let fileNames = "";
    //     for (let i = 0; i < files.length; i++) {
    //         fileNames += files[i].name;
    //         if (i !== files.length - 1) {
    //             fileNames += ", "; // Thêm dấu phẩy và khoảng trắng giữa các tên tệp
    //         }
    //     }
    //     // Lưu chuỗi tên tệp vào state hoặc làm gì đó với nó
    //     setPlaceholder(prev => prev ? prev + ", " + fileNames : fileNames);

    //     console.log(placeholder);
    //     console.log(imagePath);

    // };
    // const handleDeleteFile = (fileNameToDelete) => {
    //     setPlaceholder(prev => {
    //         const updatedFileNames = prev.split(", ").filter(fileName => fileName !== fileNameToDelete).join(", ");
    //         console.log(updatedFileNames);
    //         setPlaceholder(updatedFileNames);
    //         console.log(placeholder);
    //         console.log(imagePath);

    //         return updatedFileNames;

    //     });
    // };
    const handleImageClick = (path) => {
        setShowImg(path);
    };
    return (
        <>

            <section className="section-content bg-white padding-y">
                <div className="container">

                    <div className="row">
                        <aside className="col-md-6">
                            <div className="card">
                                <article className="gallery-wrap">
                                    <div className="img-big-wrap">
                                        <div>  {/* Hình 1 */}
                                            {showImg && showImg.length > 0 ?
                                                <img
                                                    src={require(`../../../assets/images/products/${showImg}`)}
                                                    alt={showImg}
                                                    style={{ width: '100%', objectFit: 'cover' }}
                                                />
                                                :
                                                <img
                                                    src={require(`../../../assets/images/products/chamhoi.png`)}
                                                    alt={showImg}
                                                    style={{ width: '50px', height: '50px', objectFit: 'cover' }}
                                                />
                                            }
                                        </div>
                                    </div>{/* <!--slider-product.// --> */}
                                    <div className="thumbs-wrap container">
                                        <div style={{ display: 'flex' }}>
                                            {allImg.split(', ').map((path, index) => {
                                                const trimmedPath = path.trim();
                                                if (trimmedPath !== '') {
                                                    return (
                                                        <img
                                                            key={index}
                                                            src={require(`../../../assets/images/products/${trimmedPath}`)}
                                                            alt={placeholder}
                                                            width={100} // Chiều rộng cố định
                                                            height="auto" // Chiều cao tự động tính toán dựa trên tỷ lệ chiều rộng
                                                            className="product-img hover"
                                                            onClick={() => handleImageClick(trimmedPath)}

                                                        />
                                                    );
                                                }
                                                return null;
                                            })}
                                        </div>


                                    </div>{/* <!--slider-nav.// --> */}
                                </article>{/* <!--gallery-wrap .end// --> */}
                            </div>{/* <!--card.// --> */}
                        </aside>
                        <main className="col-md-6">
                            <article className="product-info-aside">

                                <h1 className="title mt-3">{productName}</h1>

                                <div className="rating-wrap my-3">
                                    <ul className="rating-stars">
                                        <li className="stars-active">
                                            <i className="fa fa-star"></i> <i className="fa fa-star"></i>
                                            <i className="fa fa-star"></i> <i className="fa fa-star"></i>
                                            <i className="fa fa-star"></i>
                                        </li>
                                        <li>
                                            <i className="fa fa-star"></i> <i className="fa fa-star"></i>
                                            <i className="fa fa-star"></i> <i className="fa fa-star"></i>
                                            <i className="fa fa-star"></i>
                                        </li>
                                    </ul>
                                    <small className="label-rating text-muted">132 reviews</small>
                                    <small className="label-rating text-success"> <i className="fa fa-clipboard-check"></i> 154 orders </small>
                                </div>{/* <!--rating-wrap.// --> */}

                                <div className="mb-3">
                                    <var className="price h4">USD {salePrice}.00 </var>
                                    <span className="text-muted">USD {comparePrice}.00 incl. VAT</span>
                                </div>{/* <!--price-detail-wrap .// --> */}
                                <h4>Product Description</h4>
                                <p>{productDescription} </p>


                                <dl className="row">
                                    {/* <dt className="col-sm-3">Manufacturer</dt>
                                    <dd className="col-sm-9"><a href="#">Great textile Ltd.</a></dd> */}

                                    <dt className="col-sm-3">Quantity</dt>
                                    <dd className="col-sm-9">{quantity}</dd>
                                    {/* 
                                    <dt className="col-sm-3">Guarantee</dt>
                                    <dd className="col-sm-9">{categoryId}</dd>

                                    <dt className="col-sm-3">Delivery time</dt>
                                    <dd className="col-sm-9">3-4 days</dd>

                                    <dt className="col-sm-3">Availabilty</dt>
                                    <dd className="col-sm-9">in Stock</dd> */}
                                </dl>

                                <div className="form-row  mt-4">
                                    <div className="form-group col-md flex-grow-0">
                                        <div className="input-group mb-3 input-spinner">
                                            <div className="input-group-prepend">
                                                <button className="btn btn-light" type="button" id="button-plus"> + </button>
                                            </div>
                                            <input type="text" className="form-control" value="1" />
                                            <div className="input-group-append">
                                                <button className="btn btn-light" type="button" id="button-minus"> &minus; </button>
                                            </div>
                                        </div>
                                    </div>{/* <!--col.// --> */}
                                    <div className="form-group col-md">
                                        <a href="#" onClick={handleAddToCart} className="btn  btn-primary">
                                            <i className="fas fa-shopping-cart"></i> <span className="text">Add to cart</span>
                                        </a>
                                        <a href="#" className="btn btn-light">
                                            <i className="fas fa-envelope"></i> <span className="text">Contact supplier</span>
                                        </a>
                                    </div>{/* <!--col.// --> */}
                                </div>{/* <!--row.// --> */}

                            </article>{/* <!--product-info-aside .// --> */}
                        </main>{/* <!--col.// --> */}
                    </div>{/* <!--row.// --> */}



                </div > {/* <!--container .//  --> */}
            </section >
            <section className="section-name padding-y bg">
                <div className="container">

                    <div className="row">
                        <div className="col-md-8">
                            <h5 className="title-description">Description</h5>
                            <p>
                                {shortDescription}
                            </p>
                            <ul className="list-check">
                                <li>Material: Stainless steel</li>
                                <li>Weight: 82kg</li>
                                <li>built-in drip tray</li>
                                <li>Open base for pots and pans</li>
                                <li>On request available in propane execution</li>
                            </ul>

                            <h5 className="title-description">Specifications</h5>
                            <table className="table table-bordered">
                                <tr> <th colspan="2">Basic specs</th> </tr>
                                <tr> <td>Type of energy</td><td>Lava stone</td> </tr>
                                <tr> <td>Number of zones</td><td>2</td> </tr>
                                <tr> <td>Automatic connection	</td> <td> <i className="fa fa-check text-success"></i> Yes </td></tr>

                                <tr> <th colspan="2">Dimensions</th> </tr>
                                <tr> <td>Width</td><td>500mm</td> </tr>
                                <tr> <td>Depth</td><td>400mm</td> </tr>
                                <tr> <td>Height	</td><td>700mm</td> </tr>

                                <tr> <th colspan="2">Materials</th> </tr>
                                <tr> <td>Exterior</td><td>Stainless steel</td> </tr>
                                <tr> <td>Interior</td><td>Iron</td> </tr>

                                <tr> <th colspan="2">Connections</th> </tr>
                                <tr> <td>Heating Type</td><td>Gas</td> </tr>
                                <tr> <td>Connected load gas</td><td>15 Kw</td> </tr>

                            </table>
                        </div>{/* <!--col.// --> */}

                        <aside className="col-md-4">

                            <div className="box">

                                <h5 className="title-description">Files</h5>
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                                    quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                    consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                                    cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                                    proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                                </p>

                                {/* <h5 className="title-description">Videos</h5> */}


                                {/* <article className="media mb-3">
                                    <a href="#"><img className="img-sm mr-3" src="images/posts/3.jpg" /></a>
                                    <div className="media-body">
                                        <h6 className="mt-0"><a href="#">How to use this item</a></h6>
                                        <p className="mb-2"> Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin </p>
                                    </div>
                                </article>

                                <article className="media mb-3">
                                    <a href="#"><img className="img-sm mr-3" src="images/posts/2.jpg" /></a>
                                    <div className="media-body">
                                        <h6 className="mt-0"><a href="#">New tips and tricks</a></h6>
                                        <p className="mb-2"> Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin </p>
                                    </div>
                                </article>

                                <article className="media mb-3">
                                    <a href="#"><img className="img-sm mr-3" src="images/posts/1.jpg" /></a>
                                    <div className="media-body">
                                        <h6 className="mt-0"><a href="#">New tips and tricks</a></h6>
                                        <p className="mb-2"> Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin </p>
                                    </div>
                                </article> */}



                            </div>{/* <!--box.// --> */}
                        </aside>{/* <!--col.// --> */}
                    </div>{/* <!--row.// --> */}

                </div>{/* <!--container .//  --> */}
            </section>



            <section className="padding-y-lg bg-light border-top">
                <div className="container">

                    <p className="pb-2 text-center">Delivering the latest product trends and industry news straight to your inbox</p>

                    <div className="row justify-content-md-center">
                        <div className="col-lg-4 col-sm-6">
                            <form className="form-row">
                                <div className="col-8">
                                    <input className="form-control" placeholder="Your Email" type="email" />
                                </div>{/* <!--col.// --> */}
                                <div className="col-4">
                                    <button type="submit" className="btn btn-block btn-warning"> <i className="fa fa-envelope"></i> Subscribe </button>
                                </div>{/* <!--col.// --> */}
                            </form>
                            <small className="form-text">We’ll never share your email address with a third-party. </small>
                        </div>{/* <!--col-md-6.// --> */}
                    </div>


                </div>
            </section>

        </>
    )
}
