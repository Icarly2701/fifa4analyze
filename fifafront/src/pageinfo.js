import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const initialState = {
    bestpomation:"",
    worstpomation:"",
    playfix:"",
    worstplayer:[],
    renew:false,
    mainData:{
        nickname:"",
        level:0,
        tier:"",
        mypomation:"",
        record:[],
    },
}

export const getData = createAsyncThunk(
    'history/getData',
    async(nickname) => {
        const response = await axios.get("/userInformation?nickname="+nickname)
            .then((res) => {
                console.log(res.data.level);
                return res.data;
            })
            .catch((Error) => {
                console.log(Error);
                return "";
            });

        return response;
    }
)

export const pageinfo = createSlice({
    name: 'history',
    initialState: initialState,
    reducers:{},
    extraReducers : (builder) => {
        builder.addCase(getData.pending, (state) => {
            state.renew = false;
        })
        builder.addCase(getData.fulfilled, (state, action)=>{
            console.log(action);
            state.mainData = action.payload;
            state.renew = true;
            console.log(state);
        })
        builder.addCase(getData.rejected, (state)=>{
            state.renew = false;
            alert("존재하지 않는 ID 입니다.");
        })
    }
})

export default pageinfo.reducer;

