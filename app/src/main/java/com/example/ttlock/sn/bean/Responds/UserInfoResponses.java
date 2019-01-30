package com.example.ttlock.sn.bean.Responds;

/**
 * Created by jl on 2019/1/30.
 */

public class UserInfoResponses {

    /**
     * id : 0
     * certificateNumber : string
     * certificateType : IDCARD
     * email : string
     * group : {"id":0,"name":"string","shortName":"string","created":"2019-01-30T08:47:12.035Z","updated":"2019-01-30T08:47:12.035Z"}
     * mobile : string
     * nickname : string
     * password : string
     * role : {"id":0,"name":"string","created":"2019-01-30T08:47:12.035Z","updated":"2019-01-30T08:47:12.035Z"}
     * salt : string
     * type : GOD
     * username : string
     * created : 2019-01-30T08:47:12.035Z
     * updated : 2019-01-30T08:47:12.035Z
     */

    private int id;
    private String certificateNumber;
    private String certificateType;
    private String email;
    private GroupBean group;
    private String mobile;
    private String nickname;
    private String password;
    private RoleBean role;
    private String salt;
    private String type;
    private String username;
    private String created;
    private String updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleBean getRole() {
        return role;
    }

    public void setRole(RoleBean role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public static class GroupBean {
        /**
         * id : 0
         * name : string
         * shortName : string
         * created : 2019-01-30T08:47:12.035Z
         * updated : 2019-01-30T08:47:12.035Z
         */

        private int id;
        private String name;
        private String shortName;
        private String created;
        private String updated;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }
    }

    public static class RoleBean {
        /**
         * id : 0
         * name : string
         * created : 2019-01-30T08:47:12.035Z
         * updated : 2019-01-30T08:47:12.035Z
         */

        private int id;
        private String name;
        private String created;
        private String updated;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }
    }
}
