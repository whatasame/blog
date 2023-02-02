package ch16.sec02.exam02;

public class ButtonExample {
    public static void main(String[] args) {
        /* OK 버튼 */
        Button btnOk = new Button();
        btnOk.setClickListener(()-> { //  Button 객체에 ClickListener 익명 구현 객체 주입
            System.out.println("OK 버튼을 클릭했습니다.");
        });
        btnOk.click();

        /* Cancel 버튼 */
        Button btnCancel = new Button();
        btnCancel.setClickListener(() -> { //  Button 객체에 ClickListener 익명 구현 객체 주입
            System.out.println("Cancel 버튼을 클릭했습니다.");
        });
        btnCancel.click();
    }
}
