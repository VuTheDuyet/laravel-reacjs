import Product from "../ProductAtHome";
import Blog from "./Blog";
import Category from "./Category";
import Slider from "./Slider";
import Testimonial from "./Testimonial";


function Home() {
    return ( 
        <>
            <Slider />
            <Category />
            <Product />
            <Testimonial />
            <Blog />
        </>
     );
}

export default Home;