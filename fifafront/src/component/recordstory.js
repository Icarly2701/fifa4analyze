const Recordstory = (props) => {
    const result = props.result;
    return(
        <div className="resultBox" style={{background: result === "승" ? "#ECF2FF" : result === "패" ? "#FFF1F3" : "#ECEEF0"}}>
            <div className="vsNickname">
                {props.nickname}
            </div>
            <div className="resultScore">
                {props.result}
            </div>
            <div className="score">
                {props.score}
            </div>
        </div>
    );
}

export default Recordstory