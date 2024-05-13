import React, { useEffect, useState } from 'react'
import { makeStyles } from '@mui/styles';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Alert from '@mui/material/Alert';
import { Redirect } from 'react-router-dom';
import IconButton from '@mui/icons-material/IosShare';
import CloseIcon from '@mui/icons-material/IosShare';
import Button from '@mui/material/Button';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { Link } from 'react-router-dom'
import productservice from '../../../services/ProductService';
import { TextField } from '@mui/material';
const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
        marginTop: 20
    },
    paper: {
        width: '100%',
        margin: 'auto'
    },
    removeLink: {
        textDecoration: 'none'
    },
    priceCell: {
        textAlign: 'center',
    },
    tableContainer: {
        overflow: 'auto', // Thêm thanh trượt khi nội dung vượt quá kích thước
        maxHeight: '600px', // Đặt chiều cao tối đa để kích hoạt thanh trượt
    },
    searchContainer: {
        display: 'flex',
        justifyContent: 'center',
        margin: '10px 0', // Khoảng cách xung quanh ô nhập
    },
    searchInput: {
        width: '50%', // Chiều rộng của ô nhập
    },
}));
const formatPrice = (price) => {
    // Sử dụng Number.toFixed(2) để giữ chính xác hai số sau dấu thập phân
    return Number(price).toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ".");
};
export default function ProductList() {
    const classes = useStyles();
    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(1);
    const [productsPerPage] = useState(4);
    const [currentPage, setCurrentPage] = useState(1);
    const [searchQuery, setSearchQuery] = useState(''); // Trạng thái cho giá trị tìm kiếm


    useEffect(() => {
        productservice.GET_ALL_PRODUCTS(`products`).then(response => {
            if (Array.isArray(response.data)) {
                setProducts(response.data);
            }
        }).catch(error => {
            console.error('Error fetching products:', error);
        });
    }, []);
    console.log(products);
    const deleteProductID = async (id) => {
        try {
            // Xóa productTags có productId là id
            await productservice.DELETE_PRODUCT_TAGS_BY_PRODUCT_ID('productTags', id);
            console.log('Product tags deleted successfully');

            // Xóa productCategories có productId là id
            await productservice.DELETE_PRODUCT_CATEGORIES_BY_PRODUCT_ID('productCategories', id);
            console.log('Product categories deleted successfully');

            // Xóa productGalleries có productId là id
            await productservice.DELETE_GALLERY_BY_PRODUCT_ID('galleries', id);
            console.log('productGalleries deleted successfully');

            // Xóa product có productId là id
            const response = await productservice.DELETE_PRODUCT_ID(`products/${id}`);
            console.log('Product deleted successfully');

            // Kiểm tra nếu xóa thành công thì cập nhật danh sách sản phẩm
            if (response.data) {
                setProducts(products.filter(product => product.productId !== id));
            }
        } catch (error) {
            console.error('Error deleting product:', error);
        }
    };
    // Hàm xử lý sự kiện onChange của input tìm kiếm
    const handleSearchChange = (e) => {
        setSearchQuery(e.target.value);
    };

    // Lọc sản phẩm dựa trên giá trị tìm kiếm
    const filteredProducts = products.filter(product =>
        product.productName.toLowerCase().includes(searchQuery.toLowerCase())
    );
    console.log(filteredProducts);
    // Tính toán chỉ mục sản phẩm đầu tiên của trang hiện tại
    const indexOfLastProduct = page * productsPerPage;
    const indexOfFirstProduct = indexOfLastProduct - productsPerPage;
    const currentProducts = filteredProducts.slice(indexOfFirstProduct, indexOfLastProduct);

    // Thay đổi trang
    const paginate = (pageNumber) => setPage(pageNumber);


    const RawHTML = (body, className) => {
        // Kiểm tra nếu body là null hoặc không xác định
        if (!body) {
            return null;
        }
        return (
            <div className={className} dangerouslySetInnerHTML={{ __html: body.replace(/\n/g, '<br />') }} />
        );
    };




    return (
        <div className={classes.root}>
            <section className="content-header my-2">
                <h1 className="d-inline">Sản phẩm</h1>
                <Link to="/admin/product/create" className="btn-add" width="100px">Thêm mới</Link>
                <div className="row mt-3 align-items-center">
                    <div className="col-6">
                        <ul className="manager">
                            <li>
                                <Link to="/admin/product">Tất cả </Link>
                            </li>
                            <li>
                                <Link to="#">Xuất bản (12)</Link>
                            </li>
                            <li>
                                <Link to="/admin/product/trash">Rác </Link>
                            </li>
                        </ul>
                    </div>
                    <div className={classes.searchContainer}>
                        <TextField
                            className={classes.searchInput}
                            label="Tìm kiếm sản phẩm"
                            variant="outlined"
                            value={searchQuery}
                            size="small"
                            onChange={handleSearchChange}
                        />
                    </div>

                </div>
                <div className="row mt-1 align-items-center">
                    <div className="col-md-8">
                        <select name="" className="d-inline me-1">
                            <option value="">Hành động</option>
                            <option value="">Bỏ vào thùng rác</option>
                        </select>
                        <button className="btnapply">Áp dụng</button>
                        <select name="" className="d-inline me-1">
                            <option value="">Tất cả danh mục</option>
                        </select>
                        <select name="" className="d-inline me-1">
                            <option value="">Tất cả thương hiệu</option>
                        </select>
                        <button className="btnfilter">Lọc</button>
                    </div>
                    {/* <div className="col-md-4 text-end">
                        <nav aria-label="Page navigation example">
                            <ul className="pagination pagination-sm justify-content-end">
                                <li className="page-item disabled">
                                    <Link className="page-link">&laquo;</Link>
                                </li>
                                <li className="page-item">
                                    <Link className="page-link" to="#">1</Link>
                                </li>
                                <li className="page-item">
                                    <Link className="page-link" to="#">2</Link>
                                </li>
                                <li className="page-item">
                                    <Link className="page-link" to="#">3</Link>
                                </li>
                                <li className="page-item">
<Link className="page-link" to="#">&raquo;</Link>
                                </li>
                            </ul>
                        </nav>
                    </div> */}
                </div>
            </section>
            <Grid container spacing={3}>
                <Grid item xs={12}>
                    <Paper className={classes.paper}>

                        <TableContainer component={Paper} className={classes.tableContainer}>
                            <Table className={classes.table} aria-label="simple table">
                                <TableHead>
                                    <TableRow>
                                        <TableCell>Name</TableCell>
                                        <TableCell align="center">Image Path</TableCell>
                                        <TableCell align="center">Place Holder</TableCell>

                                        <TableCell align="center">Slug</TableCell>
                                        <TableCell align="center">Sku</TableCell>
                                        <TableCell align="center" style={{ width: '200px' }}>Tag</TableCell>
                                        <TableCell align="center">Category</TableCell>
                                        <TableCell align="center">Sale Price</TableCell>
                                        <TableCell align="center">Comepare Price</TableCell>
                                        <TableCell align="center">Buying Price</TableCell>
                                        <TableCell align="center">Quantity</TableCell>
                                        <TableCell align="center">Short Description</TableCell>
                                        <TableCell align="center">Product Description</TableCell>
                                        <TableCell align="center">Note</TableCell>
                                        <TableCell align="center">Product Type</TableCell>
                                        <TableCell align="center">Published</TableCell>
                                        <TableCell align="center">DisableOutOfStock</TableCell>
                                        {/* <TableCell align="center">Created At</TableCell>
                                        <TableCell align="center">Updated At</TableCell>
                                        <TableCell align="center">Created By</TableCell>
                                        <TableCell align="center">Updated By</TableCell> */}
                                        <TableCell align="center">Modify</TableCell>
                                        <TableCell align="center">Delete</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {currentProducts.length > 0 &&
                                        currentProducts
                                            .sort((a, b) => {
                                                const aCreatedAt = new Date(a.createdAt);
                                                const bCreatedAt = new Date(b.createdAt);
                                                const aUpdatedAt = new Date(a.updatedAt);
                                                const bUpdatedAt = new Date(b.updatedAt);

                                                // So sánh theo createdAt trước
                                                if (bCreatedAt.getTime() !== aCreatedAt.getTime()) {
                                                    return bCreatedAt.getTime() - aCreatedAt.getTime();
                                                } else {
                                                    // Nếu createdAt giống nhau, thì so sánh theo updatedAt
                                                    return bUpdatedAt.getTime() - aUpdatedAt.getTime();
                                                }
                                            })
                                            .map((row) => (
                                                <TableRow key={row.productId}>
                                                    <TableCell component="th" scope="row">{row.productName}</TableCell>
                                                    <TableCell align="center">
                                                        <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                                                            {/* Hình 1 */}
                                                            {row.productGalleries && row.productGalleries.length > 0 ?
                                                                <img
                                                                    src={require(`../../../assets/images/products/${row.productGalleries[0].imagePath}`)}
                                                                    alt={row.productName}
                                                                    style={{ width: '50px', height: '50px', objectFit: 'cover' }}
                                                                />
                                                                :
                                                                <img
                                                                    src={require(`../../../assets/images/products/chamhoi.png`)}
                                                                    alt={row.productName}
                                                                    style={{ width: '50px', height: '50px', objectFit: 'cover' }}
                                                                />
                                                            }


                                                        </div>
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        {row.productGalleries && row.productGalleries.length > 0 ? (
                                                            <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                                                                {row.productGalleries[0].placeholder.split(', ').map((placeholder, index, arr) => (
                                                                    <React.Fragment key={index}>
                                                                        <img
                                                                            src={require(`../../../assets/images/products/${placeholder.trim()}`)}
                                                                            alt={row.productName}
                                                                            style={{
                                                                                width: '50px',
                                                                                height: '50px',
                                                                                objectFit: 'cover',
                                                                                marginRight: index !== arr.length - 1 ? '10px' : '0' // Thêm margin-right nếu không phải là hình cuối cùng
                                                                            }}
                                                                        />
                                                                    </React.Fragment>
                                                                ))}
                                                            </div>
                                                        ) : (
                                                            <img
                                                                src={require(`../../../assets/images/products/chamhoi.png`)}
                                                                alt={row.productName}
                                                                style={{ width: '50px', height: '50px', objectFit: 'cover' }}
                                                            />
                                                        )}

                                                    </TableCell>

                                                    <TableCell align="left">{RawHTML(row.slug, "body")}</TableCell>
                                                    <TableCell align="center">{row.sku}</TableCell>
                                                    <TableCell align="left">
                                                        {row.productTags && row.productTags.length > 0 ? (
                                                            RawHTML(row.productTags.map(tag => tag.tag.tagName).join(', '), "body")
                                                        ) : (
                                                            <img
                                                                src={require(`../../../assets/images/products/chamhoi.png`)}
                                                                alt={row.productName}
                                                                style={{ width: '50px', height: '50px', objectFit: 'cover' }}
                                                            />
                                                        )}
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        {row.productCategories && row.productCategories.length > 0 ?
                                                            row.productCategories[0].category.categoryName :
                                                            <img
                                                                src={require(`../../../assets/images/products/chamhoi.png`)}
                                                                alt={row.productName}
                                                                style={{ width: '50px', height: '50px', objectFit: 'cover' }}
                                                            />
                                                        }
                                                    </TableCell>

                                                    <TableCell align="center" className={classes.priceCell}>
                                                        {formatPrice(row.salePrice)}
                                                    </TableCell>
                                                    <TableCell align="center" className={classes.priceCell}>
                                                        {formatPrice(row.comparePrice)}
                                                    </TableCell>
                                                    <TableCell align="center" className={classes.priceCell}>
                                                        {formatPrice(row.buyingPrice)}
                                                    </TableCell>
                                                    <TableCell align="center" className={classes.priceCell}>
                                                        {row.quantity}
                                                    </TableCell>
                                                    <TableCell align="left">{RawHTML(row.shortDescription, "body")}</TableCell>
                                                    <TableCell align="left">{RawHTML(row.productDescription, "body")}</TableCell>
                                                    <TableCell align="left">{RawHTML(row.note, "body")}</TableCell>
                                                    <TableCell align="center">{row.productType}</TableCell>
                                                    <TableCell align="center">
                                                        {row.published ? 'True' : 'False'}
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        {row.disableOutOfStock ? 'True' : 'False'}
                                                    </TableCell>
                                                    {/* <TableCell align="center">{row.createdAt}</TableCell>
                                                    <TableCell align="center">{row.updatedAt}</TableCell>
                                                    <TableCell align="center">{row.createdBy && `${row.createdBy.firstName} ${row.createdBy.lastName}`}</TableCell>
                                                    <TableCell align="center">{row.updatedBy && `${row.updatedBy.firstName} ${row.updatedBy.lastName}`}</TableCell> */}
                                                    <TableCell align="center">
                                                        <Link to={`/admin/product-update/${row.productId}`} className={classes.removeLink}>
                                                            <Button size="small" variant="contained" color="primary">Edit</Button></Link>
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        <Button size="small" variant="contained" color="secondary"
                                                            onClick={() => deleteProductID(row.productId)}>Remove</Button>
                                                    </TableCell>
                                                </TableRow>
                                            ))}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </Paper>
                    <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                        {Array.from({ length: Math.ceil(products.length / productsPerPage) }, (_, index) => (
                            <Button
                                key={index}
                                onClick={() => {
                                    paginate(index + 1);
                                    setCurrentPage(index + 1);
                                }}
                                style={{ backgroundColor: currentPage === index + 1 ? 'pink' : 'inherit' }}
                            >
                                {index + 1}
                            </Button>
                        ))}
                    </div>

                </Grid>
            </Grid>
        </div>
    )
}
