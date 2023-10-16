import axios from "axios";
import React, { useContext, useEffect, useState } from "react";
import { UserContext } from "../user/UserContext";
import { useNavigate } from "react-router-dom";

const ListProduct = ({setProduct}) => {
  const { user } = useContext(UserContext);
  const navigation = useNavigate();

  const [productData, setProductData] = useState([]);
  const [pageUnit, setPageUnit] = useState(1);
  const [searchType, setSearchType] = useState("0"); // 초기값은 "회원ID"
  const [searchKeyword, setSearchKeyword] = useState("");

  const infinityScrollData = () => {
    const requestData = {
      pageUnit: pageUnit,
      searchType: searchType,
      searchKeyword: searchKeyword,
    };
    axios
      .post("/product/json/getProductList", requestData, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        const newData = response.data.data;
        setProductData((prevData) => [...prevData, ...newData]);
        setPageUnit((prevPage) => prevPage + 1);
      })
      .catch((error) => {
        console.error("서버에서 정보를 못 가져옴", error);
      });
  };

  //실시간으로 검색조건 변경감지
  const handleSearchTypeChange = (e) => {
    setSearchType(e.target.value);
  };

  const handleSearchKeywordChange = (e) => {
    setSearchKeyword(e.target.value);
  };

  //검색 이벤트 추가
  const handleSearch = () => {
    setPageUnit(1); // 검색 시 페이지 단위를 1로 초기화
    setUserData([]); // 검색 시 기존 데이터 초기화
    infinityScrollData(); // 검색 실행
  };

  //페이지 로드 시 초기데이터 불러옴
  useEffect(() => {
    infinityScrollData();
  }, []);

  useEffect(() => {
    // 스크롤 이벤트를 감지하여 맨 아래로 갔을 때 loadMoreData 함수를 호출합니다.
    const handleScroll = () => {
      const { scrollTop, scrollHeight, clientHeight } =
        document.documentElement;
      if (scrollTop + clientHeight >= scrollHeight - 10) {
        infinityScrollData();
      }
    };

    window.addEventListener("scroll", handleScroll);
    console.log(productData);
    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, [productData]); // userData가 업데이트될 때마다 스크롤 이벤트를 감지

  const handleGetProduct = (prodNo) => {
    axios
      .get(`/product/json/getProduct/${prodNo}`)
      .then((response) => {
        console.log(response);
        setProduct(response.data);
        navigation('/product/getProduct');
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  };

  return (
    <div style={{ width: "98%", marginLeft: "10px" }}>
      <section>
  <input type="hidden" name="menu" value="manage" />
  <div className="container my-5">
    <div className="d-flex justify-content-center row">
      <div className="col-md-10">
        {productData.map((product) => (
          <div key={product.prodNo} className="row p-2 bg-white border rounded mt-2">
            <div className="col-md-3 mt-1">
              <img
                className="img-fluid img-responsive rounded product-image"
                src={`/images/uploadFiles/${product.fileName}`}
                alt="Product"
              />
            </div>
            <div className="col-md-6 mt-1">
              <h5>{product.prodName}</h5>
              <div className="d-flex flex-row">

                {/* 구매리뷰 추가 시 별점 구현 예정 
                <div className="ratings mr-2">
                  <i className="fa fa-star"></i>
                  <i className="fa fa-star"></i>
                  <i className="fa fa-star"></i>
                  <i className="fa fa-star"></i>
                </div> */}
                <span>남은재고: {product.prodCount}개</span>
              </div>
              <p className="text-justify text-truncate para mb-0">{product.prodDetail}</p>
            </div>
            <div className="align-items-center align-content-center col-md-3 border-left mt-1">
              <div className="d-flex flex-row align-items-center">
                <h4 className="mr-1">{product.price}</h4>
                <span className="strike-text">원</span>
              </div>
              <h6 className="text-success">무료배송</h6>
              <div className="d-flex flex-column mt-4">
                <button
                onClick={() => handleGetProduct(product.prodNo)} 
                className="btn btn-primary btn-sm" 
                type="button">
                  자세히보기
                </button>
                <button className="btn btn-outline-primary btn-sm mt-2" type="button">
                  장바구니에 삽입
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  </div>
</section>

    </div>
  );
};

export default ListProduct;
