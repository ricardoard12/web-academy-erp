package academy.member.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        Vector vector = memberdao.isMember(memberbean);
        
        //벡터로 가져와서 검사
        int result = (int)vector.get(0);
        
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
        
        //세션으로 아아디 띄우기(로그인 id)
        HttpSession session = request.getSession();
        session.setAttribute("id", mm_id);
        
        //DB에서 가져온 이름 DB값 띄운다
        String name = (String) vector.get(1);
        session.setAttribute("name", name);
        
        //DB에서 가져온 레벨 DB값 띄운다
        String level = (String)vector.get(2);
        session.setAttribute("level", level);
        
        forward.setRedirect(false);
        forward.setPath("./Main.main");
        return forward;
    }

}
