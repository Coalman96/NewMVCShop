import Template from "./components/templates/Template";
import { Route, Routes } from "react-router-dom";
import { UserProvider } from "./components/user/UserContext";
import AddUserView from "./components/user/AddUserView";
import Landing from "./components/landing/Landing";
import ListUser from "./components/user/ListUser";
import ListProduct from "./components/product/ListProduct";
import AddProductView from "./components/product/AddProductView";
import GetProduct from "./components/product/GetProduct";
import { useState } from "react";
import UpdateProductView from "./components/product/UpdateProductView";
import Shoppingcart from "./components/purchase/Shoppingcart";
import CheckoutForm from "./components/purchase/CheckoutForm";

function App() {

  // product 데이터를 상태로 관리
  const [product, setProduct] = useState(null);

  // ListProduct 컴포넌트에서 product를 설정하는 함수
  const handleSetProduct = (productData) => {
    setProduct(productData);
  };

  return (
    <UserProvider>
      <Template>
        <Routes>
          <Route path="/" element={<Landing />} />
          <Route path="/user/addUser" element={<AddUserView/>} />
          <Route path="/user/listUser" element={<ListUser/>} />
          <Route
            path="/product/listProduct"
            element={<ListProduct setProduct={handleSetProduct} />}
          />
          <Route path="/product/addProductView" element={<AddProductView/>} />
          <Route path="/product/getProduct" element={<GetProduct product={product} setProduct={handleSetProduct}/>} />
          <Route path="/product/updateProduct" element={<UpdateProductView product={product}/>} />
          <Route path="/purchase/shoppingCart" element={<Shoppingcart/>} />
          <Route path="/purchase/checkout" element={<CheckoutForm/>}/>
        </Routes>
      </Template>
    </UserProvider>
  );
}

export default App;
