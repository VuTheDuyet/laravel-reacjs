import React, { useEffect, useState } from 'react'
import { makeStyles } from '@mui/styles';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Alert from '@mui/material/Alert';
import { useNavigate } from 'react-router-dom';
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
export default function ProductCreate() {
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
    const [published, setPublished] = useState(true)
    const [createdBy, setCreatedBy] = useState("no-one");
    const [updatedBy, setUpdatedBy] = useState("no-one");
    const [isThumbnail, setIsThumbnail] = useState(false)
    const [imagePath, setImagePath] = useState("");
    const [placeholder, setPlaceholder] = useState("");
    const [staffAccounts, setStaffAccounts] = useState([]);
    const [tags, setTags] = useState([]);
    const [categoryId, setCategoryId] = useState("no-category");
    const [categories, setCategories] = useState([]);
    const [selectedTags, setSelectedTags] = useState([]);
    useEffect(() => {
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

    }, []);

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


    const handleChangeTagId = (event) => {
        const value = event.target.value;
        setSelectedTags(prevState => {
            if (prevState.includes(value)) {
                return prevState.filter(tag => tag !== value);
            } else {
                return [...prevState, value];
            }
        });
    };

    const handleDeleteTag = (tagIdToDelete) => {
        setSelectedTags((prevTags) => prevTags.filter((tagId) => tagId !== tagIdToDelete));
    };


    const handleChangeCategoryId = (event) => {
        setCategoryId(event.target.value)
    };

    const addProduct = (event) => {
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

            productservice.POST_ADD_PRODUCT(`products`, product).then(item => {
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


                    let product_tags = selectedTags.map(tagId => ({
                        tag: { tagId },
                        product: { productId }
                    }));


                    let product_category = {
                        product: {
                            productId: productId
                        },
                        category: {
                            categoryId: categoryId
                        }
                    };

                    galleryservice.POST_ADD_GALLERY(`galleries`, gallery).then(item1 => {
                        console.log(item1.data)
                    }).catch(error => {
                        console.error("Error adding gallery:", error);
                    });
                    product_tags.forEach(product_tag => {
                        productservice.POST_ADD_PRODUCT_TAG(`productTags`, product_tag)
                            .then(item => {
                                console.log(item.data)
                            })
                            .catch(error => {
                                console.error("Error adding product_tag:", error);
                            });
                    });
                    productservice.POST_ADD_PRODUCT_CATEGORY(`productCategories`, product_category).then(item => {
                        console.log(item.data)
                    }).catch(error => {
                        console.error("Error adding product_category:", error);
                    });

                    setCheckAdd(true);
                }
            }).catch(error => {
                console.error("Error adding product:", error);
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
        fileNames= fileName[0].name;
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
                <Grid item xs={5}>
                    <Paper className={classes.paper}>
                        <Typography className={classes.title} variant="h4">
                            Add Product
                        </Typography>
                        <Grid item xs={12} sm container>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Product Name
                                </Typography>
                                <TextField id="ProductName" onChange={handleChangeProductName} name="ProductName" label="ProductName"
                                    variant="outlined" className={classes.txtInput} size="small" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Slug
                                </Typography>
                                <TextField id="Slug" onChange={handleChangeSlug} name="Slug" label="Slug"
                                    variant="outlined" className={classes.txtInput} size="small" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Sku
                                </Typography>
                                <TextField id="Sku" onChange={handleChangeSku} name="Sku" label="Sku"
                                    variant="outlined" className={classes.txtInput} size="small" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Sale Price
                                </Typography>
                                <TextField id="SalePrice" onChange={handleChangeSalePrice} name="SalePrice" label="SalePrice"
                                    variant="outlined" className={classes.txtInput} size="small" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Compare Price
                                </Typography>
                                <TextField id="ComparePrice" onChange={handleChangeComparePrice} name="ComparePrice" label="ComparePrice > SalePrice"
                                    variant="outlined" className={classes.txtInput} size="small" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Buying Price
                                </Typography>
                                <TextField id="BuyingPrice" onChange={handleChangeBuyingPrice} name="BuyingPrice" label="BuyingPrice"
                                    variant="outlined" className={classes.txtInput} size="small" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Quantity
                                </Typography>
                                <TextField id="Quantity" onChange={handleChangeQuantity} name="Quantity" label="Quantity"
                                    variant="outlined" className={classes.txtInput} size="small" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Short Description
                                </Typography>
                                <TextField id="outlined-multiline-static" onChange={handleChangeShortDescription} label="ShortDescription"
                                    name="ShortDescription" className={classes.txtInput} multiline rows={4} variant="outlined" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Product Description
                                </Typography>
                                <TextField id="outlined-multiline-static" onChange={handleChangeProductDescription} label="ProductDescription"
                                    name="ProductDescription" className={classes.txtInput} multiline rows={4} variant="outlined" />
                            </Grid>
                            <Grid item xs={12}>
                                <Typography gutterBottom variant="subtitle1">
                                    Note
                                </Typography>
                                <TextField id="outlined-multiline-static" onChange={handleChangeNote} label="Note"
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
                                    value={isThumbnail} // Giá trị boolean
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
                                    {selectedTags.map((tagId) => (
                                        <Chip
                                            key={tagId}
                                            label={tags.find(tag => tag.tagId === tagId).tagName}
                                            onDelete={() => handleDeleteTag(tagId)}
                                            className={classes.tagChip}
                                        />
                                    ))}
                                </div>
                                <TextField
                                    id="outlined-select-currency-native"
                                    name="Tag"
                                    select
                                    value="" // Giá trị này không được sử dụng, chỉ để cho TextField hoạt động
                                    onChange={handleChangeTagId}
                                    SelectProps={{
                                        native: true,
                                    }}
                                    variant="outlined"
                                    className={classes.txtInput}
                                    multiple
                                >
                                    <option value="">Choose Tag</option>
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
                                <Button type="button" onClick={addProduct} fullWidth variant="contained" color="primary"
                                    className={classes.submit} >
                                    Add product
                                </Button>
                            </Grid>
                        </Grid>
                    </Paper>
                </Grid>
            </Grid>
        </div>
    )
}
