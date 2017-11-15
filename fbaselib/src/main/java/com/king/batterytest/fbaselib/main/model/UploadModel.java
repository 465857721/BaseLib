package com.king.batterytest.fbaselib.main.model;

/**
 * Created by zhoukang on 2017/5/4.
 */

public class UploadModel {

    /**
     * error : 0
     * msg : {"id":"2296","url":"/uimage/2f/01/d4/81/2f01d4817efb6d1b306efd4c11160a36.jpg"}
     */

    private int error;
    private MsgBean msg;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * id : 2296
         * url : /uimage/2f/01/d4/81/2f01d4817efb6d1b306efd4c11160a36.jpg
         */

        private String id;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
