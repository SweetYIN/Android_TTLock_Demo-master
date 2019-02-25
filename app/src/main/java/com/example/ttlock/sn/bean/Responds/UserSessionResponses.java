package com.example.ttlock.sn.bean.Responds;

import java.util.List;

/**
 * Created by jl on 2019/2/25.
 */

public class UserSessionResponses {

    /**
     * gid : 0
     * name : string
     * resources : ["string"]
     * rid : 0
     * scope : {"conditions":{"additionalProp1":{},"additionalProp2":{},"additionalProp3":{}},"gid":0,"restricteds":["string"],"rid":0,"uid":0}
     * tags : ["string"]
     * type : GOD
     * uid : 0
     */

    private int gid;
    private String name;
    private int rid;
    private ScopeBean scope;
    private String type;
    private int uid;
    private List<String> resources;
    private List<String> tags;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public ScopeBean getScope() {
        return scope;
    }

    public void setScope(ScopeBean scope) {
        this.scope = scope;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public static class ScopeBean {
        /**
         * conditions : {"additionalProp1":{},"additionalProp2":{},"additionalProp3":{}}
         * gid : 0
         * restricteds : ["string"]
         * rid : 0
         * uid : 0
         */

        private ConditionsBean conditions;
        private int gid;
        private int rid;
        private int uid;
        private List<String> restricteds;

        public ConditionsBean getConditions() {
            return conditions;
        }

        public void setConditions(ConditionsBean conditions) {
            this.conditions = conditions;
        }

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public List<String> getRestricteds() {
            return restricteds;
        }

        public void setRestricteds(List<String> restricteds) {
            this.restricteds = restricteds;
        }

        public static class ConditionsBean {
            /**
             * additionalProp1 : {}
             * additionalProp2 : {}
             * additionalProp3 : {}
             */

            private AdditionalProp1Bean additionalProp1;
            private AdditionalProp2Bean additionalProp2;
            private AdditionalProp3Bean additionalProp3;

            public AdditionalProp1Bean getAdditionalProp1() {
                return additionalProp1;
            }

            public void setAdditionalProp1(AdditionalProp1Bean additionalProp1) {
                this.additionalProp1 = additionalProp1;
            }

            public AdditionalProp2Bean getAdditionalProp2() {
                return additionalProp2;
            }

            public void setAdditionalProp2(AdditionalProp2Bean additionalProp2) {
                this.additionalProp2 = additionalProp2;
            }

            public AdditionalProp3Bean getAdditionalProp3() {
                return additionalProp3;
            }

            public void setAdditionalProp3(AdditionalProp3Bean additionalProp3) {
                this.additionalProp3 = additionalProp3;
            }

            public static class AdditionalProp1Bean {
            }

            public static class AdditionalProp2Bean {
            }

            public static class AdditionalProp3Bean {
            }
        }
    }
}
