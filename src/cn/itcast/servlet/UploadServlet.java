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
 * �ļ��ϴ�
 * @author Administrator
 *
 */
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1294333361466189264L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 1.���������ļ������	DiskFileItemFactory
		 * 2.�������Ľ���request��	ServletFileUpload
		 * 3.��ʼ����request���󣬷�����List����
		 * 4.List�����б��棨�������ݣ��ֲ��֣�
		 * 5.ѭ���������ϣ���ȡ����
		 */
		
		// �жϱ���enctype�Ƿ���ȷ
		if(!ServletFileUpload.isMultipartContent(request)){
			// ���û���ʾ
			throw new RuntimeException("enctype���ò���ȷ");//�൱��return
		}
		
		
		// ���������ļ������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// ���û������Ĵ�С��Ĭ��10K
		factory.setSizeThreshold(1024*1024*3);	// ���û�����Ϊ3M
		// �Ȼ�ȡtemp�ľ��Դ���·��
		String temp  = getServletContext().getRealPath("/temp");
		// ������ʱ�ļ����ļ��Ĵ�С���ڻ������Ĵ�С��
		factory.setRepository(new File(temp));
		
		// �������Ľ�������
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// �����ϴ��ļ����ܴ�С
		// upload.setSizeMax(1024*1024*5);	// ����5M
		
		// �����ϴ��ļ����ļ����Ƶı���
		upload.setHeaderEncoding("UTF-8");
		
		try {
			// ����request
			List<FileItem> list = upload.parseRequest(request);
			// ѭ������
			for (FileItem fileItem : list) {
				// fileItem�п�������ͨ���ı��Ҳ�п�����һ���ļ��ϴ���
				// ���жϵ�ǰfileItem����ͨ�����ϴ���		
				// isFormField() ����true��������ͨ�ı������false�������ļ��ϴ���
				if(fileItem.isFormField()){
					// ��ͨ��
					// ��ȡname������  filedesc  password  sex
					String name = fileItem.getFieldName();
					// ��ȡ�û������ֵ
					String value = fileItem.getString("UTF-8");
					System.out.println(name+" : "+value);
				}else{
					// �ļ��ϴ���
					// �Ȼ�ȡ�ļ�������
					String filename = fileItem.getName();
					
					// ��Ӧ�������ݿ��а��ļ���ԭ���ƺ�UUID�����ƶ���Ҫ���浽���ݿ��С�
					// ��ȡΨһ���ַ���
					filename = UUID.randomUUID().toString()+"_"+filename;
					System.out.println("�ļ����ƣ�"+filename);
					// ��ȡ�ļ���������
					InputStream in = new BufferedInputStream(fileItem.getInputStream());
					// ��ĳ���ļ���д��
					// ��WebRoot/uploadĿ¼д��
					String path = getServletContext().getRealPath("/upload");
					
					// ����Ŀ¼����
					String dir = UploadUtil.getPath(filename);
					File file = new File(path+dir);
					// �������Ŀ¼
					file.mkdirs();
					
					// ��ȡ�����
					OutputStream os = new BufferedOutputStream(new FileOutputStream(path+dir+"/"+filename));		
					// io����
					int len = 0;
					byte [] b = new byte[1024];
					while((len = in.read(b)) != -1){
						os.write(b, 0, len);
					}
					in.close();
					os.close();
					
					// ɾ����ʱ�ļ��������رպ�ִ�У�
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





