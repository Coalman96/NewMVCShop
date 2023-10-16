import axios from "axios";
import React, { useEffect, useState } from "react";

const ListUser = () => {
  const [userData, setUserData] = useState([]);
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
      .post("/user/json/getUserList", requestData, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        const newData = response.data.data;
        setUserData((prevData) => [...prevData, ...newData]);
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
    console.log(userData);
    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, [userData]); // userData가 업데이트될 때마다 스크롤 이벤트를 감지

  return (
    <div style={{ width: "98%", marginLeft: "10px" }}>
      <input type="hidden" name="currentPage" value="0" />
      <div className="container my-5">
        <nav className="navbar row">
        <div className="container-fluid">
              <h3 className="relative position-relative">회원관리</h3>
              <div className="row">
                <div className="col-4">
                  <select
                    className="w-100 form-select"
                    value={searchType}
                    onChange={handleSearchTypeChange}
                  >
                    <option value="0">회원ID</option>
                    <option value="1">회원명</option>
                  </select>
                </div>
                <div className="col-8 d-flex">
                  <input
                    className="form-control w-100"
                    type="text"
                    value={searchKeyword}
                    onChange={handleSearchKeywordChange}
                  />
                  <button className="btn btn-outline-info w-50 text-14" type="submit">
                    검색
                  </button>
                </div>
              </div>
            </div>
            </nav>
        {/* <div style={{ marginTop: '10px' }}>전체 {resultPage.totalCount} 건수</div> */}

        <div
          className="row"
          style={{ marginBottom: "20px", textAlign: "center" }}
        >
          <div className="col-md-1 ct_list_b">No</div>
          <div className="col-md-1 ct_line02"></div>
          <div className="col-md-2 ct_list_b">회원ID</div>
          <div className="col-md-1 ct_line02"></div>
          <div className="col-md-2 ct_list_b">회원명</div>
          <div className="col-md-1 ct_line02"></div>
          <div className="col-md-4 ct_list_b">이메일</div>
        </div>

        {userData.map((user, i) => (
          <div key={i}>
            <div
              className="row ct_list_pop"
              style={{
                display: "flex",
                alignItems: "center",
                textAlign: "center",
              }}
            >
              <div className="col-md-1" style={{ height: "80px" }}>
                <div
                  style={{
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center",
                    height: "100%",
                    justifyContent: "center", // 세로 중앙 정렬을 위해 추가
                  }}
                >
                  {i + 1}
                </div>
              </div>
              <div className="col-md-1"></div>
              <div className="col-md-2">{user.userId}</div>
              <div className="col-md-1"></div>
              <div className="col-md-2">{user.userName}</div>
              <div className="col-md-1"></div>
              <div className="col-md-4">{user.email}</div>
            </div>
            <div className="row">
              <div
                id={user.userId}
                className="col-md-12"
                style={{ backgroundColor: "#D6D7D6", height: "1px" }}
              ></div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ListUser;
