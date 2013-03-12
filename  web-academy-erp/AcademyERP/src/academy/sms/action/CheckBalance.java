package academy.sms.action;

import java.io.IOException;

public class CheckBalance {
	private int cash;
	private int point;
	private int mDrop;
	private int credits;
	
	
	public int getCash() {
		return cash;
	}

	public int getPoint() {
		return point;
	}

	public int getmDrop() {
		return mDrop;
	}

	public int getCredits() {
		return credits;
	}
	
    private SmsBalanceInfo sbi = null;
	
	public CheckBalance() {}
	
	void checkBalance() {
		SMS sms = new SMS();
	
		/**
		 * 사용자 아이디, 비밀번호 설정
		 */
		sms.setuser("academytest", "dkzkepal1234");
	
		/**
		 * 사용가능한 SMS건수 확인
		 */

		try {
			sms.connect();
			sbi = sms.getBalanceInfo();
			sms.disconnect();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	
		if (sbi.resultCode.equals("00"))
		{
//			System.out.println("캐쉬: " + sbi.cash);
//			System.out.println("포인트: " + sbi.point);
//			System.out.println("문자방울: " + sbi.mdrop);
//			System.out.println("발송가능 SMS 건수: " + sbi.credits);
			cash = sbi.cash;
			point = sbi.point;
			mDrop = sbi.mdrop;
			credits = sbi.credits;
		} else {
//			System.out.println("Result Code: " + sbi.resultCode);
//			System.out.println("Result Message: " + sbi.resultMessage);
			cash = -1;
			point = -1;
			mDrop = -1;
			credits = -1;
		}
		
	}

}
