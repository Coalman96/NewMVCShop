import { useContext, useEffect, useState } from "react";
import { UserContext } from "../user/UserContext";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Shoppingcart = () => {
  const { user } = useContext(UserContext);
  const navigation = useNavigate();

  const [productData, setProductData] = useState([]);

  useEffect(() => {
    axios
      .get(`/json/cart/${user.userId}`)
      .then((response) => {
        console.log(response.data);
        setProductData(response.data);
      })
      .catch((error) => {
        console.error("데이터를 불러오는 중 오류 발생:", error);
      });
  }, [user.userId]);

  const handleDelete = (productId) => {
    axios
      .get(`/json/cartDelete/${user.userId}/${productId}`)
      .then((response) => {
        console.log(response);
        setProductData(response.data);
      })
      .catch((error) => {
        console.error("데이터를 삭제하는 중 오류 발생:", error);
      });
    };

  return (
    <section className="h-100 h-custom" style={{ backgroundColor: "#eee" }}>
      <div className="container h-100 py-5">
        <div className="row justify-content-center align-items-center h-100">
          <div className="col">
            <div
              className="card shopping-cart"
              style={{ borderRadius: "15px" }}
            >
              <div className="card-body text-black">
                <div className="row">
                  <div className="col-lg-7 px-5 py-4">
                    <h3 className="mb-5 pt-2 text-center fw-bold text-uppercase">
                      {user.userName}님의 장바구니
                    </h3>

                    {/* <div className="d-flex align-items-center mb-5">
                      <div className="flex-shrink-0">
                        <img
                          src="https://mdbcdn.b-cdn.net/img/Photos/Horizontal/E-commerce/Products/13.webp"
                          fluid
                          style={{ width: "150px" }}
                          alt="Product 1"
                        />
                      </div>

                      <div className="flex-grow-1 ms-3">
                        <a href="#!" className="float-end text-black">
                        <i class="bi bi-trash"></i>
                        </a>
                        <h5 className="text-primary">
                          Samsung Galaxy M11 64GB
                        </h5>
                        <h6 style={{ color: "#9e9e9e" }}>Color: white</h6>

                        <div className="d-flex align-items-center">
                          <p className="fw-bold mb-0 me-5 pe-3">222원</p>

                          <div className="def-number-input number-input">
                            <input
                              className="quantity fw-bold text-black"
                              min={0}
                              defaultValue={1}
                              type="number"
                            />
                          </div>
                        </div>
                      </div>
                    </div> */}
                    {productData.map((data) => (
                      <div key={data.product.prodNo} className="d-flex align-items-center mb-5">
                        <div className="flex-shrink-0">
                          <img src={`/images/uploadFiles/${data.product.fileName}`} fluid style={{ width: "150px" }} alt={`Product ${data.product.prodName}`} />
                        </div>

                        <div className="flex-grow-1 ms-3">
                          <a href="#!" className="float-end text-black" onClick={()=>handleDelete(data.id)}>
                            <i className="bi bi-trash"></i>
                          </a>
                          <h5 className="text-primary">상품명 : {data.product.prodName}</h5>
                          <h6 style={{ color: "#9e9e9e" }}>상품설명 : {data.product.prodDetail}</h6>

                          <div className="d-flex align-items-center">
                            <p className="fw-bold mb-0 me-5 pe-3">가격 : {data.product.price}원</p>

                            <div className="def-number-input number-input">
                              <input className="quantity fw-bold text-black" min={1} defaultValue={data.count} type="number" />
                            </div>
                          </div>
                        </div>
                      </div>
                    ))}

                    {/* Repeat similar structure for other products */}

                    <hr
                      className="mb-4"
                      style={{
                        height: "2px",
                        backgroundColor: "#1266f1",
                        opacity: 1,
                      }}
                    />
{/* 
                    <div className="d-flex justify-content-between px-x">
                      <p className="fw-bold">할인:</p>
                      <p className="fw-bold">추가예정</p>
                    </div> */}
                    <div
                      className="d-flex justify-content-between p-2 mb-2"
                      style={{ backgroundColor: "#e1f5fe" }}
                    >
                      <h5 className="fw-bold mb-0">총 가격:</h5>
                      <h5 className="fw-bold mb-0">{productData.reduce((total, data) => total + data.product.price, 0)}원</h5>
                    </div>
                  </div>
                  <div className="col-lg-5 px-5 py-4">
                    <h3 className="mb-5 pt-2 text-center fw-bold text-uppercase">
                      결제정보 입력
                    </h3>

                    <form className="mb-5">
                      {/* Input fields for card information */}
                    </form>

                    <p className="mb-5">
                      Lorem ipsum dolor sit amet consectetur, adipisicing elit{" "}
                      
                    </p>

                    <button className="btn btn-primary btn-block btn-lg">
                      구매하기
                    </button>

                    <h5
                      className="fw-bold mb-5"
                      style={{ position: "absolute", bottom: "0" }}
                    >
                      <button
                        onClick={() => window.history.back()}
                        type="button"
                        className="btn btn-primary"
                      >
                        뒤로가기
                      </button>
                    </h5>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Shoppingcart;
