import { useSelector } from "react-redux";
import RecordPage from "./component/recordpage";
const Record = () => {
  const renew = useSelector((state) => state.pageinfo.renew);
  return <>{renew ? <RecordPage /> : <></>}</>;
};

export default Record;
