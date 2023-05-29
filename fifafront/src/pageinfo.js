import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    logo:"",
    nickname:"",
    level:0,
    renew:false,
    tier:"",
    mypomation:"",
    bestpomation:"",
    worstpomation:"",
    playfix:"",
    worstplayer:[],
    record:[{
            oppnickname:"",
            result:"",
            score:"",
            time:"",
        },  
    ],
}

export const pageinfo = createSlice({
    name: 'history',
    initialState,
    reducers : {
        setNickname(state, action) {
            state.nickname = action.payload;
            console.log(state.nickname);
        }
    }
})

export const {setNickname} = pageinfo.actions;
export default pageinfo.reducer;

