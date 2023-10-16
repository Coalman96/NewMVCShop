import axios from "axios";
import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { useNavigate } from "react-router-dom";

const UpdateProductView = ({ product }) => {
  console.log("아씨발")
  console.log(product);
  const formattedManuDate = `${product.manuDate.substr(0, 4)}-${product.manuDate.substr(4, 2)}-${product.manuDate.substr(6, 2)}`;
  const [prodName, setProdName] = useState("");
  const [prodDetail, setProdDetail] = useState("");
  const [manuDate, setManuDate] = useState(null);
  const [price, setPrice] = useState("");
  const [prodCount, setProdCount] = useState(1);
  const [file, setFile] = useState("");
  const navigation = useNavigate();

  useEffect(() => {
    if (product) {
      setProdName(product.prodName);
      setProdDetail(product.prodDetail);
      setManuDate(product.manuDate);
      setPrice(product.price);
      setProdCount(product.prodCount);
      setFile(product.fileName);
    }
  }, []);

 


  const handleUpdateProductClick = () => {
    const formData = new FormData();
    const productData = {
      prodNo:product.prodNo,
      prodName: prodName,
      prodDetail: prodDetail,
      manuDate: manuDate,
      price: price,
      prodCount: prodCount,
      fileName: file,
    };

    // Multiple files
    // const fileInput = document.getElementById("file");
    // const files = fileInput.files;
    // for (let i = 0; i < files.length; i++) {
    //   formData.append("file", files[i]);
    // }

    // formData.append("productData", new Blob([JSON.stringify(productData)], { type: "application/json" }));

    axios
      .post("/product/json/updateProduct", productData, {
        headers: {
          // "Content-Type": "multipart/form-data"
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        console.log("상품 업데이트 성공", response);
        navigation("/product/listProduct");
      })
      .catch((error) => {
        console.error("상품 업데이트 실패", error);
      });
  };

  return (
    <div className="container">
      <h1 className="text-center mt-5">상품 수정</h1>
      <div className="row mt-3">
        <div className="col-md-6">
          <div className="mb-3">
            <label htmlFor="prodName" className="form-label">
              상품명
            </label>
            <input
              type="text"
              className="form-control"
              id="prodName"
              maxLength="20"
              value={prodName}
              onChange={(e) => setProdName(e.target.value)}
            />
          </div>

          <div className="mb-3">
            <label htmlFor="prodDetail" className="form-label">
              상품 상세 정보
            </label>
            <input
              type="text"
              className="form-control"
              id="prodDetail"
              value={prodDetail}
              onChange={(e) => setProdDetail(e.target.value)}
            />
          </div>

          <div className="mb-3">
            <label htmlFor="manuDate" className="form-label">
              제조일자
            </label>
            <DatePicker
              onChange={(date) => setManuDate(date)} // setManuDate는 state를 업데이트하는 함수입니다.
              selected={manuDate ? new Date(formattedManuDate) : null} // selected prop로 설정
              className="form-control"
              id="manuDate"
              dateFormat="yyyy-MM-dd"
            />
          </div>

          <div className="mb-3">
            <label htmlFor="price" className="form-label">
              가격
            </label>
            <div className="input-group">
              <input
                type="text"
                className="form-control"
                id="price"
                maxLength="10"
                value={price}
                onChange={(e) => setPrice(e.target.value)}
              />
              &nbsp;원
            </div>
          </div>

          <div className="mb-3">
            <label htmlFor="prodCount" className="form-label">
              수량
            </label>
            <div className="input-group">
              <input
                type="text"
                className="form-control"
                id="prodCount"
                maxLength="10"
                value={prodCount}
                onChange={(e) => setProdCount(e.target.value)}
              />
              &nbsp;개
            </div>
          </div>
        </div>

        <div className="col-md-6 d-flex">
          <div className="w-100 row flex-column">
            <label htmlFor="file" className="col-md-12 col-form-label mb-1">
              상품 이미지
            </label>
            <div className="col-md-12 mt-auto">
              <input
                type="text"
                className="form-control"
                id="file"
                maxLength="13"
                value={file}
                onChange={(e) => setFile(e.target.value)}
                // multiple
              />
            </div>
          </div>
        </div>
      </div>

      <div className="row my-3 py-4">
        <div className="text-center d-flex justify-content-center gap-5">
          <button
            type="button"
            className="btn btn-primary mr-2"
            onClick={handleUpdateProductClick}
          >
            수정
          </button>
          <button
          onClick={() => window.history.back()} 
          type="button" 
          className="btn btn-primary">
            취소
          </button>
        </div>
      </div>
    </div>
  );
};

export default UpdateProductView;
