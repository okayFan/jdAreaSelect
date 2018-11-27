package com.okayfan.jdareaselect;

import java.util.List;


/**
 * <pre>
 *     author : fanYangXiao
 *     time   : 2018/11/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class Area {



    private int code;
    private String msg;
    private DataBean data;
    private long t;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public static class DataBean {


        private String areaId;
        private String areaName;
        private List<AreasBeanXX> areas;//省

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public List<AreasBeanXX> getAreas() {
            return areas;
        }

        public void setAreas(List<AreasBeanXX> areas) {
            this.areas = areas;
        }

        public static class AreasBeanXX {
            private String areaId;
            private String areaName;
            private List<AreasBeanX> areas;//市


            public String getAreaId() {
                return areaId;
            }

            public void setAreaId(String areaId) {
                this.areaId = areaId;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public List<AreasBeanX> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBeanX> areas) {
                this.areas = areas;
            }

            public static class AreasBeanX {
                private String parentId;
                private String areaId;
                private String areaName;
                private List<AreasBean> areas;//区

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }

                public String getAreaId() {
                    return areaId;
                }

                public void setAreaId(String areaId) {
                    this.areaId = areaId;
                }

                public String getAreaName() {
                    return areaName;
                }

                public void setAreaName(String areaName) {
                    this.areaName = areaName;
                }

                public List<AreasBean> getAreas() {
                    return areas;
                }

                public void setAreas(List<AreasBean> areas) {
                    this.areas = areas;
                }

                public static class AreasBean {
                    /**
                     * areaId : 110101
                     * areaName : 东城区
                     * areas : null
                     */
                    private String parentId;
                    private String areaId;
                    private String areaName;
                    private Object areas;

                    public String getParentId() {
                        return parentId;
                    }

                    public void setParentId(String parentId) {
                        this.parentId = parentId;
                    }

                    public String getAreaId() {
                        return areaId;
                    }

                    public void setAreaId(String areaId) {
                        this.areaId = areaId;
                    }

                    public String getAreaName() {
                        return areaName;
                    }

                    public void setAreaName(String areaName) {
                        this.areaName = areaName;
                    }

                    public Object getAreas() {
                        return areas;
                    }

                    public void setAreas(Object areas) {
                        this.areas = areas;
                    }
                }
            }
        }
    }
}
