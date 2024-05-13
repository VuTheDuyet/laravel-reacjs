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

const customSpacing = (multiplier) => `${multiplier * 8}px`;
const useStyles = makeStyles(() => ({
    root: {
        flexGrow: 1,
        marginTop: 20
    },
    paper: {
        padding: customSpacing(2),
        margin: 'auto',
        maxWidth: 600,
    },
    title: {
        fontSize: 30,
        textAlign: 'center'
    },
    link: {
        padding: 10,
        display: 'inline-block'
    },
    txtInput: {
        width: '98%',
        margin: '1%'
    },
    submit: {
        margin: `${customSpacing(3)} 0 ${customSpacing(2)}`,
    },
}));
const currencies = [
    {
        value: 'USD',
        label: '$',
    },
    {
        value: 'EUR',
        label: '€',
    },
    {
        value: 'BTC',
        label: '฿',
    },
    {
        value: 'JPY',
        label: '¥',
    },
];
export default function ProductUpdate() {
    const { id } = useParams();
    const [idProduct, setIdProduct] = useState(0);
    const classes = useStyles();
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
            setPlaceholder(product.data.productGalleries[0].placeholder);
            setCategoryId(product.data.productCategories[0].category.categoryId);
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

    const handleChangeProductName = (event) => {
        setProductName(event.target.value)
    }
    const handleChangeSlug = (event) => {
        setSlug(event.target.value)
    }
    const handleChangeSku = (event) => {
        setSku(event.target.value)
    }
    const handleChangeSalePrice = (event) => {
        setSalePrice(event.target.value)
    }
    const handleChangeComparePrice = (event) => {
        setComparePrice(event.target.value)
    }
    const handleChangeBuyingPrice = (event) => {
        setBuyingPrice(event.target.value)
    }
    const handleChangeQuantity = (event) => {
        setQuantity(event.target.value);
    };
    const handleChangeShortDescription = (event) => {
        setShortDescription(event.target.value);
    };
    const handleChangeProductDescription = (event) => {
        setProductDescription(event.target.value);
    };
    const handleChangeNote = (event) => {
        setNote(event.target.value);
    };
    const handleChangeProductType = (event) => {
        const newValue = event.target.value.toString();
        setProductType(newValue);
    };
    const handleChangeDisableOutOfStock = (event) => {
        setDisableOutOfStock(event.target.value);
    };
    const handleChangeCreatedBy = (event) => {
        setCreatedBy(event.target.value);
    };
    const handleChangeUpdatedBy = (event) => {
        setUpdatedBy(event.target.value);
    };
    const handleChangePublished = (event) => {
        setPublished(event.target.value);
    };
    const handleChangeIsThumbnail = (event) => {
        setIsThumbnail(event.target.value);
    };
    // const handleChangeImagePath = (event) => {
    //     const newValue = event.target.value.toString();
    //     setImagePath(newValue)
    // };
    // const handleChangePlaceholder = (event) => {
    //     const newValue = event.target.value.toString();
    //     setPlaceholder(newValue)
    // };





    // Hàm xử lý sự kiện khi thêm/xóa thẻ
    const handleChangeTagId = (event) => {
        const value = event.target.value;

        // Kiểm tra xem tag đã tồn tại trong danh sách tagsToDelete chưa
        const tagToDeleteExists = tagsToDelete.includes(value);
        // Kiểm tra xem tag đã tồn tại trong danh sách tagsToAdd chưa
        const tagToAddExists = tagsToAdd.includes(value);

        setSelectedTags(prevState => {
            // Kiểm tra xem thẻ đã được chọn có trong danh sách ban đầu không
            const tagExists = initialTags.includes(value);
            if (tagExists) {
                // Nếu thẻ đã tồn tại, loại bỏ nó khỏi danh sách xóa nếu chưa tồn tại
                if (!tagToDeleteExists) {
                    setTagsToDelete(prevState => [...prevState, value]);
                }
            } else {
                // Nếu thẻ mới, thêm vào danh sách thêm mới nếu chưa tồn tại và chưa tồn tại trong tagsToDelete
                if (!tagToAddExists && !prevState.includes(value)) {
                    setTagsToAdd(prevState => [...prevState, value]);
                }
            }

            // Trả về danh sách thẻ đã chọn mới
            return prevState.includes(value) ? prevState.filter(tag => tag !== value) : [...prevState, value];
        });
    };




    const handleDeleteTag = (tagIdToDelete) => {
        // Loại bỏ thẻ khỏi danh sách selectedTags
        setSelectedTags((prevTags) => prevTags.filter((tagId) => tagId !== tagIdToDelete));

        // Kiểm tra xem thẻ đã được chọn có trong danh sách ban đầu không
        const tagExists = initialTags.includes(tagIdToDelete);
        if (tagExists) {
            // Nếu thẻ đã tồn tại, thêm vào danh sách xóa thực sự
            setTagsToDelete(prevState => [...prevState, tagIdToDelete]);
        }
    };


    const handleChangeCategoryId = (event) => {
        setCategoryId(event.target.value)
    };

    const EditProduct = (event) => {
        event.preventDefault();
        if (productName !== "" && slug !== "" && sku !== "" && salePrice !== "" && comparePrice !== "" &&
            buyingPrice !== "" && quantity !== "" && shortDescription !== "" && productDescription !== "" &&
            note !== "" && productType !== "" && disableOutOfStock !== null && createdBy !== "no-one" && updatedBy !== "no-one" && published !== null
            && isThumbnail !== null && imagePath !== "" && placeholder !== "" && selectedTags.length > 0 && categoryId !== "no-category") {
            let product = {
                slug: slug,
                productName: productName,
                sku: sku,
                salePrice: salePrice,
                comparePrice: comparePrice,
                buyingPrice: buyingPrice,
                quantity: quantity,
                shortDescription: shortDescription,
                productDescription: productDescription,
                note: note,
                productType: productType,
                disableOutOfStock: disableOutOfStock,
                published: published,
                createdBy: {
                    staffAccountId: createdBy
                },
                updatedBy: {
                    staffAccountId: updatedBy
                }
            };

            productservice.PUT_EDIT_PRODUCT(`products/${idProduct}`, product).then(item => {
                if (item.data) {
                    const productId = item.data.productId; // Lấy ID của sản phẩm mới tạo

                    // Cập nhật các đối tượng liên quan trước khi thêm vào cơ sở dữ liệu
                    let gallery = {
                        product: {
                            productId: productId
                        },
                        imagePath: imagePath,
                        isThumbnail: isThumbnail,
                        placeholder: placeholder,
                        createdBy: {
                            staffAccountId: createdBy
                        },
                        updatedBy: {
                            staffAccountId: updatedBy
                        }
                    };

                    let product_category = {
                        product: {
                            productId: productId
                        },
                        category: {
                            categoryId: categoryId
                        }
                    };




                    // Xóa các thẻ không còn tồn tại
                    tagsToDelete.forEach(tagId => {
                        productservice.DELETE_PRODUCT_TAGS_BY_TAG_ID_FROM_PRODUCT('productTags', productId, tagId)
                            .then(response => {
                                console.log("Deleted tag:", response.data);
                            })
                            .catch(error => {
                                console.error("Error deleting tag:", error);
                            });
                    });

                    // Thêm các thẻ mới vào sản phẩm
                    tagsToAdd.forEach(tagId => {
                        const product_tag = {
                            tag: { tagId },
                            product: { productId }
                        };
                        productservice.POST_ADD_PRODUCT_TAG(`productTags`, product_tag)
                            .then(response => {
                                console.log("Added tag:", response.data);
                            })
                            .catch(error => {
                                console.error("Error adding tag:", error);
                            });
                    });

                    productservice.GET_GALLERY_BY_PRODUCT_ID('galleries', productId).then(item => {
                        if (item.data && item.data.length > 0) {
                            const galleryId = item.data[0].galleryId;
                            galleryservice.PUT_EDIT_GALLERY(`galleries/${galleryId}`, gallery).then(item => {
                                console.log(item.data)
                            }).catch(error => {
                                console.error("Error editing gallery:", error);
                            });
                        } else {
                            console.error("No product categories found for the product with ID:", productId);
                        }
                    }).catch(error => {
                        console.error("Error getting gallery:", error);
                    });

                    productservice.GET_PRODUCT_CATEGORIES_BY_PRODUCT_ID(`productCategories`, productId).then(item => {
                        if (item.data && item.data.length > 0) {
                            const productCategoryId = item.data[0].productCategoryId; // Lấy productCategoryId từ phần tử đầu tiên
                            productservice.PUT_EDIT_PRODUCT_CATEGORY(`productCategories/${productCategoryId}`, product_category).then(item => {
                                console.log(item.data)
                            }).catch(error => {
                                console.error("Error editing Product_Category:", error);
                            });
                        } else {
                            console.error("No product categories found for the product with ID:", productId);
                        }

                    }).catch(error => {
                        console.error("Error getting Product_Category:", error);
                    });
                    setCheckAdd(true);
                }
            }).catch(error => {
                console.error("Error editing product:", error);
            });
        } else {
            alert("Bạn chưa nhập đủ thông tin!");
        }
    };

    const navigate = useNavigate();
    if (checkAdd) {
        navigate('/admin/Product/Index');
    }
    const handleFileSelecta1 = (event) => {
        const fileName = event.target.files;
        let fileNames = "";
        fileNames = fileName[0].name;
        setImagePath(fileNames)
    };

    const handleFileSelect = (event) => {
        const files = event.target.files;
        let fileNames = "";
        for (let i = 0; i < files.length; i++) {
            fileNames += files[i].name;
            if (i !== files.length - 1) {
                fileNames += ", "; // Thêm dấu phẩy và khoảng trắng giữa các tên tệp
            }
        }
        // Lưu chuỗi tên tệp vào state hoặc làm gì đó với nó
        setPlaceholder(prev => prev ? prev + ", " + fileNames : fileNames);

        console.log(placeholder);
        console.log(imagePath);

    };
    const handleDeleteFile = (fileNameToDelete) => {
        setPlaceholder(prev => {
            const updatedFileNames = prev.split(", ").filter(fileName => fileName !== fileNameToDelete).join(", ");
            console.log(updatedFileNames);
            setPlaceholder(updatedFileNames);
            console.log(placeholder);
            console.log(imagePath);

            return updatedFileNames;

        });
    };
    return (
        <div className={classes.root}>
            <Grid container spacing={3}>
                <Grid item xs={12}>
                    <Paper className={classes.paper}>
                        <Typography className={classes.title} variant="h4">
                            Update Product
                        </Typography>
                        <Grid item xs={12} sm container>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Product Name
                                </Typography>
                                <TextField id="ProductName" onChange={handleChangeProductName} name="ProductName" value={productName}
                                    variant="outlined" className={classes.txtInput} size="small" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Slug
                                </Typography>
                                <TextField id="Slug" onChange={handleChangeSlug} name="Slug" value={slug}
                                    variant="outlined" className={classes.txtInput} size="small" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Sku
                                </Typography>
                                <TextField id="Sku" onChange={handleChangeSku} name="Sku" value={sku}
                                    variant="outlined" className={classes.txtInput} size="small" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Sale Price
                                </Typography>
                                <TextField id="SalePrice" onChange={handleChangeSalePrice} name="SalePrice" value={salePrice}
                                    variant="outlined" className={classes.txtInput} size="small" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Compare Price
                                </Typography>
                                <TextField id="ComparePrice" onChange={handleChangeComparePrice} name="ComparePrice" value={comparePrice}
                                    variant="outlined" className={classes.txtInput} size="small" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Buying Price
                                </Typography>
                                <TextField id="BuyingPrice" onChange={handleChangeBuyingPrice} name="BuyingPrice" value={buyingPrice}
                                    variant="outlined" className={classes.txtInput} size="small" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Quantity
                                </Typography>
                                <TextField id="Quantity" onChange={handleChangeQuantity} name="Quantity" value={quantity}
                                    variant="outlined" className={classes.txtInput} size="small" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Short Description
                                </Typography>
                                <TextField id="outlined-multiline-static" onChange={handleChangeShortDescription} value={shortDescription}
                                    name="ShortDescription" className={classes.txtInput} multiline rows={4} variant="outlined" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Product Description
                                </Typography>
                                <TextField id="outlined-multiline-static" onChange={handleChangeProductDescription} value={productDescription}
                                    name="ProductDescription" className={classes.txtInput} multiline rows={4} variant="outlined" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Note
                                </Typography>
                                <TextField id="outlined-multiline-static" onChange={handleChangeNote} value={note}
                                    name="Note" className={classes.txtInput} multiline rows={4} variant="outlined" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    ImagePath:
                                </Typography>
                                <input
                                    type="file"
                                    onChange={handleFileSelecta1}
                                    className={classes.fileInput}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Placeholder:
                                </Typography>
                                <div>
                                    {placeholder.split(", ").map((fileName, index) => (
                                        <Chip
                                            key={index}
                                            label={fileName}
                                            onDelete={() => handleDeleteFile(fileName)}
                                            className={classes.tagChip}
                                        />
                                    ))}
                                </div>
                                <input
                                    type="file"
                                    onChange={handleFileSelect}
                                    className={classes.fileInput}
                                    multiple // Cho phép chọn nhiều tệp
                                />

                            </Grid>
                            <Grid item xs={12} style={{ marginBottom: '16px' }}>
                                <Typography gutterBottom variant="subtitle1">
                                    Choose IsThumbnail
                                </Typography>
                                <TextField
                                    id="outlined-select-currency-native"
                                    name="IsThumbnail"
                                    select
                                    value={isThumbnail}
                                    onChange={handleChangeIsThumbnail}
                                    SelectProps={{
                                        native: true,
                                    }}
                                    variant="outlined"
                                    className={classes.txtInput}
                                >
                                    <option value="true">True</option>
                                    <option value="false">False</option>
                                </TextField>
                            </Grid>
                            <Grid item xs={12} style={{ marginBottom: '16px' }}>
                                <Typography gutterBottom variant="subtitle1">
                                    Choose ProductType
                                </Typography>
                                <TextField
                                    id="outlined-select-currency-native"
                                    name="productType"
                                    select
                                    value={productType}
                                    onChange={handleChangeProductType}
                                    SelectProps={{
                                        native: true,
                                    }}
                                    variant="outlined"
                                    className={classes.txtInput}
                                >
                                    <option value="null">Null</option>
                                    <option value="sale">Sale</option>
                                    <option value="new">New</option>
                                    <option value="15%">15%</option>
                                </TextField>
                            </Grid>



                            <Grid item xs={12} style={{ marginBottom: '16px' }}>
                                <Typography gutterBottom variant="subtitle1">
                                    Choose DisableOutOfStock
                                </Typography>
                                <TextField
                                    id="outlined-select-currency-native"
                                    name="disableOutOfStock"
                                    select
                                    value={disableOutOfStock} // Giá trị boolean
                                    onChange={handleChangeDisableOutOfStock}
                                    SelectProps={{
                                        native: true,
                                    }}
                                    variant="outlined"
                                    className={classes.txtInput}
                                >
                                    <option value="true">True</option>
                                    <option value="false">False</option>
                                </TextField>
                            </Grid>
                            <Grid item xs={12} style={{ marginBottom: '16px' }}>
                                <Typography gutterBottom variant="subtitle1">
                                    Choose Published
                                </Typography>
                                <TextField
                                    id="outlined-select-currency-native"
                                    name="published"
                                    select
                                    value={published} // Giá trị boolean
                                    onChange={handleChangePublished}
                                    SelectProps={{
                                        native: true,
                                    }}
                                    variant="outlined"
                                    className={classes.txtInput}
                                >
                                    <option value="true">True</option>
                                    <option value="false">False</option>
                                </TextField>
                            </Grid>

                            <Grid item xs={12} style={{ marginBottom: '16px' }}>
                                <Typography gutterBottom variant="subtitle1">
                                    Choose Tag
                                </Typography>
                                <div>
                                    {selectedTags.map((tagId) => {
                                        // Tìm tag trong mảng tags
                                        const foundTag = tags.find(tag => tag.tagId === tagId);
                                        // Nếu không tìm thấy, trả về null
                                        if (!foundTag) return null;

                                        // Nếu tìm thấy, hiển thị Chip với label là tagName của tag
                                        return (
                                            <Chip
                                                key={tagId}
                                                label={foundTag.tagName}
                                                onDelete={() => handleDeleteTag(tagId)}
                                                className={classes.tagChip}
                                            />
                                        );
                                    })}
                                </div>
                                <TextField
                                    id="outlined-select-currency-native"
                                    name="Tag"
                                    select
                                    value={''} // Để trống giá trị value
                                    onChange={handleChangeTagId}
                                    SelectProps={{
                                        native: true,
                                    }}
                                    variant="outlined"
                                    className={classes.txtInput}
                                    multiple
                                >
                                    <option value="no-tag">Choose Tag</option>
                                    {tags.length > 0 && tags.map((option) => (
                                        <option key={option.tagId} value={option.tagId}>
                                            {option.tagName}
                                        </option>
                                    ))}
                                </TextField>
                            </Grid>





                            <Grid item xs={12} style={{ marginBottom: '16px' }}>
                                <Typography gutterBottom variant="subtitle1">
                                    Choose Category
                                </Typography>
                                <TextField
                                    id="outlined-select-currency-native"
                                    name="Category"
                                    select
                                    value={categoryId}
                                    onChange={handleChangeCategoryId}
                                    SelectProps={{
                                        native: true,
                                    }}
                                    variant="outlined"
                                    className={classes.txtInput}
                                >
                                    <option value="no-category">Choose Category</option>
                                    {categories.length > 0 && categories.map((option) => (
                                        <option key={option.categoryId} value={option.categoryId}>
                                            {option.categoryName}
                                        </option>
                                    ))}
                                </TextField>
                            </Grid>



                            <Grid item xs={12} style={{ marginBottom: '16px' }}>
                                <Typography gutterBottom variant="subtitle1">
                                    Choose CreatedBy
                                </Typography>
                                <TextField
                                    id="outlined-select-currency-native"
                                    name="createdBy"
                                    select
                                    value={createdBy}
                                    onChange={handleChangeCreatedBy}
                                    SelectProps={{
                                        native: true,
                                    }}
                                    variant="outlined"
                                    className={classes.txtInput}
                                >
                                    <option value="no-one">Choose StaffAccount</option>
                                    {staffAccounts.length > 0 && staffAccounts.map((option) => (
                                        <option key={option.staffAccountId} value={option.staffAccountId}>
                                            {`${option.firstName} ${option.lastName}`}
                                        </option>
                                    ))}
                                </TextField>
                            </Grid>
                            <Grid item xs={12} style={{ marginBottom: '16px' }}>
                                <Typography gutterBottom variant="subtitle1">
                                    Choose UpdatedBy
                                </Typography>
                                <TextField
                                    id="outlined-select-currency-native"
                                    name="updatedBy"
                                    select
                                    value={updatedBy}
                                    onChange={handleChangeUpdatedBy}
                                    SelectProps={{
                                        native: true,
                                    }}
                                    variant="outlined"
                                    className={classes.txtInput}
                                >
                                    <option value="no-one">Choose StaffAccount</option>
                                    {staffAccounts.length > 0 && staffAccounts.map((option2) => (
                                        <option key={option2.staffAccountId} value={option2.staffAccountId}>
                                            {`${option2.firstName} ${option2.lastName}`}
                                        </option>
                                    ))}
                                </TextField>
                            </Grid>

                            <Grid item xs={12}>
                                <Button type="button" onClick={EditProduct} fullWidth variant="contained" color="primary"
                                    className={classes.submit} >
                                    Update product
                                </Button>
                            </Grid>
                        </Grid>
                    </Paper>
                </Grid>
            </Grid>
        </div>
    )
}