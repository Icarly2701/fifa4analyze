import { useCallback, useState } from "react";
import { useDispatch, useSelector} from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { getData, } from "../pageinfo";

const Recordheader = () => {
    const [nickname , setNickname] = useState('');
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const renew = useSelector((state) => state.renew);

    const onChangeNickname = useCallback((e) => {
        setNickname(e.target.value);
    }, []);

    /*seEffect(() => {
        navigate(`/record/${nickname}`);
    }, [renew])*/

    function setnickname(){
        dispatch(getData(nickname));
    }

    const goToHome = () => {
        navigate("/");
    }

    return(
        <div className="researchBox" style={{paddingRight:0}}>
                <div className="backMain" style={{fontSize:50,color:"black",marginLeft:100, fontWeight:"bold", cursor:"pointer"}} onClick={goToHome}>
                    피알남
                </div>
            <div>
                <input placeholder="닉네임을 입력하세요" type='text'
                    value={nickname} onChange={onChangeNickname}
                    style={{width:200, height:30, fontSize:15}}
                />
                <Link to = {`/record/${nickname}`}>
                    <input type='button' value="검색하기" className='nickname-btn' onClick={setnickname} style={{height:40, fontSize:15}}/>
                </Link>
            </div>
        </div>
    )
}

export default Recordheader;