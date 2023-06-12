import { useCallback, useState } from 'react';
import { useDispatch, } from 'react-redux';
import {Link} from 'react-router-dom';
import { getData} from '../pageinfo';
const Basicpage = () => {
    const [nickname , setNickname] = useState('');
    const dispatch = useDispatch();

    const onChangeNickname = useCallback((e) => {
        setNickname(e.target.value);
    }, []);

    function setnickname(){
        dispatch(getData(nickname));
    }

    return(
        <div className="centerBox">
            <div className='mainTitle'>
                <p>피파 알려주는 남자들</p>
            </div>
            <div className='inputForm'>
                <div>
                    <input placeholder="닉네임을 입력하세요" type='text'
                    value={nickname} onChange={onChangeNickname}
                    style={{width:500, height:50, fontSize:20}}/>
                </div>
                <div className='nickname-btn'>
                    <Link to = {`/record/${nickname}`}>
                    <input type='button' value="검색하기" className='nickname-btn' onClick={setnickname}/>
                    </Link>
                </div>
            </div>
        </div>
    );
}

export default Basicpage;