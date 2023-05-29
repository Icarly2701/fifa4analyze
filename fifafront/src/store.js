import { configureStore, getDefaultMiddleware } from "@reduxjs/toolkit";
import pageinfo from "./pageinfo";
export const store = configureStore({
    reducer:{
        pageinfo:pageinfo,
    },
})
