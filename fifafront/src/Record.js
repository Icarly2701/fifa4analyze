import { useSelector } from "react-redux";
import RecordPage from "./component/recordpage";
const Record = () => {
    const renew = useSelector((state) => state.renew);
    return(
        <>
            {renew ? <RecordPage /> : <div></div>}
        </>
    )  
}

export default Record;