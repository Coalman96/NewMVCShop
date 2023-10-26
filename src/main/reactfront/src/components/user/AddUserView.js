import { useEffect } from "react";
import axios from "axios";

function InputFormGroup({
  label,
  text,
  type = "text",
  id,
  name,
  placeholder,
  children,
}) {
  return (
    <div className="form-group">
      <label htmlFor={label} className="col-sm-offset-1 col-sm-3 control-label">
        {text}
      </label>
      <div className="col-sm-4">
        <input
          type={type}
          className="form-control"
          id={id}
          name={name}
          placeholder={placeholder}
        />
        {children}
      </div>
    </div>
  );
}

function AddUserView() {
  const handleAddUserClick = () => {
    const id = document.querySelector("input[name='newUserId']").value;
    const pw = document.querySelector("input[name='newPassword']").value;
    const pwConfirm = document.querySelector("input[name='password2']").value;
    const name = document.querySelector("input[name='userName']").value;
    const phone1 = document.getElementById("phone1").value;
    const phone2 = document.querySelector("input[name='phone2']").value;
    const phone3 = document.querySelector("input[name='phone3']").value;
    const phone = `${phone1}-${phone2}-${phone3}`;
    const email = document.querySelector("input[name='email']").value;
    const ssn = document.querySelector("input[name='ssn']").value;
    const addr = document.querySelector("input[name='addr']").value;

    const userData = {
      userId: id,
      password: pw,
      userName: name,
      ssn: ssn,
      addr: addr, 
      phone: phone,
      phone1: phone1,
      phone2: phone2,
      phone3: phone3,
      email: email,
    };

    if (id == null || id.length < 3) {
      alert("아이디는 반드시 3글자 이상 입력하셔야 합니다.");
      return;
    }
    if (pw == null || pw.length < 1) {
      alert("패스워드는  반드시 입력하셔야 합니다.");
      return;
    }
    if (pwConfirm == null || pwConfirm.length < 1) {
      alert("패스워드 확인은  반드시 입력하셔야 합니다.");
      return;
    }
    if (name == null || name.length < 1) {
      alert("이름은  반드시 입력하셔야 합니다.");
      return;
    }

    if (pw != pwConfirm) {
      alert("비밀번호 확인이 일치하지 않습니다.");
      document.querySelector("input[type='text'][name='password2']").focus();
      return;
    }
    axios
      .post("/api/v1/json/signup", userData)
      .then((response) => {
        console.log("사용자 추가 성공", response);
        window.location = "/";
      })
      .catch((error) => {
        console.error("사용자 추가 실패", error);
      });
  };

  useEffect(() => {
    const handleUserIdChange = (event) => {
      const userId = event.target.value;
      console.log(userId);
      if (userId === "" || userId.length <= 2) {
        document.querySelector("strong.text-danger").innerHTML =
          "아이디는 3글자 이상 입력해야합니다.";
        return;
      }
      axios
        .post("/user/json/checkDuplication/" + userId, { userId: userId })
        .then((response) => {
          if (!response.data.result) {
            document.querySelector("strong.text-danger").innerHTML =
              "사용 가능한 아이디입니다.";
          } else if (response.data.result && userId.length > 2) {
            document.querySelector("strong.text-danger").innerHTML =
              "사용할 수 없는 아이디입니다.";
          }
        })
        .catch((error) => {
          document.querySelector("strong.text-danger").innerHTML =
            "아이디 확인 중 오류가 발생했습니다.";
        });
    };
    //컴포넌트 마운트 시 이벤트 등록
    document
      .querySelector("#newUserId")
      .addEventListener("input", handleUserIdChange);
    return () => {
      //컴포넌트 언마운트 시 이벤트 해제
      document
        .querySelector("#newUserId")
        .removeEventListener("input", handleUserIdChange);
    };
  }, []);
  //fetch 사용
  /*
    fetch("/user/json/checkDuplication/"+userId, {
    method: "POST",
    body: JSON.stringify({ userId: userId }),
    headers: {
        "Content-Type": "application/json"
    }
    })
     .then(response => response.json())
      .then(data => {
        if (!data.result) {
             document.querySelector("strong.text-danger").innerHTML("이미 사용 중인 아이디입니다.")
        } else if (data.result && userId.length > 2) {
            document.querySelector("strong.text-danger").innerHTML("사용 가능한 아이디입니다.")
        }
    })
    .catch(error => {
        document.querySelector("strong.text-danger").innerHTML("아이디 확인 중 오류가 발생했습니다.")
    });
    */

  function handleEmailChange(event) {
    const email = event.target.value;
    if (email != "" && (email.indexOf("@") < 1 || email.indexOf(".") == -1)) {
      alert("이메일 형식이 아닙니다.");
    }
  }

  return (
    <>
      <div className="container">
        <h1 className="text-center">회 원 가 입</h1>


          <InputFormGroup
            label={"newUserId"}
            text={"아 이 디"}
            id={"newUserId"}
            name={"newUserId"}
          >
            <span>
              <strong className="text-danger">
                아이디는 3글자 이상 입력해야합니다.
              </strong>
            </span>
          </InputFormGroup>

          <InputFormGroup
            label={"newPassword"}
            type={"password"}
            text={"비밀번호"}
            id={"newPassword"}
            name={"newPassword"}
            placeholder={"비밀번호"}
          />

          <InputFormGroup
            label={"password2"}
            type={"password"}
            text={"비밀번호 확인"}
            id={"password2"}
            name={"password2"}
            placeholder={"비밀번호 확인"}
          />

          <InputFormGroup
            label={"userName"}
            text={"이름"}
            id={"userName"}
            name={"userName"}
            placeholder={"회원이름"}
          />

          <InputFormGroup
            label={"ssn"}
            text={"주민번호"}
            id={"ssn"}
            name={"ssn"}
            placeholder={"주민번호"}
          >
            <span id="helpBlock" className="help-block">
              <strong className="text-danger">
                " - " 제외 13자리입력하세요
              </strong>
            </span>
          </InputFormGroup>

          <InputFormGroup
            label={"ssn"}
            text={"주소"}
            id={"addr"}
            name={"addr"}
            placeholder={"주소"}
          />

          <div className="form-group">
            <label
              htmlFor="ssn"
              className="col-sm-offset-1 col-sm-3 control-label"
            >
              휴대전화번호
            </label>
            <div className="col-sm-2">
              <select className="form-control" name="phone1" id="phone1">
                <option value="010" selected>
                  010
                </option>
                <option value="011">011</option>
                <option value="016">016</option>
                <option value="018">018</option>
                <option value="019">019</option>
              </select>
            </div>
            <div className="col-sm-2">
              <input
                type="text"
                className="form-control"
                id="phone2"
                name="phone2"
                placeholder="번호"
              />
            </div>
            <div className="col-sm-2">
              <input
                type="text"
                className="form-control"
                id="phone3"
                name="phone3"
                placeholder="번호"
              />
            </div>
            <input type="hidden" name="phone" />
          </div>

          <InputFormGroup
            label={"ssn"}
            text={"이메일"}
            id={"email"}
            name={"email"}
            placeholder={"이메일"}
            onChange={handleEmailChange}
          />

          <div className="form-group">
            <div className="col-sm-offset-4  col-sm-4 text-center">
              <button
                type="button"
                className="btn btn-primary"
                onClick={handleAddUserClick}
              >
                가&nbsp;입
              </button>
              <button
                type="button"
                className="btn btn-primary"
                onClick={() => {
                  history.back();
                }}
              >
                취&nbsp;소
              </button>
            </div>
          </div>
      </div>
      {/* <!--  화면구성 div end /////////////////////////////////////--> */}
    </>
  );
}
export default AddUserView;
