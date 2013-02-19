package academy.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.member.db.MemberBean;
import academy.member.db.MemberDAO;

public class MemberLoginAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        request.setCharacterEncoding("utf-8");
        ActionForward forward = new ActionForward();
        
        String mm_id = request.getParameter("mm_id");
        String mm_passwd = request.getParameter("mm_passwd");
        
        MemberBean memberbean = new MemberBean();
        memberbean.setMm_id(mm_id);
        memberbean.setMm_passwd(mm_passwd);
        
        MemberDAO memberdao = new MemberDAO();
        int result = memberdao.isMember(memberbean);
        
        if(result == 0){
            //비밀번호 틀림
            response.setContentType ("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('비밀번호 다시입력하세요');");
            out.println("history.back();");
            out.println("</script>");
            out.close();
            return null;
            
        }else if(result == -1){
            //아이디 없음 틀림
            response.setContentType ("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('아이디 틀림');");
            out.println("history.back();");
            out.println("</script>");
            out.close();
            return null;
            
        }
        
        //아아디 띄우기(로그인 id)
        request.setAttribute("id", mm_id);
        
        forward.setRedirect(false);
        forward.setPath("./Main.main");
        return forward;
    }

}
