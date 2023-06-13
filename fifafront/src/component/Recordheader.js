import { useCallback, useState } from "react";
import { useDispatch} from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { getData } from "../pageinfo";

const Recordheader = () => {
    const [nickname , setNickname] = useState('');
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const onChangeNickname = useCallback((e) => {
        setNickname(e.target.value);
    }, []);
    function setnickname(){
        dispatch(getData(nickname));
    }

    const goToHome = () => {
        navigate("/");
    }

    return(
        <div className="researchBox">
                <div className="backMain" style={{fontSize:50,color:"black",marginLeft:100, fontWeight:"bold", cursor:"pointer"}} onClick={goToHome}>
                    피알남
                </div>
            <div style={{marginRight:100}}>
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