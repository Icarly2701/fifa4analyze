import { useCallback, useEffect, useState } from 'react';
import { useDispatch, useSelector, } from 'react-redux';
import {Link, useNavigate} from 'react-router-dom';
import { getData} from './pageinfo';
const Basicpage = () => {
    const [nickname , setNickname] = useState('');
    const renew = useSelector((state) => state.renew);
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const onChangeNickname = useCallback((e) => {
        setNickname(e.target.value);
    }, []);
    useEffect(() => {
        if(renew)
            navigate(`/record/${nickname}`);
    }, [renew])
    function setnickname(){
        dispatch(getData(nickname));
    }

    const goToHome = () => {
        navigate('/');
    }

    return(
        <div className="centerBox">
            <div className='mainTitle'>
                <p onClick={goToHome}>피파 알려주는 남자들</p>
            </div>
            <div className='inputForm'>
                <div>
                    <input placeholder="닉네임을 입력하세요" type='text'
                    value={nickname} onChange={onChangeNickname}
                    style={{width:500, height:50, fontSize:20}}/>
                </div>
                <div className='nickname-btn'>
                    <input type='button' value="검색하기" className='nickname-btn' onClick={setnickname}/>
                </div>
            </div>
        </div>
    );
}

export default Basicpage;