import axios from "axios";
import GetUser from "../user/GetUser";
import { useContext } from "react";
import { UserContext } from "../user/UserContext";
import { useNavigate } from "react-router-dom";

//메뉴버튼
function MenuButton({ name, href, toggle, target, onClick }) {
  return (
    <li className="nav-item" data-bs-toggle={toggle} data-bs-target={target}>
      <a className="nav-link" href={href} onClick={onClick}>
        {name}
      </a>
    </li>
  );
}

function FormModal() {
  
  const handleAddUserClick = () => {
    window.location = "/user/addUser";
  };

  const handleCheckUserId = (event) => {
    event.preventDefault()
    const userId = document.querySelector("#userId").value;
    const password = document.querySelector("#password").value;
  
    if (!userId) {
      alert("아이디를 입력해주세요");
      return;
    } else if (!password) {
      alert("비밀번호를 입력해주세요");
      return;
    }
    const data = {
      userId: userId,
      password: password
    };
  
    axios.post("/user/json/login", data, {
      headers: {
        'Content-Type': 'application/json'
      }
    })
    .then((response) => {
      if (response.data.checkUser) {
        sessionStorage.setItem("user", JSON.stringify(response.data.user));
        window.location = "/";
      } else {
        alert("아이디 및 비밀번호가 틀렸습니다");
      }
    });
  };

  return (
      <div
        className="modal fade"
        id="exampleModal"
        tabIndex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLabel">
                환영합니다
              </h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              <div className="mb-3">
                <label htmlFor="userId" className="form-label">
                  아이디
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="userId"
                  name="userId"
                />
              </div>
              <div className="mb-3">
                <label htmlFor="password" className="form-label">
                  비밀번호
                </label>
                <input
                  type="password"
                  className="form-control"
                  id="password"
                  name="password"
                />
              </div>
            </div>
            <div className="modal-footer">
              <button
                type="submit"
                className="btn btn-secondary"
                onClick={handleCheckUserId}
              >
                로그인
              </button>
              <button
                type="button"
                className="btn btn-primary"
                onClick={handleAddUserClick}
              >
                회원가입
              </button>
            </div>
          </div>
        </div>
      </div>
  );
}

function Header() {

  const navigation = useNavigate();

  const { user } = useContext(UserContext);

  const handleLogoutClick = () => {
    //세션 제거
    sessionStorage.clear();
    window.location = "/user/logout";
  };

  const handleLinkClick = (menu) => {
    switch (menu) {
      case "판매상품등록":
        navigation("/product/addProductView");
        break;
      case "회원정보조회":
        navigation("/user/listUser");
        break;
      case "판매상품관리":
        navigation("/product/listProduct?menu=manage");
        break;
      case "상품검색":
        navigation("/product/listProduct");
        break;
      case "구매이력조회":
        navigation("/purchase/listPurchase?menu=search");
        break;
      case "배송관리":
        navigation("/purchase/listPurchase?menu=manage");
        break;
      default:
        break;
    } //enf of switch
  };

  return (
    <>
      {/* <!-- Modal --> */}
      <FormModal/>
      {/* <!-- Jumbotron --> */}
      <div className="text-center bg-white border-bottom">
        <div className="container">
          <div className="row gy-3 d-flex justify-content-between">
            {/* <!-- Logo --> */}
            <div className="logo col-lg-1 col-sm-4 col-4 d-flex text-left">
              <img
                src="https://avatars.githubusercontent.com/u/96984831?v=4"
                height="50"
                width="50"
              />
            </div>
            {/* <!-- Logo--> */}

            {/* <!-- Navbar (중앙 정렬 추가) --> */}
            <div className="col-lg-7 col-sm-4 col-4">
              <nav className="navbar navbar-expand-lg">
                {/* <!-- Toggle button --> */}
                <button
                  className="navbar-toggler mx-auto"
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target="#navbarTogglerDemo02"
                  aria-controls="navbarTogglerDemo02"
                  aria-expanded="false"
                  aria-label="Toggle navigation"
                >
                  <span className="navbar-toggler-icon"></span>
                </button>
                {/* <!-- Collapsible wrapper --> */}
                <div
                  className="collapse navbar-collapse"
                  id="navbarTogglerDemo02"
                >
                  {/* <!-- menu links --> */}
                  <ul className="navbar-nav me-auto mb-2 mb-lg-0 mx-auto">
                    <MenuButton
                      name="상품검색"
                      onClick={() => {
                        handleLinkClick("상품검색");
                      }}
                    />
                    {user !== "guest"&&(
                      <MenuButton
                        name="개인정보조회"
                        toggle="modal"
                        target="#myModal"
                      />
                    )}
                    {user.role === "admin" && (
                      <MenuButton
                        name="회원정보조회"
                        onClick={() => {
                          handleLinkClick("회원정보조회");
                        }}
                      />
                    )}
                    {user.role === "admin" && (
                      <MenuButton
                        name="판매상품등록"
                        onClick={() => {
                          handleLinkClick("판매상품등록");
                        }}
                      />
                    )}
                    {user.role === "admin" && (
                      <MenuButton
                        name="판매상품관리"
                        onClick={() => {
                          handleLinkClick("판매상품관리");
                        }}
                      />
                    )}
                    {user.role === "admin" && (
                      <MenuButton
                        name="배송관리"
                        onClick={() => {
                          handleLinkClick("배송관리");
                        }}
                      />
                    )}
                    {user.role === "user" && (
                      <MenuButton
                        name="구매이력조회"
                        onClick={() => {
                          handleLinkClick("구매이력조회");
                        }}
                      />
                    )}
                  </ul>
                  {/* <!-- menu links --> */}
                </div>
              </nav>
            </div>
            {/* <!-- Navbar (중앙 정렬 추가) --> */}

            {/* <!-- Right elements --> */}
            <div className="d-flex order-lg-last col-lg-2 col-sm-4 col-4 text-end justify-content-end">
              <div className="d-flex flex-center align-items-center">
                {/* <!-- Button trigger modal --> */}
                {/* 로그인 버튼 */}
                {user === "guest" && (
                  <button
                    type="button"
                    className="btn btn-primary"
                    data-bs-toggle="modal"
                    data-bs-target="#exampleModal"
                  >
                    <i
                      className="bi bi-person-circle"
                      style={{ fontSize: 25 }}
                    ></i>
                  </button>
                )}
                {/* 로그아웃 버튼 */}
                {user !== "guest"&& (
                  <i
                    className="bi bi-door-open"
                    style={{ fontSize: 25, fontStyle: "normal" }}
                    onClick={handleLogoutClick}
                  >
                    Logout
                  </i>
                )}
              </div>
            </div>
            {/* <!-- Right elements --> */}
            <div
              className="modal fade"
              id="myModal"
              tabIndex="-1"
              aria-labelledby="exampleModalLabel"
              aria-hidden="true"
            >
              <div className="modal-dialog">
                <div className="modal-content">
                  {/* <!-- 모달 내용 표시 --> */}
                  {<GetUser user={user}/>}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      {/* <!-- Jumbotron --> */}
    </>
  );
}

export default Header;
