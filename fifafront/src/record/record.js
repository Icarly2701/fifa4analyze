import Recordstory from "../component/recordstory";
import logo from "../images/logo.png";
import renew from "../images/renew.png";
const record = () => {
    return(
        <div className="centerBox" style={{marginTop:"5%"}}>
            <div className="headerBox">

                <div className="logoBox">
                    <div className="logo">
                        <img src={logo} className="logo-picture"  alt="로고" />
                    </div>
                    <div className="nickname" style={{marginLeft:30, marginRight:30, display:"flex",}}>
                        <div className="nickname-box">icarly1379</div>
                    </div>
                    <div className="level" style = {{display:"flex", fontSize:20}}>
                        LEVEL: 
                        <div className="nickname-box">121</div>
                    </div>
                </div>

                <div className="logoBox">
                    <div className="renew">
                        <img src={renew} className="renew-picture" alt="새로고침" />
                    </div>
                    <div className="tier" style={{marginLeft:30}} >
                        TIER: 
                        <div className="nickname-box">프로 1부</div>
                    </div>
                </div>

            </div>
            
            <div className="bodyBox">

                <div className="pomation">
                    <div className="yours-pomation">
                        YOUR FORMATION: 
                        <div className="nickname-box">4-2-3-1</div>
                    </div>
                    <div className="bestworst-pomation">
                        <p style={{display:"flex", alignItems:"center"}}>FAVORABLE FORMATION: 
                            <p className="nickname-box">5-2-3</p>
                        </p>
                        <p style={{display:"flex", alignItems:"center"}}>UNFAVORABLE FORMATION: 
                            <p className="nickname-box">4-1-4-1</p>
                        </p>
                    </div>
                </div>
                <div className="advice">
                    <div className="goodplay" style ={{display:"flex"}}>
                        GOOD ADVICE:
                        <div className="nickname-box">
                            패스를 늘려서 하기
                        </div>
                    </div>
                    <div className="worstplayer" style={{display:"flex"}}>
                        WORST PLAYER: 
                        <div className="nickname-box">
                            제시 린가드, 루크 쇼, 호날두
                        </div>
                    </div>
                </div>
            </div>
            

            <div style={{fontSize:25, fontWeight:"bold"}}>전적</div>
            <div className="record">
                <Recordstory />
            </div>
        </div>
    )
}

export default record;