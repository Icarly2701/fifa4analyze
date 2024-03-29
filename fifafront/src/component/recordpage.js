import { useSelector, useDispatch } from "react-redux";
import renew from "../images/logo2.png";
import Recordstory from "./Recordstory";
import Recordheader from "./Recordheader";
import { getData } from "../pageinfo";

const RecordPage = () => {
  const mainData = useSelector((state) => state.pageinfo.mainData);
  const dispatch = useDispatch();

  function setnickname() {
    dispatch(getData({ nickname: mainData.nickname, red: "yes" }));
  }

  let tier;
  switch (mainData.tier) {
    case "유망주1":
      tier = "amature1";
      break;
    case "유망주2":
      tier = "amature2";
      break;
    case "유망주3":
      tier = "amature3";
      break;
    case "세미프로1":
      tier = "semipro1";
      break;
    case "세미프로2":
      tier = "semipro2";
      break;
    case "세미프로3":
      tier = "semipro3";
      break;
    case "프로1":
      tier = "pro1";
      break;
    case "프로2":
      tier = "pro2";
      break;
    case "프로3":
      tier = "pro3";
      break;
    case "월드클래스1":
      tier = "worldclass1";
      break;
    case "월드클래스2":
      tier = "worldclass2";
      break;
    case "월드클래스3":
      tier = "worldclass3";
      break;
    case "챌린지1":
      tier = "challenge1";
      break;
    case "챌린지2":
      tier = "challenge2";
      break;
    case "챌린지3":
      tier = "challenge3";
      break;
    case "슈퍼챌린지":
      tier = "superchallenge";
      break;
    case "챔피언스":
      tier = "champions";
      break;
    default:
      tier = "superchampions";
  }

  return (
    <div
      className="centerBox"
      style={{
        marginTop: "3%",
        marginLeft: "5%",
        marginRight: "5%",
        marginBottom: "3%",
        paddingBottom: "0%",
        paddingTop: "3%",
        backgroundColor: "rgba(255, 255, 255, 0.9)",
      }}
    >
      <Recordheader />
      <div className="headerBox">
        <div className="logoBox">
          <div className="logo">
            <img
              src={process.env.PUBLIC_URL + "/" + tier + ".png"}
              className="logo-picture"
              alt="로고"
            />
          </div>
          <div
            className="nickname"
            style={{ marginLeft: 30, marginRight: 30, display: "flex" }}
          >
            <div className="nickname-box">{mainData.nickname}</div>
          </div>
          <div className="level" style={{ display: "flex", fontSize: 20 }}>
            LEVEL:
            <div className="nickname-box">{mainData.level}</div>
          </div>
        </div>

        <div className="logoBox">
          <div className="renew">
            <img
              src={renew}
              className="renew-picture"
              alt="새로고침"
              onClick={setnickname}
            />
            {/*<p>{mainData.date}</p>*/}
          </div>
          <div className="tier" style={{ marginLeft: 30 }}>
            최고 티어:
            <div className="nickname-box">{mainData.tier}</div>
          </div>
        </div>
      </div>

      <div className="bodyBox">
        <div className="pomation">
          <div className="yours-pomation">
            사용 포메이션:
            <div className="nickname-box">{mainData.mypomation}</div>
          </div>

          <div className="bestworst-pomation">
            <p style={{ display: "flex", alignItems: "center" }}>
              상대하기 어려운 포메이션:
              <p className="nickname-box">{mainData.worstpomation}</p>
            </p>
          </div>
        </div>
        <div className="advice">
          <div className="goodplay" style={{ display: "flex" }}>
            플레이 개선점:
            <div className="nickname-box">{mainData.playfix}</div>
          </div>
          <div className="worstplayer" style={{ display: "flex" }}>
            아쉬운 선수:
            <div className="nickname-box">
              {mainData.worstP1 +
                ", " +
                mainData.worstP2 +
                ", " +
                mainData.worstP3}
            </div>
          </div>
        </div>
      </div>

      <div style={{ fontSize: 25, fontWeight: "bold" }}>전적</div>
      <div className="record">
        {mainData.records.map((i, index) => (
          <Recordstory
            key={index}
            nickname={i.oppnickname}
            result={i.result}
            score={i.score}
          />
        ))}
      </div>
    </div>
  );
};

export default RecordPage;
