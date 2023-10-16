import axios from "axios";
import GetUser from "./GetUser";
import { useState } from "react";

function UpdateUserView({ user }) {
  const [showGetUser, setShowGetUser] = useState(false);

  const handleGetClick = () => {
    const phone1 = document.getElementById("phone1").value;
    const phone2 = document.querySelector("input[name='phone2']").value;
    const phone3 = document.querySelector("input[name='phone3']").value;
    const phone = `${phone1}-${phone2}-${phone3}`;
    const updatedUserInfo = {
        userId: user.userId,
        userName: document.querySelector('input[name="userName"]').value,
        email: document.querySelector('input[name="email"]').value,
        phone1: phone1,
        phone2: phone2,
        phone3: phone3,
        phone:phone,
        addr: document.querySelector('input[name="addr"]').value
  
      };
      console.log(updatedUserInfo)
      axios.post("/user/json/updateUser", updatedUserInfo)
      .then(response => {
        sessionStorage.setItem("user", JSON.stringify(response.data));
        console.log("사용자 정보가 성공적으로 업데이트되었습니다.");
        console.log(JSON.parse(sessionStorage.getItem('user')))
        setShowGetUser(true);
      })
      .catch(error => {
        console.error("사용자 정보 업데이트 중 오류가 발생했습니다.", error);
      });
  };

  return (
    <>
    {showGetUser ? <GetUser user={user}/> : (
      <section style={{ backgroundColor: "#f4f5f7", width: 800, height: 550 }}>
        <form name="detailForm">
          <div className="container py-3 w-800" style={{ height: 400 }}>
            <div className="row d-flex justify-content-center align-items-center h-100 text-dark">
              <div className="col col-lg-6 mb-4 mb-lg-0 w-100 h-80">
                <div
                  className="card mb-3 h-100"
                  style={{ borderRadius: ".5rem", height: 430 }}
                >
                  <div className="row g-0">
                    <div
                      className="col-md-4 gradient-custom text-center text-white d-flex align-items-center flex-column p-4 justify-content-center"
                      style={{
                        borderTopLeftRadius: ".5rem",
                        borderBottomLeftRadius: ".5rem",
                      }}
                    >
                      <img
                        src="https://opgg-com-image.akamaized.net/attach/images/20200513062114.1056898.jpg"
                        alt="엄"
                        style={{ width: 100, height: 100, borderRadius: "50%" }}
                      />
                      <span className="text-dark ">
                        <img
                          src="/images/ct_icon_red.gif"
                          width="3"
                          height="3"
                          align="absmiddle"
                        />
                        이름{" "}
                        <input
                          className="w-50"
                          type="text"
                          name="userName"
                          defaultValue={user.userName}
                        />
                      </span>
                      <p className="text-dark">권한 {user.role}</p>
                    </div>
                    <div className="col-md-8">
                      <div className="card-body p-4">
                        <h6>회원정보수정</h6>
                        <hr className="mt-0 mb-4" />
                        <div className="row pt-1">
                          <div className="col-6 mb-3">
                            <h6>
                              <img
                                src="/images/ct_icon_red.gif"
                                width="3"
                                height="3"
                                align="absmiddle"
                              />
                              이메일
                            </h6>
                            <input
                              type="text"
                              name="email"
                              defaultValue={user.email}
                            />
                          </div>
                          <div className="col-6 mb-3">
                            <h6>전화번호</h6>
                            <div className="d-flex justify-content-between">
                              <select
                                name="phone1"
                                className="ct_input_g"
                                style={{ width: "50px", height: "25px" }}
                                onChange={() =>
                                  document.detailForm.phone2.focus()
                                }
                              >
                                <option
                                  value="010"
                                  selected={user.phone1 === "010"}
                                >
                                  010
                                </option>
                                <option
                                  value="011"
                                  selected={user.phone1 === "011"}
                                >
                                  011
                                </option>
                                <option
                                  value="016"
                                  selected={user.phone1 === "016"}
                                >
                                  016
                                </option>
                                <option
                                  value="018"
                                  selected={user.phone1 === "018"}
                                >
                                  018
                                </option>
                                <option
                                  value="019"
                                  selected={user.phone1 === "019"}
                                >
                                  019
                                </option>
                              </select>
                              <input
                                type="text"
                                name="phone2"
                                defaultValue={user.phone2}
                                style={{ width: "70px" }}
                              />
                              <input
                                type="text"
                                name="phone3"
                                defaultValue={user.phone3}
                                style={{ width: "70px" }}
                              />
                            </div>
                          </div>
                          <div className="col-6 mb-3">
                            <h6>주소</h6>
                            <input type="text" name="addr" defaultValue={user.addr} />
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="d-flex justify-content-center align-items-center gap-5">
            <button type="button" className="btn btn-outline-primary px-6" onClick={handleGetClick}>
              확인
            </button>
          </div>
        </form>
      </section>
      )}
    </>
  );
}

export default UpdateUserView;
