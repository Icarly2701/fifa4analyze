import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const initialState = {
  renew: false,
  mainData: {
    nickname: "",
    level: 0,
    tier: "",
    mypomation: "",
    record: [],
    worstpomation: "",
    playfix: "",
    worstP1: "",
    worstP2: "",
    worstP3: "",
  },
};

export const getData = createAsyncThunk("history/getData", async (data) => {
  const response = await axios
    .get("/userInformation?nickname=" + data.nickname + "&isrenew=" + data.red)
    .then((res) => {
      return res.data;
    })
    .catch((Error) => {
      alert("존재하지 않습니다");
      return Error;
    });

  return response;
});

export const pageinfo = createSlice({
  name: "history",
  initialState: initialState,
  reducers: {
    setRenew(state) {
      state.renew = false;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(getData.pending, (state) => {
      state.renew = false;
    });
    builder.addCase(getData.fulfilled, (state, action) => {
      console.log(action);
      state.mainData = action.payload;
      state.renew = true;
    });
    builder.addCase(getData.rejected, (state) => {
      state.renew = false;
      alert("존재하지 않는 ID 입니다.");
    });
  },
});

export const { setRenew } = pageinfo.actions;
export default pageinfo.reducer;
