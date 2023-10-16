import Banner from "./Banner";
import FeatureProduct from "./FeatureProduct";
import { Link } from "react-router-dom";

function Landing() {
  return (
    <>
      <Banner />
      <h2 className="text-muted text-center mt-4 mb-3">신상품</h2>
      <div className="container pb-5 px-lg-5">
        <div className="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4 px-md-5">
          {Array.from({ length: 6 }, (i) => {
            return <FeatureProduct key={i} />;
          })}
        </div>
      </div>
      <div className="d-flex flex-column bg-white py-4">
        <h5 className="text-center mb-3">Follow us on</h5>
        <div className="d-flex justify-content-center">
          <a href="!#" className="me-3">
            <i className="bi bi-facebook" style={{ fontSize: "2em" }}></i>
          </a>
          <a href="!#">
            <i className="bi bi-instagram" style={{ fontSize: "2em" }}></i>
          </a>
          <a href="!#" className="ms-3">
            <i className="bi bi-twitter" style={{ fontSize: "2em" }}></i>
          </a>
        </div>
      </div>
    </>
  );
}

export default Landing;
