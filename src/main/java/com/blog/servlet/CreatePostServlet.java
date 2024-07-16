package com.blog.servlet;

import com.blog.dao.BlogPostDAO;
import com.blog.model.BlogPost;
import com.blog.service.BlogPostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.io.File;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/create-post")
@MultipartConfig
public class CreatePostServlet extends HttpServlet {
    private BlogPostDAO blogPostDAO;

    @Override
    public void init() {
        blogPostDAO = new BlogPostDAO();
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String title = request.getParameter("title");
//        String content = request.getParameter("content");
//        Part imagePart = request.getPart("image");
//        Part videoPart = request.getPart("video");
//
//        String imagePath = saveFile(imagePart);
//        String videoPath = saveFile(videoPart);
//
//        BlogPost post = new BlogPost();
//        post.setTitle(title);
//        post.setContent(content);
//        post.setImagePath(imagePath);
//        post.setVideoPath(videoPath);
//
//        try {
//            blogPostDAO.createPost(post);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        response.sendRedirect("admin-dashboard.jsp");
//    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multipartItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                BlogPost post = new BlogPost();

                for (FileItem item : multipartItems) {
                    if (item.isFormField()) {
                        switch (item.getFieldName()) {
                            case "title":
                                post.setTitle(item.getString());
                                break;
                            case "content":
                                post.setContent(item.getString());
                                break;
                        }
                    } else {
                        // Handle file uploads
                        if (item.getFieldName().equals("image")) {
                            String imagePath = "path/to/images/" + item.getName();
                            item.write(new File(imagePath));
                            post.setImagePath(imagePath);
                        }
                        if (item.getFieldName().equals("video")) {
                            String videoPath = "path/to/videos/" + item.getName();
                            item.write(new File(videoPath));
                            post.setVideoPath(videoPath);
                        }
                    }
                }

                BlogPostService blogPostService = new BlogPostService();
                blogPostService.createPost(post);
                response.sendRedirect("admin-dashboard.jsp");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String saveFile(Part part) throws IOException {
        if (part != null && part.getSize() > 0) {
            String fileName = part.getSubmittedFileName();
            String filePath = getServletContext().getRealPath("/uploads/") + fileName;
            part.write(filePath);
            return "uploads/" + fileName;
        }
        return null;
    }
}
