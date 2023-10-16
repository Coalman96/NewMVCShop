import { createContext, useState, useEffect } from "react";

const UserContext = createContext();

const UserProvider = ({ children }) => {
    const initialUser = JSON.parse(sessionStorage.getItem("user")) || "guest";
    const [user, setUser] = useState(initialUser);
  
    useEffect(() => {
      // 사용자 정보가 업데이트될 때 세션에 저장
      sessionStorage.setItem("user", JSON.stringify(user));
    }, [user]);
  
    return (
      <UserContext.Provider value={{ user, setUser }}>
        {children}
      </UserContext.Provider>
    );
  };

export { UserProvider, UserContext };
