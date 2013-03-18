package academy.noticle.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class NoticleFrontController extends HttpServlet implements Servlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String RequestURI = request.getRequestURI();
		String contextPath= request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		
		//Action Forward 정의
		ActionForward  forward = null;
		Action action = null;
		if(command.equals("/NoticeList.no")){
			action = new NoticeListAction();
			try {
				forward = action.execute(request,response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(command.equals("/NoticleWriting.no")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./notice/notice_write.jsp");
		}else if(command.equals("/NoticleWritingAction.no")){
			action = new NoticleWritingAction();
			try {
				forward =action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(command.equals("/NoticeDetail.no")){
			action = new NoticeDetailAction();
			try {
				forward =action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(command.equals("/NoticeModify.no")){
			action=new NoticeModify();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}else if(command.equals("/NoticeModifyAction.no")){
			action = new NoticeModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace()	;
			}
		}else if(command.equals("/NoticeDelete.no")){
			action = new NoticeDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		//이동
				if(forward!=null){
					if(forward.isRedirect()){ // 데이터가 없으면 그냥 주소를 넘긴다.
						response.sendRedirect(forward.getPath());
					}else{ // 데이터를 가지고 넘어가면 RequestDispatcher를 정의해서 주소를 넘긴다.
						RequestDispatcher dispatcher =request.getRequestDispatcher(forward.getPath());
						dispatcher.forward(request, response);
					}
				}
	}
  
}
