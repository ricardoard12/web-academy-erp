package academy.seq_board.action;

public class ActionForward {

	private boolean isRedirect =false; // 데이터를 가지고 전송되는지 확인 
	private String path=null; // URL주소를 저장한다.
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
