import { Outlet } from "react-router-dom";
import { OverlayProvider } from "../../FatherContext/OverlayContext";
import { SidebarProvider } from "../../FatherContext/SidebarContext";
import DataOverlay from "../../pages/frontend/Home/DataOverlay";
import Modal from "../../pages/frontend/Home/Modal";
import Notification from "../../pages/frontend/Home/Notification";
import Header from "./Header";
import Footer from "./Footer";
import "../../assets/sass/app.scss"
function LayoutSite() {
    return (
        <>
            <OverlayProvider>
                <SidebarProvider>
                    <DataOverlay />
                    <Modal />
                    <Notification />
                    <Header />
                    <Outlet />
                    <Footer />
                </SidebarProvider>
            </OverlayProvider>
        </>
    );
}

export default LayoutSite;