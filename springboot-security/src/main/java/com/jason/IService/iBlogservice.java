package com.jason.IService;

import com.jason.model.Blog;

import java.util.List;

public interface iBlogservice {
    List<Blog> getBlogs();
    void deleteBlog(long id);
}
