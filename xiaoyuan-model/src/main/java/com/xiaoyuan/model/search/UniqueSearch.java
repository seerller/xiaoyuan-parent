package com.xiaoyuan.model.search;

import lombok.Data;

@Data
public class UniqueSearch extends BaseSearch {

    private Integer userId;

    private Integer customerId;

    private boolean type = true;

    public UniqueSearch setUserId(Integer userId){
        this.userId = userId;
        return this;
    }

    public UniqueSearch setCustomerId(Integer customerId){
        this.customerId = customerId;
        return this;
    }

    public UniqueSearch(){}
    public UniqueSearch(String search,Integer userId,Integer customerId){
        this.setSearch(search);
        this.userId = userId;
        this.customerId = customerId;
    }
    public UniqueSearch setType(boolean type){
        this.type = type;
        return this;
    }
}
