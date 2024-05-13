import { BrowserRouter, Route, Routes } from "react-router-dom";
import RouterSite from "./router";
import LayoutSite from "./layouts/LayoutSite";
import LayoutAdmin from "./layouts/LayoutAdmin";
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<LayoutSite/>}>
          {RouterSite.RouterPublic.map(function(route, index) {
            const Page = route.component
            return <Route key={index} path={route.path} element={<Page/>} />
          })}
        </Route>

        <Route path='/admin' element={<LayoutAdmin/>}>
          {RouterSite.RouterPrivate.map(function(route, index) {
            const Page = route.component
            return <Route key={index} path={route.path} element={<Page/>} />
          })}
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;