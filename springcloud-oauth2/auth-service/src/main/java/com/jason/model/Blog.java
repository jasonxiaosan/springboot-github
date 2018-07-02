package com.jason.model;

/**
 * Created by Administrator on 2018/6/29.
 */
public class Blog {
    private Long id;
    private String name;
    private String content;
    public Blog(Long id,String name,String content){
        this.setId(id);
        this.setName(name);
        this.setContent(content);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
