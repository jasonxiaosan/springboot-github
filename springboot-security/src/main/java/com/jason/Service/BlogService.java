package com.jason.Service;

import com.jason.IService.iBlogservice;
import com.jason.model.Blog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class BlogService implements iBlogservice {
    private List<Blog> list = new ArrayList<>();
    public BlogService(){
        list.add(new Blog(1L,"111111","good!"));
        list.add(new Blog(2L,"222222","nice"));
    }
    @Override
    public List<Blog> getBlogs(){
        return list;
    }
    @Override
    public void deleteBlog(long id){
        Iterator iter = list.iterator();
        while(iter.hasNext()){
            Blog blog = (Blog)iter.next();
            if(blog.getId()==id){
                iter.remove();
            }
        }

    }
}
