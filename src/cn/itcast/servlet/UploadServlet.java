package cn.itcast.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.util.UploadUtil;

/**
 * 文件上传
 * @author Administrator
 *
 */
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1294333361466189264L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 1.创建磁盘文件项工厂类	DiskFileItemFactory
		 * 2.创建核心解析request类	ServletFileUpload
		 * 3.开始解析request对象，返回是List集合
		 * 4.List集合中保存（表单的内容，分部分）
		 * 5.循环遍历集合，获取内容
		 */
		
		// 判断表单的enctype是否正确
		if(!ServletFileUpload.isMultipartContent(request)){
			// 给用户提示
			throw new RuntimeException("enctype设置不正确");//相当于return
		}
		
		
		// 创建磁盘文件项工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 设置缓冲区的大小，默认10K
		factory.setSizeThreshold(1024*1024*3);	// 设置缓冲区为3M
		// 先获取temp的绝对磁盘路径
		String temp  = getServletContext().getRealPath("/temp");
		// 设置临时文件（文件的大小大于缓冲区的大小）
		factory.setRepository(new File(temp));
		
		// 创建核心解析对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// 设置上传文件的总大小
		// upload.setSizeMax(1024*1024*5);	// 设置5M
		
		// 设置上传文件的文件名称的编码
		upload.setHeaderEncoding("UTF-8");
		
		try {
			// 解析request
			List<FileItem> list = upload.parseRequest(request);
			// 循环遍历
			for (FileItem fileItem : list) {
				// fileItem有可能是普通的文本项，也有可能是一个文件上传项
				// 先判断当前fileItem是普通还是上传项		
				// isFormField() 返回true，就是普通文本项，返回false，就是文件上传项
				if(fileItem.isFormField()){
					// 普通项
					// 获取name的名称  filedesc  password  sex
					String name = fileItem.getFieldName();
					// 获取用户输入的值
					String value = fileItem.getString("UTF-8");
					System.out.println(name+" : "+value);
				}else{
					// 文件上传项
					// 先获取文件的名称
					String filename = fileItem.getName();
					
					// 你应该在数据库中把文件的原名称和UUID的名称都需要保存到数据库中。
					// 获取唯一的字符串
					filename = UUID.randomUUID().toString()+"_"+filename;
					System.out.println("文件名称："+filename);
					// 获取文件的输入流
					InputStream in = new BufferedInputStream(fileItem.getInputStream());
					// 项某个文件中写入
					// 项WebRoot/upload目录写入
					String path = getServletContext().getRealPath("/upload");
					
					// 进行目录分离
					String dir = UploadUtil.getPath(filename);
					File file = new File(path+dir);
					// 创建多层目录
					file.mkdirs();
					
					// 获取输出流
					OutputStream os = new BufferedOutputStream(new FileOutputStream(path+dir+"/"+filename));		
					// io拷贝
					int len = 0;
					byte [] b = new byte[1024];
					while((len = in.read(b)) != -1){
						os.write(b, 0, len);
					}
					in.close();
					os.close();
					
					// 删除临时文件（放流关闭后执行）
					fileItem.delete();
				}
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}





