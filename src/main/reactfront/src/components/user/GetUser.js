import { useState } from "react";
import UpdateUserView from "./UpdateUserView";

function GetUser({ user }) {

  const [showUpdateUserView, setShowUpdateUserView] = useState(false);

  const handleUpdateClick = () => {

    setShowUpdateUserView(true);

  }

  function InputText({ subTitle, contents }) {
    return (
      <div className="col-6 mb-3">
        <h6>{subTitle}</h6>
        <p className="text-muted">{contents}</p>
      </div>
    );
  }

  return (
    <>
    {showUpdateUserView ? <UpdateUserView user={user}/> : (
      <section
        style={{
          backgroundColor: "#f4f5f7",
          width: 800,
          height: 550,
        }}
      >
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
                      style={{
                        width: 100,
                        height: 100,
                        borderRadius: "50%",
                      }}
                    />
                    <h5 className="text-dark">{user.userName}</h5>
                    <p className="text-dark">권한 {user.role}</p>
                  </div>
                  <div className="col-md-8">
                    <div className="card-body p-4">
                      <h6>{user.userId}님의 정보</h6>
                      <hr className="mt-0 mb-4" />
                      <div className="row pt-1">
                        <InputText subTitle={"이메일"} contents={user.email} />
                        <InputText
                          subTitle={"전화번호"}
                          contents={user.phone !== null ? user.phone : ""}
                        />
                        <InputText subTitle={"주소"} contents={user.addr} />
                        <InputText
                          subTitle={"가입일자"}
                          contents={user.regDate}
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="d-flex justify-content-center align-items-center gap-5">
          <button type="button" className="btn btn-outline-primary px-6" onClick={handleUpdateClick}>
            수정
          </button>
          {/* <!-- <button type="button" className="btn btn-outline-secondary px-6">확인</button> --> */}
        </div>
      </section>
      )}
    </>
  );
}

export default GetUser;
