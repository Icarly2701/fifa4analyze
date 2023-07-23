import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { getData, setRenew } from "./pageinfo";
const Basicpage = () => {
  const [nickname, setNickname] = useState("");
  const renew = useSelector((state) => state.pageinfo.renew);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const onChangeNickname = useCallback((e) => {
    setNickname(e.target.value);
  }, []);

  useEffect(() => {
    if (renew) {
      navigate(`/record/${nickname}`);
    }
  }, [renew]);

  function setnickname() {
    dispatch(getData({ nickname: nickname, red: "no" }));
  }

  const goToHome = () => {
    console.log("dldldl");
    navigate("/");
  };

  return (
    <div className="centerBox">
      <div className="mainTitle">
        <p style={{ cursor: "pointer" }} onClick={goToHome}>
          피파 알려주는 남자들
        </p>
      </div>
      <div className="inputForm">
        <div>
          <input
            placeholder="닉네임을 입력하세요"
            type="text"
            value={nickname}
            onChange={onChangeNickname}
            style={{ width: 500, height: 50, fontSize: 20, border: "none" }}
          />
        </div>
        <div className="nickname-btn">
          <input
            type="button"
            value="검색하기"
            className="nickname-btn"
            onClick={setnickname}
          />
        </div>
      </div>
    </div>
  );
};
export default Basicpage;
