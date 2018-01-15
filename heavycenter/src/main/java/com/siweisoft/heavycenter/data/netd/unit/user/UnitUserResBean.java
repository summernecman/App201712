package com.siweisoft.heavycenter.data.netd.unit.user;

//by summer on 2018-01-11.

import com.android.lib.bean.BaseBean;

import java.util.List;

public class UnitUserResBean extends BaseBean {


    /**
     * totalCount : 9
     * pageIndex : -1000
     * pageCount : 1
     * pageSize : 1000
     * results : [{"trueName":"唐杰","userPhoto":"","auditerName":"","tel":"18721607419","userRole":"admin","userId":140,"bindCompanyTime":1515663494000,"bindCompanyState":2,"status":1}]
     */

    private int totalCount;
    private int pageIndex;
    private int pageCount;
    private int pageSize;
    private List<ResultsBean> results;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * trueName : 唐杰
         * userPhoto :
         * auditerName :
         * tel : 18721607419
         * userRole : admin
         * userId : 140
         * bindCompanyTime : 1515663494000
         * bindCompanyState : 2
         * status : 1
         */

        private String trueName;
        private String userPhoto;
        private String auditerName;
        private String tel;
        private String userRole;
        private int userId;
        private long bindCompanyTime;
        private int bindCompanyState;
        private int status;

        public final static int STATUS_ONLINE = 1;

        public final static int STATUS_OFFLINE = 0;

        public final static String STATUS_ONLINE_CN = "在线";

        public final static String STATUS_OFFLINE_CN = "离线";

        public String getTrueName() {
            return trueName;
        }

        public void setTrueName(String trueName) {
            this.trueName = trueName;
        }

        public String getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(String userPhoto) {
            this.userPhoto = userPhoto;
        }

        public String getAuditerName() {
            return auditerName;
        }

        public void setAuditerName(String auditerName) {
            this.auditerName = auditerName;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getUserRole() {
            return userRole;
        }

        public void setUserRole(String userRole) {
            this.userRole = userRole;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public long getBindCompanyTime() {
            return bindCompanyTime;
        }

        public void setBindCompanyTime(long bindCompanyTime) {
            this.bindCompanyTime = bindCompanyTime;
        }

        public int getBindCompanyState() {
            return bindCompanyState;
        }

        public void setBindCompanyState(int bindCompanyState) {
            this.bindCompanyState = bindCompanyState;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}