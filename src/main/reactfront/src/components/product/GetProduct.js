import { useContext, useState } from "react";
import { UserContext } from "../user/UserContext";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const GetProduct = ({ product,setProduct }) => {
  const { user } = useContext(UserContext);
  const navigation = useNavigate();

  console.log(product);

  const year = product.manuDate.substring(0, 4);
  const month = product.manuDate.substring(4, 6);
  const day = product.manuDate.substring(6, 8);

  const formattedManufactureDate = `${year}년 ${month}월 ${day}일`;

  const [quantity, setQuantity] = useState(1);

  const handleQuantityChange = (e) => {
    const newQuantity = parseInt(e.target.value, 10);
    setQuantity(newQuantity);
  };

  const handlePurchase = () => {
    if (quantity > product.prodCount) {
      alert("재고가 부족합니다. 수량을 확인해주세요.");
    }
    axios
    .get(`/json/cart/${user.userId}/${product.prodNo}/${quantity}`)
    .then((response)=>{
      console.log(response);
      navigation('/purchase/shoppingCart');
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
    
  };

  const handleEdit = (prodNo) => {
    axios
    .get(`/product/json/getProduct/${prodNo}`)
      .then((response) => {
        console.log(response);
        setProduct(response.data);
        navigation('/product/updateProduct');
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }

  const handleBasket = () => {
    console.log(user)
    axios
    .get(`/json/cart/${user.userId}/${product.prodNo}/${quantity}`)
    .ther((response)=>{
      console.log(response);
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
  }
  return (
    <section className="py-5">
      <div className="container">
        <div className="row gx-5">
          <aside className="col-lg-6">
            <div className="border rounded-4 mb-3 d-flex justify-content-center">
              <img
                style={{
                  maxWidth: "100%",
                  maxHeight: "100vh",
                  margin: "auto",
                }}
                className="rounded-4 fit"
                src={`/images/uploadFiles/${product.fileName}`}
                alt="Product"
              />
            </div>
          </aside>
          <main className="col-lg-6">
            <div className="ps-lg-3">
              <h4 className="title text-dark">{product.prodName}</h4>
              <div className="d-flex flex-row my-3">
                <span className="text-muted">
                  <i className="fas fa-shopping-basket fa-sm mx-1"></i>
                  {product.prodCount}
                </span>
                <span className="text-success ms-2">In stock</span>
              </div>

              <div className="mb-3">
                <span className="h5">{product.price}원</span>
                <span className="text-muted">/개</span>
              </div>

              <p>{product.prodDetail}</p>

              <div className="row">
                <dt className="col-3">상품번호:</dt>
                <dd className="col-9">{product.prodNo}</dd>

                <dt className="col-3">제조일자:</dt>
                <dd className="col-9">{formattedManufactureDate}</dd>
              </div>

              <hr />

              <div className="row mb-4">
                <div className="col-md-4 col-6">
                  <label className="mb-2">수량</label>
                  <input
                    type="number"
                    id="prodCount"
                    value={quantity}
                    onChange={handleQuantityChange}
                  />
                </div>
                {/* ... Other input elements */}
              </div>
              <div>
                {user.role === "admin" ? (
                  <button
                    onClick={() => handleEdit(product.prodNo)}
                    className="btn mr-4 btn-warning shadow-0"
                  >
                    수정
                  </button>
                ) : (
                  <div>
                    <button
                      onClick={handlePurchase}
                      className="btn mr-4 btn-warning shadow-0"
                    >
                      구매
                    </button>
                    <button
                    onClick={handleBasket} 
                    className="btn btn-primary shadow-0">
                      장바구니
                    </button>
                  </div>
                )}
              </div>
            </div>
          </main>
        </div>
      </div>
    </section>
  );
};

export default GetProduct;
